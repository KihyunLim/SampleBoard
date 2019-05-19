package com.testboard.biz.boardReply;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardReplyVO {

	private int seq;
	private int boardSeq;
	private String parentSeq;
	private String writer;
	private String content;
	private Date regDate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(String parentSeq) {
		this.parentSeq = parentSeq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	
	@Override
	public String toString() {
		return "BoardReplyVO [seq=" + seq + ", boardSeq=" + boardSeq + ", parentSeq=" + parentSeq + ", writer=" + writer
				+ ", content=" + content + ", regDate=" + regDate + "]";
	}
}
