package com.vo.bean;

import com.model.news.album.NewsAlbumGrid;
import com.model.news.album.NewsAlbumLazy;
import com.ynu.zjx.PageBean;



public class NewsAlbumVO extends PageBean{
	private Long id;
	private String ids;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	private String title;
	private String photographer;
	private String coverPath;
	private String description;
	private Integer visitors;
	private String status;
	private Long categoryId;
	public NewsAlbumLazy getNewsAlbumLazy(){
		return new NewsAlbumLazy(id, categoryId, title, photographer, coverPath, description, visitors, status);
	}
	public NewsAlbumGrid getNewsAlbumGrid(){
		return new NewsAlbumGrid(id, title, photographer,coverPath, description, visitors, status);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhotographer() {
		return photographer;
	}
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
	public String getCoverPath() {
		return coverPath;
	}
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getVisitors() {
		return visitors;
	}
	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
