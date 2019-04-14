package com.testboard.biz.board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {

	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date regDate;
	private int cnt;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		SimpleDateFormat simpleRegDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return simpleRegDate.format(regDate);
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", writer=" + writer + ", title=" + title + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + "]";
	}
}
