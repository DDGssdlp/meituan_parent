package com.ddg.meituan.product.domain.vo;

import com.ddg.meituan.product.domain.AttrEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/12/27 11:11
 * @email: wangzhijie0908@gmail.com
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AttrRespVo extends AttrEntity {

    /**
     * 属性分类的名称
     */
    private String categoryName;
    /**
     *  属性分组的名称
     */
    private String attrGroupName;
    /**
     *  分类路径
     */
    private Long[] categoryPath;
}
