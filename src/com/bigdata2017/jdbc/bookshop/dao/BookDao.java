package com.bigdata2017.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.jdbc.bookshop.vo.AuthorVo;
import com.bigdata2017.jdbc.bookshop.vo.BookVo;

public class BookDao {
	private String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private String hostName = "dev";
	private String hostPassWord = "dev";
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC 드라이버 로딩(JDBC 클래스 로딩)
			Class.forName(dbDriver);
			
			// 2. Connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, hostName, hostPassWord);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		
		return conn;
	}
	
	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			// 3. statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = "select t1.no, t1.title, t1.state, t2.name from book t1, author t2 where t1.author_no = t2.no";
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 가져오기(사용하기)
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String state = rs.getString(3);
				String authorName = rs.getString(4);
				
				list.add(new BookVo(no, title, state, authorName));
			}
		} catch (SQLException e) {
			System.out.println("연결 실패 : " + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (stmt != null) {
					stmt.close();
				}
				
				if (conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;

	}

	public int delete() {
		int count = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = getConnection();
			
			// 3. statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = "delete from book";
			count = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("연결 실패 : " + e);
		} finally {
			// 자원 정리
			try {
				if (stmt != null) {
					stmt.close();
				}
				
				if (conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}

	public int insert( BookVo vo ) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. statement 준비
			String sql = "insert into book values(seq_book.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getState());
			pstmt.setLong(3, vo.getAuthorNo());
			
			// 5. SQL문 실행
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("연결 실패 : " + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}

	public int updateState( Long no, String state ) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update book set state = '" + state + "' where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("연결 실패 : " + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
}