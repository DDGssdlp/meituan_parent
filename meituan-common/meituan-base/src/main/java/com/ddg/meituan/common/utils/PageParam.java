package com.ddg.meituan.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Tag;
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
@ApiModel("分页参数")
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
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    private String sidx;

    /**
     * 排序方式
     */
    @ApiModelProperty("排序方式")
    private String order;

    @ApiModelProperty("分页模型")
    private Page<?> iPage;


    public <T> void put(Page<T> page) {
        this.iPage = page;
    }
}