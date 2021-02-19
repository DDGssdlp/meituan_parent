package com.ddg.meituan.product.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.ddg.meituan.common.annotation.ListValue;
import com.ddg.meituan.common.validgroup.AddGroup;
import com.ddg.meituan.common.validgroup.UpdateGroup;
import com.ddg.meituan.product.entity.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
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
