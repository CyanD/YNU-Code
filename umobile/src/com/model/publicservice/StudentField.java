package com.model.publicservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Studentfield entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "studentfield", catalog = "umobile")
public class StudentField implements java.io.Serializable {

	// Fields

	private Long id;
	private StudentFieldKindTree studentFieldKindTree;
	private String title;
	private String content;
	private String createTime;
	private String publisher;

	// Constructors

	/** default constructor */
	public StudentField() {
	}

	/** full constructor */
	public StudentField(Long id, String title,
			String content, String createTime, String publisher) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.publisher = publisher;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kindId", nullable = false)
	public StudentFieldKindTree getStudentFieldKindTree() {
		return studentFieldKindTree;
	}

	public void setStudentFieldKindTree(StudentFieldKindTree studentFieldKindTree) {
		this.studentFieldKindTree = studentFieldKindTree;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

}