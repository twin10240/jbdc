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

public class AuthorDao {
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
	
	public int delete() {
		int count = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = getConnection();
			
			// 3. statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = "delete from author";
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
	
	public int delete(Long no) {
		return 0;
	}
	
	public int delete(String name) {
		return 0;
	}
	
	
	public int insertList(AuthorVo vo) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. statement 준비
			String sql = "insert into author values(seq_author.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getProfile());
			
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
	
	public List<AuthorVo> getList(){
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			// 3. statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = "select no, name, profile from author order by no";
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 가져오기(사용하기)
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String profile = rs.getString(3);
				
				list.add(new AuthorVo(no, name, profile));
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
}
