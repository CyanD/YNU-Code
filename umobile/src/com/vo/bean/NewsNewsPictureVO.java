package com.vo.bean;

import java.io.File;
import java.util.List;

import com.model.news.picture.NewsNewsPictureLazy;
import com.ynu.zjx.PageBean;



public class NewsNewsPictureVO extends PageBean{
	private String ids;
	private Long id;
	private Long pid;
	private String edits;
	private String deletes;
	private String description;
	private Integer orders=0;
	private String path;
	private String isCover="否";
	private String createTime;
	private String publisher;
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	public String getCreateTime() {
		return createTime;
	}
	public String getDeletes() {
		return deletes;
	}
	public String getDescription() {
		return description;
	}
	public String getEdits() {
		return edits;
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
	public String getIsCover() {
		return isCover;
	}
	public NewsNewsPictureLazy getNewsNewsPictureLazy(){
		return new NewsNewsPictureLazy(id, pid, description, orders, path, isCover, createTime, publisher);
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
	public String getPublisher() {
		return publisher;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setDeletes(String deletes) {
		this.deletes = deletes;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEdits(String edits) {
		this.edits = edits;
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
	public void setIsCover(String isCover) {
		this.isCover = isCover;
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
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
