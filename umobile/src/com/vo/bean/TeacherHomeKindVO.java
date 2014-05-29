package com.vo.bean;

import com.model.publicservice.TeacherHomeKind;
import com.ynu.zjx.PageBean;

public class TeacherHomeKindVO extends PageBean{
	private Long id;
    private Long _parentId;
    private String name;
    private String createTime;
    private String publisher;
    private String ids;

	public TeacherHomeKind getTeacherhomeKind(){
		return new TeacherHomeKind(id, _parentId, name, createTime, publisher);
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
	
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
