package com.luolh.blog.test;

import java.util.List;

import org.junit.Test;

import com.luolh.blog.entities.Page;
import com.luolh.blog.utils.PageUtils;

/**
 *
 * @author LuoLH
 * @since
 */
public class PageUtilsTest {

	@Test
	public void testGetPageBean() {
		
		int pageNumber = 10;
		int pageNo = 21;
		int totalPage = 25;
		List<Integer> numberList = PageUtils.getNumberList(pageNumber, pageNo, totalPage);
		for(Integer number:numberList) {
			System.out.print(number+" ");
		}
	}
}
