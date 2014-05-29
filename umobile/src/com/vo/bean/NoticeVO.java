package com.vo.bean;

import com.model.notice.NoticeLazy;
import com.ynu.zjx.PageBean;



public class NoticeVO extends PageBean{
	private Long id;
	private Long categoryId;
	private String ids;
	private String title;
	private String deptName;
	private String author;
	private String keywords;
	private String abstracts;
	private String content;
	private Integer visitors;
	private String status;
	public NoticeLazy getNoticeLazy(){
		return new NoticeLazy(id, categoryId, title, deptName, author, keywords, abstracts, content, visitors, status);
	}
	public String getAbstracts() {
		return abstracts;
	}
	public String getAuthor() {
		return author;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public String getContent() {
		return content;
	}
	public String getDeptName() {
		return deptName;
	}
	public Long getId() {
		return id;
	}
	public String getIds() {
		return ids;
	}
	public String getKeywords() {
		return keywords;
	}
	public String getStatus() {
		return status;
	}
	public String getTitle() {
		return title;
	}
	public Integer getVisitors() {
		return visitors;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
}
