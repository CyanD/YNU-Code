package com.model.calendar;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * BusSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "school_calendar_sub", catalog = "umobile")
public class SchoolCalendarSubLazy implements java.io.Serializable {

	// Fields

		private Long id;
		private Long pid;
		private String title;
		private String category;
		private String createTime;
		private String publisher;

		// Constructors

		/** default constructor */
		public SchoolCalendarSubLazy() {
		}

		/** minimal constructor */
		public SchoolCalendarSubLazy(
				String createTime, String publisher) {
			
			this.createTime = createTime;
			this.publisher = publisher;
		}

		/** full constructor */
		public SchoolCalendarSubLazy(String title,String category,
				String createTime, String publisher) {
			this.title = title;
			this.category = category;
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


		@Column(name = "category", length = 20)
		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}
		
		@Column(name = "title", length = 100)
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Column(name = "createTime", nullable = false, length = 19)
		public String getCreateTime() {
			return this.createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		@Column(name = "publisher", nullable = false, length = 50)
		public String getPublisher() {
			return this.publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		
		
		@Column(name = "pid", nullable = false)
		public Long getPid() {
			return pid;
		}

		public void setPid(Long pid) {
			this.pid = pid;
		}
		
		
}