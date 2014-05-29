package com.vo.bean;

import com.model.publicservice.StudentFieldLazy;
import com.ynu.zjx.PageBean;

public class StudentFieldVO extends PageBean{
	private Long id;
	private Long kindId;
	private String kindName;
	private String title;
	private String content;
	private String createTime;
	private String publisher;
    private String ids;

	public StudentFieldLazy getStudentfieldLazy(){
		return new StudentFieldLazy(id, kindId, title, content, createTime, publisher);
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getKindId() {
		return kindId;
	}
	public void setKindId(Long kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
