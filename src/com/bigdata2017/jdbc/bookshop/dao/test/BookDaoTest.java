package com.bigdata2017.jdbc.bookshop.dao.test;

import java.util.List;

import com.bigdata2017.jdbc.bookshop.dao.AuthorDao;
import com.bigdata2017.jdbc.bookshop.dao.BookDao;
import com.bigdata2017.jdbc.bookshop.vo.AuthorVo;
import com.bigdata2017.jdbc.bookshop.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
//		insertTest();
//		updateTest();
		testGetList();
	}
	
	public static void insertTest() {
		BookDao dao = new BookDao();
		dao.insert(new BookVo("트와일라잇", "대여가능", 1L));
		dao.insert(new BookVo("뉴문", "대여가능", 1L));
		dao.insert(new BookVo("이클립스", "대여가능", 1L));
		dao.insert(new BookVo("브레이킹던", "대여가능", 1L));
		dao.insert(new BookVo("아리랑", "대여가능", 2L));
		dao.insert(new BookVo("젊은그들", "대여가능", 3L));
		dao.insert(new BookVo("아프니까 청춘이다", "대여가능", 4L));
		dao.insert(new BookVo("귀천", "대여가능", 5L));
		dao.insert(new BookVo("태백산백", "대여가능", 2L));
		dao.insert(new BookVo("풀하우스", "대여가능", 6L));
	}
	
	public static void testGetList() {
		BookDao dao = new BookDao();
		List<BookVo>list = dao.getList();
		
		for (BookVo Vo : list) {
			System.out.println(Vo.toString());
		}
	}
	
	public static void updateTest() {
		new BookDao().updateState(6L, "대여가능");
	}
}
