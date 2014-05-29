package com.model.calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "versions", catalog = "umobile")
public class Versions implements java.io.Serializable{
	// Fields
	private Long id;
	private String version_category;
	private Long versionnumber;
	/** default constructor */
	public Versions() {
		
	}

	/** minimal constructor */
	public Versions(Long versionnumber) {
		this.versionnumber = versionnumber;
	}

	/** full constructor */
	public Versions(String version_category,Long versionnumber) {
		this.version_category = version_category;
		this.versionnumber = versionnumber;
	}
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "version_category", nullable = false, length = 10)
	public String getVersion_category() {
		return version_category;
	}

	public void setVersion_category(String version_category) {
		this.version_category = version_category;
	}

	@Column(name = "versionnumber", nullable = false, length = 20)
	public Long getVersionnumber() {
		return versionnumber;
	}

	public void setVersionnumber(Long versionnumber) {
		this.versionnumber = versionnumber;
	}
	
}
