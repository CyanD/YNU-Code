package com.model.news.news;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * NewsNewsCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_news_category", catalog = "umobile")
public class NewsNewsCategory implements java.io.Serializable {

	// Fields

	private Long id;
	private NewsNewsCategory newsNewsCategory;
	private String name;
	private Timestamp createTime;
	private String publisher;
	private Set<NewsNews> newsNewses = new HashSet<NewsNews>(0);
	private Set<NewsNewsCategory> newsNewsCategories = new HashSet<NewsNewsCategory>(
			0);

	// Constructors

	/** default constructor */
	public NewsNewsCategory() {
	}

	/** minimal constructor */
	public NewsNewsCategory(String name, Timestamp createTime, String publisher) {
		this.name = name;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	/** full constructor */
	public NewsNewsCategory(NewsNewsCategory newsNewsCategory, String name,
			Timestamp createTime, String publisher, Set<NewsNews> newsNewses,
			Set<NewsNewsCategory> newsNewsCategories) {
		this.newsNewsCategory = newsNewsCategory;
		this.name = name;
		this.createTime = createTime;
		this.publisher = publisher;
		this.newsNewses = newsNewses;
		this.newsNewsCategories = newsNewsCategories;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public NewsNewsCategory getNewsNewsCategory() {
		return this.newsNewsCategory;
	}

	public void setNewsNewsCategory(NewsNewsCategory newsNewsCategory) {
		this.newsNewsCategory = newsNewsCategory;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsNewsCategory")
	public Set<NewsNews> getNewsNewses() {
		return this.newsNewses;
	}

	public void setNewsNewses(Set<NewsNews> newsNewses) {
		this.newsNewses = newsNewses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsNewsCategory")
	public Set<NewsNewsCategory> getNewsNewsCategories() {
		return this.newsNewsCategories;
	}

	public void setNewsNewsCategories(Set<NewsNewsCategory> newsNewsCategories) {
		this.newsNewsCategories = newsNewsCategories;
	}

}