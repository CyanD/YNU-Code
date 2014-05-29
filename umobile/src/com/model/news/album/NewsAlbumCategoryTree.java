package com.model.news.album;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NewsAlbumCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_album_category", catalog = "umobile")
public class NewsAlbumCategoryTree implements java.io.Serializable {

	// Fields

	private Long id;
	private Long _parentId;
	private String name;

	/** default constructor */
	public NewsAlbumCategoryTree() {
	}


	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}
	
	@Column(name = "pid")
	public Long get_parentId() {
		return _parentId;
	}


	public void set_parentId(Long _parentId) {
		if(_parentId==null)_parentId=0l;
		this._parentId = _parentId;
	}


	public void setName(String name) {
		this.name = name;
	}
}