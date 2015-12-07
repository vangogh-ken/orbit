package org.cc.automate.core;

import java.util.List;

import org.cc.automate.utils.PageIndex;
import org.cc.automate.utils.WebTool;

/**
 * //分页封装函数
 * 
 * @param <T>
 */
public class Pager {
	/** 正序. */
	public static final String ASC = "ASC";

	/** 倒序. */
	public static final String DESC = "DESC";

	/** 默认每页显示20条数据. */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/** 使用正序还是倒序. */
	private String order = ASC;

	/** 排序字段名. */
	private String orderBy;

	/** 规定显示5个页码 */
	private int pagecode = 5;

	/** 总页数,这个数是计算出来的 */
	private long pageCount;

	/** 页码的开始,索引类这个类包含，startindex　开始索引 endindex　　结束索引这个数是计算出来的 */
	private PageIndex pageindex;

	/*** 默认 当前页 为第一页,这个数是计算出来的 */
	private int pageNo = 1;

	/** 每页显示几条记录 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** 分页数据 */
	private List results;

	/** 每页数 */
	private int resultSize;

	/** 总记录数 */
	private long totalCount;

	/** 从第几条记录开始 */
	private int startPage;
	
	/**
	 * 过滤数据字符串 SQL语句
	 */
	private String filterText;

	public Pager() {
	}

	/**
	 * 使用构造函数，强制必需输入,当前页.@param pageNo　当前页
	 */
	public Pager(int pageNo) {
		this.pageNo = pageNo;
		startPage = (this.pageNo - 1) * this.pageSize;
	}

	/** 使用构造函数，，强制必需输入.每页显示数量　和　当前页.@param pageSize　　每页显示数量.@param pageNo　当前页 */
	public Pager(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public Pager(int pageSize, int pageNo, String orderBy, String order) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.orderBy = orderBy;
		this.order = order;
	}

	/** 要获得记录的开始索引　即　开始页码 */
	public int getFirstResult() {
		return (this.pageNo - 1) * this.pageSize;
	}

	public String getOrder() {
		return order;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public int getPagecode() {
		return pagecode;
	}

	public long getPageCount() {
		return pageCount;
	}

	public PageIndex getPageindex() {
		return pageindex;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List getResults() {
		return results;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	/**
	 * WebTool这是一个分页工具类
	 * 
	 * 　pagecode　要获得记录的开始索引　即　开始页码 pageNo 　当前页 　pageCount 总页数
	 * 
	 * 这个工具类　返回的是页索引　PageIndex
	 * 
	 * 在这个方法中存在一个问题，每页显示数量　和　当前页、、不能为空 必需输入
	 */
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
		this.pageindex = WebTool.getPageIndex(pagecode, pageNo, pageCount);
	}

	public void setPageindex(PageIndex pageindex) {
		this.pageindex = pageindex;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 查询结果方法 把　记录数　结果集合　放入到　PageView对象
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param results
	 *            结果集合
	 */

	public void setQueryResult(long totalCount, List results) {
		setTotalCount(totalCount);
		setResults(results);
	}

	public void setResults(List results) {
		this.results = results;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		setPageCount(this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize : this.totalCount / this.pageSize + 1);
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getResultSize() {
		return results.size();
	}

	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public boolean isAsc() {
		return ASC.equals(order);
	}

	public String getFilterText() {
		return filterText;
	}

	public void setFilterText(String filterText) {
		this.filterText = filterText;
	}

}
