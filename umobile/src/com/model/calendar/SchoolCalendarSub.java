package com.model.calendar;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "school_calendar_sub", catalog = "umobile")
public class SchoolCalendarSub implements java.io.Serializable {

	// Fields

		private Long id;
		private SchoolCalendar schoolCalendar;
		private String title;
		private String category;
		private String createTime;
		private String publisher;

		// Constructors

		/** default constructor */
		public SchoolCalendarSub() {
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

		/** minimal constructor */
		public SchoolCalendarSub(SchoolCalendar schoolCalendar,
				String createTime, String publisher) {
			this.schoolCalendar = schoolCalendar;
			this.createTime = createTime;
			this.publisher = publisher;
		}

		/** full constructor */
		public SchoolCalendarSub(SchoolCalendar schoolCalendar, String title,
				String createTime, String publisher) {
			this.schoolCalendar = schoolCalendar;
			this.title = title;
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

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "pid", nullable = false)
		public SchoolCalendar getSchoolCalendar() {
			return this.schoolCalendar;
		}

		public void setSchoolCalendar(SchoolCalendar schoolCalendar) {
			this.schoolCalendar = schoolCalendar;
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
}