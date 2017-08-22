package com.bigdata2017.jdbc.bookshop.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bigdata2017.jdbc.bookshop.dao.BookDao;
import com.bigdata2017.jdbc.bookshop.vo.BookVo;

public class BookShopApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("*****도서 정보 출력하기******");
			displayBookInfo();
			
			System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
			String input = scanner.nextLine();
			
			// quit이면 종료
			if ("quit".equals(input.toLowerCase())) {
				break;
			}

			// 숫자가 아님
			if (input.matches("\\d+") == false) {
				continue;
			}

			Long no = Long.parseLong(input);
//			System.out.println(no);

			/* state 정보 setting */
			BookDao dao = new BookDao();
			dao.updateState(no, "대여중");
			
			for (int i = 0; i < dao.getList().size(); i++) {
				if (dao.getList().get(i).getNo() == no) {
					System.out.println(dao.getList().get(i).getTitle() + "이(가) 대여됐습니다.");
				}
			}
			System.out.println();
		}
		scanner.close();
	}

	private static void displayBookInfo() {
		List<BookVo> list = new ArrayList<BookVo>();
		list = new BookDao().getList();
		
		for (BookVo vo : list) {
			System.out.println(vo.toString());
		}
	}
}