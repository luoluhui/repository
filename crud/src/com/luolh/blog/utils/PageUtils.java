package com.luolh.blog.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 * @author LuoLH
 * @since 20180606
 */
public class PageUtils {

	/**
	 * 获取PageBean
	 * @param pageCount 每页显示条数
	 * @param pageNumber 每页分页条显示数
	 * @return PageBean
	 */
	/*public static Page<Object> getPageBean(int pageCount, int pageNumber) {
		Page<Object> page = new Page<>();
		page.setPageCount(pageCount);
		page.setPageNumber(pageNumber);
		page.setNumberList(getNumberList(pageNumber,page.getPageNo(),page.getTotalPage()));
		return page;
	}*/

	/**
	 * 获取分页条list
	 * @param pageNumber 每页分页条显示数
	 * @param pageNo 当前页码
	 * @param totalPage 总页数
	 * @return 分页条数字list
	 */
	public static List<Integer> getNumberList(int pageNumber, int pageNo, int totalPage) {
		List<Integer> numberList = new ArrayList<>();
		int begin = 1;
		int end = totalPage;
		int number = pageNumber / 2;
		/*
		 * 1.totalPage小于pageNumber begin=1 end=totalPage 2.totalPage大于pageNumber 2.1
		 * pageNumber%2==0（假如总25页 每页显示10页） 2.1.1 pageNo <= number Begin = 1 end =
		 * pageNumber 2.1.2 pageNo > number Begin = pageNo - number 2.1.2.1 pageNo >
		 * (totalPage - number + 1) End = totalPage 2.1.2.2 End = pageNo + number - 1
		 */
		if (totalPage > pageNumber) {
			if (pageNo <= number) {
				end = pageNumber;
			} else {
				begin = pageNo - number;
				if (pageNo < (totalPage - number)) {
					end = pageNumber % 2 == 0 ? pageNo + number - 1 : pageNo + number;
				}
			}
		}

		for (int i = begin; i <= end; i++) {
			numberList.add(i);
		}
		return numberList;
	}
}
