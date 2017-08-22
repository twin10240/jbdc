package com.bigdata2017.jdbc.bookshop.dao.test;

import java.util.List;

import com.bigdata2017.jdbc.bookshop.dao.AuthorDao;
import com.bigdata2017.jdbc.bookshop.vo.AuthorVo;

public class AuthorDaoTest {
	public static void main(String[] args) {
		testDelete();
		testInsert();
		testGetList();
	}
	
	private static void testDelete() {
		new AuthorDao().delete();
	}

	public static void testGetList() {
		AuthorDao dao = new AuthorDao();
		List<AuthorVo>list = dao.getList();
		
		for (AuthorVo Vo : list) {
			System.out.println(Vo.toString());
		}
	}
	
	public static void testInsert() {
//		AuthorVo vo = new AuthorVo();
//		vo.setName("맹자");
//		vo.setProfile("맹자책");
		
		new AuthorDao().insertList(new AuthorVo("스테파니메이어"));
		new AuthorDao().insertList(new AuthorVo("조정래"));
		new AuthorDao().insertList(new AuthorVo("김동인"));
		new AuthorDao().insertList(new AuthorVo("김난도"));
		new AuthorDao().insertList(new AuthorVo("천상병"));
		new AuthorDao().insertList(new AuthorVo("원수연"));
	}
}
