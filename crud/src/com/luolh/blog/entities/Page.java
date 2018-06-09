package com.luolh.blog.entities;

import java.util.List;

/**
 * 分页数据类
 * @author LuoLH
 * @since 20180606
 */
public class Page<T> {

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 每页显示条数
	 */
	private int pageCount;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 当前页码
	 */
	private int pageNo;
	
	/**
	 * 每页分页条显示数
	 * */
	private int pageNumber;
	
	/**
	 * 分页条数字
	 * */
	private List<Integer> numberList;
	
	/**
	 * 当前页显示list
	 */
	private List<T> list;

	/**
	 * 
	 * */
	private String url;
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		int page = totalCount/pageNumber;
		totalPage = totalCount%pageNumber==0?page:page+1;
		return totalPage;
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the numberList
	 */
	public List<Integer> getNumberList() {
		return numberList;
	}

	/**
	 * @param numberList the numberList to set
	 */
	public void setNumberList(List<Integer> numberList) {
		this.numberList = numberList;
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
