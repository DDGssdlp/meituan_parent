package com.ddg.meituan.base.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author wzj
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/11/20 15:27
 * @email: wangzhijie0908@gmail.com
 */
@Data
@ApiModel("分页参数模型")
public class PageParam{

    /**
     * 开始的页码
     */
    @ApiModelProperty("开始的页码")
    private String page = String.valueOf(1);

    /**
     * 每页显示记录数
     */
    @ApiModelProperty("每页显示记录数")
    private String limit = String.valueOf(10);

    /**
     * 查询关键字
     */
    @ApiModelProperty("查询关键字")
    private String key;

    /**
     * id
     */
    @ApiModelProperty("查询的ID")
    private Long id;

    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    private String sidx;

    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式")
    private String order;

    //@ApiModelProperty(value = "分页模型", hidden = true)
    private Page<?> iPage;


    public <T> void put(Page<T> page) {
        this.iPage = page;
    }
}