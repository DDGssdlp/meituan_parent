package com.ddg.meituan.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class PageParam{

    /**
     * 开始的页码
     */
    private String page = String.valueOf(1);

    /**
     * 每页显示记录数
     */
    private String limit = String.valueOf(10);

    /**
     * 排序字段
     */
    private String sidx;

    /**
     * 排序方式
     */
    private String order;

    private  Page iPage;


    public <T> void put(Page<T> page) {
        this.iPage = page;
    }
}