package com.ddg.meituan.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.annotation.cache.RedisCache;
import com.ddg.meituan.base.annotation.cache.RemoveCache;
import com.ddg.meituan.base.api.PageParam;
import com.ddg.meituan.base.api.Query;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.constant.ProductConstant;
import com.ddg.meituan.product.dao.CategoryDao;
import com.ddg.meituan.product.domain.CategoryEntity;
import com.ddg.meituan.product.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public PageUtils<CategoryEntity> queryPage(PageParam param) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(param),
                new QueryWrapper<CategoryEntity>()
        );

        return PageUtils.of(page);
    }

    @Override
    @RedisCache(redisKey = "redis:list:tree", resClass = CategoryEntity.class, isList = true, lockName =
            "category:lock")
    public List<CategoryEntity> getListWithTree() {
        //首先是获取所有的CategoryEntity
        List<CategoryEntity> list = categoryDao.selectList(null);

        // 进行父子结构的组装：每一个分类下最多返回15个子节点
        return list.stream()
                .map(categoryEntity -> setChildren(categoryEntity, list, ProductConstant.MAX_LEVEL3_COUNT))
                .filter(categoryEntity -> ProductConstant.CAT_LEVEL_ONE.equals(categoryEntity.getCatLevel()))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort)).limit(ProductConstant.MAX_FATHER_LENGTH).collect(Collectors.toList());
    }

    @Override

    @RemoveCache(value = {"redis:list:tree", "redis:list:parent", "redis:key:page"})
    public void changeStatus(CategoryEntity categoryEntity) {
        // 返回的直接是修改之后的显示状态 直接进行数据库的更新即可
        categoryDao.updateById(categoryEntity);
    }

    @Override
    public PageUtils<CategoryEntity> queryPageById(PageParam param) {
        Long cartId = param.getId();
        String key = param.getKey();
        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        if (cartId != null) {
            wrapper.eq(ProductConstant.PARENT_CART_ID, cartId);
        }
        if(!StringUtils.isEmpty(key)){
            wrapper.like(ProductConstant.NAME, key);
        }
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(param),
                wrapper
        );
        return PageUtils.of(page);
    }

    @Override
    @RedisCache(redisKey = "redis:list:parent", resClass = CategoryEntity.class, isList = true)
    public List<CategoryEntity> getParentList() {
        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.ne(ProductConstant.CART_LEVEL, ProductConstant.CAT_LEVEL_THREE);
        List<CategoryEntity> list = categoryDao.selectList(wrapper);
        return list.stream()
                .filter(categoryEntity -> ProductConstant.SHOW_STATUS.equals(categoryEntity.getShowStatus()))
                .map(categoryEntity -> setChildren(categoryEntity, list, ProductConstant.MAX_LEVEL2_COUNT))
                .filter(categoryEntity -> ProductConstant.CAT_LEVEL_ONE.equals(categoryEntity.getCatLevel()))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort)).limit(ProductConstant.MAX_FATHER_LENGTH).collect(Collectors.toList());
    }

    @Override
    public Long[] findCategoryPath(Long categoryId, boolean isSelf) {

        List<Long> paths = new ArrayList<>();
        //递归查询是否还有父节点
        List<Long> parentPath = findParentPath(categoryId, paths);
        //进行一个逆序排列
        if(isSelf){
            parentPath.add(categoryId);
        }

        return parentPath.toArray(new Long[parentPath.size()]);
    }

    private List<Long> findParentPath(Long categoryId, List<Long> paths) {
        //根据当前分类id查询信息
        CategoryEntity byId = this.getById(categoryId);

        Long parentCid = byId.getParentCid();
        //如果当前分类层级 不为1
        if (!ProductConstant.ROOT_CID.equals(parentCid) || paths.size() >= ProductConstant.CAT_LEVEL_THREE) {
            findParentPath(parentCid, paths);
            paths.add(parentCid);
        }

        return paths;
    }

    /**
     *  进行父子结构的组装：
     */
    private CategoryEntity setChildren(CategoryEntity categoryEntity, List<CategoryEntity> list, Integer limit) {
        List<CategoryEntity> collect = list.stream()
                .filter(categoryEntity1 -> ProductConstant.SHOW_STATUS.equals(categoryEntity1.getShowStatus())
                        && categoryEntity.getCatId().equals(categoryEntity1.getParentCid()))
                .limit(limit).collect(Collectors.toList());
        categoryEntity.setChildren(collect);
        return categoryEntity;

    }

}