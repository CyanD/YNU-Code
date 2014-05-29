package com.vo.bean;

import java.io.File;
import java.util.List;

import com.model.course.CourseVideoLazy;
import com.ynu.zjx.PageBean;



public class CourseVideoVO extends PageBean{
	private String ids;
	private Long id;
	private Long pid;
	private String name;
	private Integer orders;
	private String path;
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	public CourseVideoLazy getCourseVideoLazy(){
		return new CourseVideoLazy(id, pid, name, path, orders);
	}
	public List<File> getFiledata() {
		return Filedata;
	}
	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}
	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}
	public Long getId() {
		return id;
	}
	public String getIds() {
		return ids;
	}
	public Integer getOrders() {
		return orders;
	}
	public String getPath() {
		return path;
	}
	public Long getPid() {
		return pid;
	}
	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}
	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}
	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
