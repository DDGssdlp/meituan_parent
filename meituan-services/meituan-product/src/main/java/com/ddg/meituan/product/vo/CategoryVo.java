package com.ddg.meituan.product.vo;

import com.ddg.meituan.product.entity.CategoryEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/5 12:10
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class CategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Long catId;
    /**
     * 分类名称
     */

    private String name;

    /**
     * 子分类：
     * */
    private List<CategoryEntity> children;

}
