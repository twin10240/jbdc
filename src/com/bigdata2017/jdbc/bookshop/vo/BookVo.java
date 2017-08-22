package com.bigdata2017.jdbc.bookshop.vo;

public class BookVo {
	private Long no;
	private String title;
	private String state;
	private Long authorNo;
	private String authorName;
	
	// insert용
	public BookVo(String title, String state, long authorNo) {
		this.title = title;
		this.state = state;
		this.authorNo = authorNo;
	}
	
	// select용
	public BookVo(Long no, String title, String state, String authorName) {
		this.no = no;
		this.title = title;
		this.state = state;
		this.authorName = authorName;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Long authorNo) {
		this.authorNo = authorNo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
//		return "BookVo [no=" + no + ", title=" + title + ", state=" + state + ", authorName=" + authorName + "]";
		return "책 제목 : " + title + ", 저자 : " + authorName + ", 대여 유뮤 : " + state;
	}
	
}
