package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.common.exception.MeituanSysException;
import com.ddg.meituan.product.constant.ProductConstant;
import com.ddg.meituan.product.dao.ProductInfoDao;
import com.ddg.meituan.product.entity.ProductInfoEntity;
import com.ddg.meituan.product.enums.ProductEnum;
import com.ddg.meituan.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/5 16:48
 * @email: wangzhijie0908@gmail.com
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfoEntity> implements ProductInfoService {

    private final ProductInfoDao productInfoDao;

    @Autowired
    public ProductInfoServiceImpl(ProductInfoDao productInfoDao) {
        this.productInfoDao = productInfoDao;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductInfoEntity> page = this.page(
                new Query<ProductInfoEntity>().getPage(params),
                new QueryWrapper<ProductInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveProduct(ProductInfoEntity productInfoEntity) {
        String images = productInfoEntity.getImages();
        if (!StringUtils.isEmpty(images)){
            String[] split = images.split(",", 2);
            productInfoEntity.setDefaultImg(split[0]);
        }else{
            productInfoEntity.setDefaultImg(ProductConstant.DEFAULT_IMG);
        }
        productInfoDao.insert(productInfoEntity);

    }

    @Override
    public void upStateById(Long id) {
        QueryWrapper<ProductInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(ProductConstant.PRODUCT_ID, id);
        Integer integer = productInfoDao.selectCount(wrapper);
        if (integer.equals(0)){
            throw  new MeituanSysException("没有此商品");
        }
        ProductInfoEntity productInfoEntity = new ProductInfoEntity();
        productInfoEntity.setIsPublish(ProductEnum.UP.getState());
        productInfoEntity.setId(id);
        //TODO 将商品同步到es中 商品上架 需要进一步的优化

        productInfoDao.updateById(productInfoEntity);
    }
}
