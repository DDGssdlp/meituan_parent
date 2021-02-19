package com.ddg.meituan.product.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.ddg.meituan.common.annotation.ListValue;
import com.ddg.meituan.common.validgroup.AddGroup;
import com.ddg.meituan.common.validgroup.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/6 16:35
 * @email: wangzhijie0908@gmail.com
 */
@Data
public class BrandListVo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long brandId;

    private String name;

    private Integer rate;

    private String img;

    private String type;

    private String addr;

    private String status;

    private Integer comment;
}
