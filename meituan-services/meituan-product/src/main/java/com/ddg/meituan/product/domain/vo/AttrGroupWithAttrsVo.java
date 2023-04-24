package com.ddg.meituan.product.domain.vo;

import com.ddg.meituan.product.domain.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * Description: 分类下的 属性组 和 属性集合
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/27 18:30
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class AttrGroupWithAttrsVo {

    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long categoryId;

    private List<AttrEntity> attrs;
}
