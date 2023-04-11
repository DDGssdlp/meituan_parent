package com.ddg.meituan.base.utils;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 分页工具类
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/4/27 12:27
 * @email: wangzhijie0908@gmail.com
 */
@ApiModel("分页工具类")
@Data
public class PageUtils<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	@ApiModelProperty("总记录数")
	private int totalCount;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty("每页记录数")
	private int pageSize;
	/**
	 * 总页数
	 */
	@ApiModelProperty("总页数")
	private int totalPage;
	/**
	 * 当前页数
	 */
	@ApiModelProperty("当前页数")
	private int currPage;
	/**
	 * 列表数据
	 */
	@ApiModelProperty("列表数据")
	private List<T> list;
	
	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public PageUtils(List<T> list, long totalCount, long pageSize, long currPage) {
		this.list = list;
		this.totalCount = (int) totalCount;
		this.pageSize = (int) pageSize;
		this.currPage = (int) currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}

	/**
	 * 分页
	 */
	public PageUtils(IPage<T> page) {
		this.list = page.getRecords();
		this.totalCount = (int)page.getTotal();
		this.pageSize = (int)page.getSize();
		this.currPage = (int)page.getCurrent();
		this.totalPage = (int)page.getPages();
	}

	private PageUtils() {
	}

	public static <T> PageUtils<T> of(IPage<T> page){
		PageUtils<T> pageUtils = new PageUtils<>();
		if(page != null){
			pageUtils.list = page.getRecords();
			pageUtils.totalCount = (int)page.getTotal();
			pageUtils.pageSize = (int)page.getSize();
			pageUtils.currPage = (int)page.getCurrent();
			pageUtils.totalPage = (int)page.getPages();
		}

		return pageUtils;
	}

}
