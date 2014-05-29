package com.model.news.news;

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

import com.model.news.picture.NewsNewsPicture;
import com.model.sys.SysDept;

/**
 * NewsNews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news_news", catalog = "umobile")
public class NewsNews implements java.io.Serializable {

	// Fields

	private Long id;
	private SysDept sysDept;
	private NewsNewsCategory newsNewsCategory;
	private String categoryName;
	private String title;
	private String deptName;
	private String author;
	private String photographer;
	private String keywords;
	private String headline;
	private String abstracts;
	private String coverPath;
	private String content;
	private Integer visitors;
	private String status;
	private String createTime;
	private String publisher;
	private Set<NewsNewsPicture> newsNewsPictures = new HashSet<NewsNewsPicture>(
			0);

	// Constructors

	/** default constructor */
	public NewsNews() {
	}

	/** minimal constructor */
	public NewsNews(String title, String author, String abstracts,
			String content, String status, String createTime,
			String publisher) {
		this.title = title;
		this.author = author;
		this.abstracts = abstracts;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	/** full constructor */
	public NewsNews(SysDept sysDept, NewsNewsCategory newsNewsCategory,
			String categoryName, String title, String deptName, String author,
			String photographer, String keywords, String headline,
			String abstracts, String coverPath, String content,
			Integer visitors, String status, 
			String createTime, String publisher,
			Set<NewsNewsPicture> newsNewsPictures) {
		this.sysDept = sysDept;
		this.newsNewsCategory = newsNewsCategory;
		this.categoryName = categoryName;
		this.title = title;
		this.deptName = deptName;
		this.author = author;
		this.photographer = photographer;
		this.keywords = keywords;
		this.headline = headline;
		this.abstracts = abstracts;
		this.coverPath = coverPath;
		this.content = content;
		this.visitors = visitors;
		this.status = status;
		this.createTime = createTime;
		this.publisher = publisher;
		this.newsNewsPictures = newsNewsPictures;
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
	@JoinColumn(name = "deptId")
	public SysDept getSysDept() {
		return this.sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	public NewsNewsCategory getNewsNewsCategory() {
		return this.newsNewsCategory;
	}

	public void setNewsNewsCategory(NewsNewsCategory newsNewsCategory) {
		this.newsNewsCategory = newsNewsCategory;
	}

	@Column(name = "categoryName", length = 30)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "deptName", length = 100)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "author", nullable = false, length = 20)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "photographer", length = 20)
	public String getPhotographer() {
		return this.photographer;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}

	@Column(name = "keywords", length = 100)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "headline", length = 2)
	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name = "abstracts", nullable = false, length = 100)
	public String getAbstracts() {
		return this.abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	@Column(name = "coverPath", length = 50)
	public String getCoverPath() {
		return this.coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "visitors")
	public Integer getVisitors() {
		return this.visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}

	@Column(name = "status", nullable = false, length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsNews")
	public Set<NewsNewsPicture> getNewsNewsPictures() {
		return this.newsNewsPictures;
	}

	public void setNewsNewsPictures(Set<NewsNewsPicture> newsNewsPictures) {
		this.newsNewsPictures = newsNewsPictures;
	}

}