package com.ddg.meituan.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddg.meituan.base.exception.MeituanSysException;
import com.ddg.meituan.base.utils.PageUtils;
import com.ddg.meituan.product.dao.SpuInfoDao;
import com.ddg.meituan.product.domain.*;
import com.ddg.meituan.product.domain.vo.AttrVo;
import com.ddg.meituan.product.domain.vo.SkuInfoVo;
import com.ddg.meituan.product.domain.vo.SpuInfoVo;
import com.ddg.meituan.product.domain.param.SpuInfoParam;
import com.ddg.meituan.product.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {


    private final SpuInfoDao spuInfoDao;

    private final SpuInfoDescService spuInfoDescService;

    private final SpuImagesService spuImagesService;

    private final AttrService attrService;

    private final ProductAttrValueService productAttrValueService;


    private final SkuInfoService skuInfoService;

    private final SkuImagesService skuImagesService;

    private final SkuSaleAttrValueService skuSaleAttrValueService;


    public SpuInfoServiceImpl(SpuInfoDao spuInfoDao,
                              SpuInfoDescService spuInfoDescService,
                              SpuImagesService spuImagesService,
                              AttrService attrService,
                              ProductAttrValueService productAttrValueService,
                              SkuInfoService skuInfoService,
                              SkuImagesService skuImagesService,
                              SkuSaleAttrValueService skuSaleAttrValueService) {
        this.spuInfoDao = spuInfoDao;
        this.spuInfoDescService = spuInfoDescService;
        this.spuImagesService = spuImagesService;
        this.attrService = attrService;
        this.productAttrValueService = productAttrValueService;
        this.skuInfoService = skuInfoService;
        this.skuImagesService = skuImagesService;
        this.skuSaleAttrValueService = skuSaleAttrValueService;
    }

    @Override
    public PageUtils<SpuInfoEntity> queryPage(SpuInfoParam param) {
        Page<SpuInfoEntity> page = new Page<>(Long.parseLong(param.getPage()), Long.parseLong(param.getLimit()));
        IPage<SpuInfoEntity> iPage = spuInfoDao.selectSpuInfoByParam(page, param);
        return PageUtils.of(iPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfoVo(SpuInfoVo spuInfo) {
        // 使用编程式事务实现
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuInfo, spuInfoEntity);
        spuInfoDao.insert(spuInfoEntity);


        final CompletableFuture<Void> desFuture = CompletableFuture.runAsync(() -> {
            //2、保存spu的描述图片：pms_spu_info_desc
            List<String> description = spuInfo.getDescription();
            if (!CollectionUtils.isEmpty(description)) {
                SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
                spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
                spuInfoDescEntity.setDescription(String.join(",", description));
                spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);
            }

        }).exceptionally(throwable -> {
            throw new MeituanSysException(throwable.getMessage());
        });


        final CompletableFuture<Void> imagesFuture = CompletableFuture.runAsync(() -> {
            //3、保存spu的图片集：pms_spu_images
            List<String> images = spuInfo.getImages();
            if (!CollectionUtils.isEmpty(images)) {
                spuImagesService.saveImages(spuInfoEntity.getId(), images);

            }
        }).exceptionally(throwable -> {
            throw new MeituanSysException(throwable.getMessage());
        });
        final CompletableFuture<Void> bastAttrFuture = CompletableFuture.runAsync(() -> {
            //4、保存spu的规格参数：pms_product_attr_value
            List<SpuInfoVo.BaseAttr> baseAttrs = spuInfo.getBaseAttrs();
            if (!CollectionUtils.isEmpty(baseAttrs)) {
                List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr -> {
                    ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
                    valueEntity.setAttrId(attr.getAttrId());
                    //查询attr属性名
                    AttrEntity byId = attrService.getById(attr.getAttrId());
                    valueEntity.setAttrName(byId.getAttrName());
                    valueEntity.setAttrValue(attr.getAttrValues());
                    valueEntity.setQuickShow(attr.getShowDesc());
                    valueEntity.setSpuId(spuInfoEntity.getId());
                    return valueEntity;
                }).collect(Collectors.toList());
                productAttrValueService.saveBatch(collect);

            }
        }).exceptionally((throwable) ->{
            throw new MeituanSysException(throwable.getMessage());
        });

        final CompletableFuture<Void> boundsFuture = CompletableFuture.runAsync(() -> {


        }).exceptionally((throwable) ->{
            throw new MeituanSysException(throwable.getMessage());
        });

        final CompletableFuture<Void> skuFuture = CompletableFuture.runAsync(() -> {
            //5、保存当前spu对应的所有sku信息：pms_sku_info
            //5、1）、sku的基本信息:pms_sku_info
            List<SkuInfoVo> skus = spuInfo.getSkus();
            if (!CollectionUtils.isEmpty(skus)) {

                skus.forEach(skuInfoVo -> {
                    String defaultImg = "";
                    for (SkuInfoVo.Images image : skuInfoVo.getImages()) {
                        if (image.getDefaultImg() == 1) {
                            defaultImg = image.getImgUrl();
                            break;
                        }
                    }

                    SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                    BeanUtils.copyProperties(skuInfoVo, skuInfoEntity);
                    skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                    skuInfoEntity.setCategoryId(spuInfoEntity.getCategoryId());
                    skuInfoEntity.setSaleCount(0L);
                    skuInfoEntity.setSpuId(spuInfoEntity.getId());
                    skuInfoEntity.setSkuDefaultImg(defaultImg);
                    skuInfoService.save(skuInfoEntity);

                    Long skuId = skuInfoEntity.getSkuId();

                    List<SkuImagesEntity> imagesEntities = skuInfoVo.getImages().stream().map(img -> {
                        SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                        skuImagesEntity.setSkuId(skuId);
                        skuImagesEntity.setImgUrl(img.getImgUrl());
                        skuImagesEntity.setDefaultImg(img.getDefaultImg());
                        return skuImagesEntity;
                    }).filter(entity -> {
                        //返回true就是需要，false就是剔除
                        return !StringUtils.isEmpty(entity.getImgUrl());
                    }).collect(Collectors.toList());

                    //5、2）、sku的图片信息：pms_sku_images
                    skuImagesService.saveBatch(imagesEntities);

                    //5、3）、sku的销售属性：pms_sku_sale_attr_value
                    List<AttrVo> attr = skuInfoVo.getAttr();
                    List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                        SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                        BeanUtils.copyProperties(a, skuSaleAttrValueEntity);
                        skuSaleAttrValueEntity.setSkuId(skuId);
                        return skuSaleAttrValueEntity;
                    }).collect(Collectors.toList());

                    skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);


                });
            }


        }).exceptionally((throwable) ->{
            throw new MeituanSysException(throwable.getMessage());
        });

        final CompletableFuture<Void> allOf = CompletableFuture.allOf(desFuture, imagesFuture, bastAttrFuture, boundsFuture, skuFuture);

        try {
            allOf.get();

        } catch (Exception e) {
            log.error("商品信息保存失败！ error = {}", e);
            throw new MeituanSysException(e.getMessage());
        }
    }

}