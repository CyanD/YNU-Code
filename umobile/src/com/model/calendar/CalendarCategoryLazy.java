package com.model.calendar;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calendar_category", catalog = "umobile")
public class CalendarCategoryLazy implements java.io.Serializable {
	// Fields
		private Long id;
		private String title;
		private String beginDate;
		private String weeknum;
		private String createTime;
		private String publisher;
		
		// Constructors
		
		/** default constructor */
		public CalendarCategoryLazy() {
			
		}

		/** minimal constructor */
		public CalendarCategoryLazy(String createTime, String publisher) {
			this.createTime = createTime;
			this.publisher = publisher;
		}

		/** full constructor */
		public CalendarCategoryLazy(String title, String beginDate,String weeknum,String createTime,
				String publisher) {
			this.title = title;
			this.beginDate = beginDate;
			this.weeknum = weeknum;
			this.createTime = createTime;
			this.publisher = publisher;
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
		@Column(name = "title", length = 100)
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		@Column(name = "beginDate", length = 100)
		public String getBeginDate() {
			return beginDate;
		}

		public void setBeginDate(String beginDate) {
			this.beginDate = beginDate;
		}
		@Column(name = "weeknum", length = 20)
		public String getWeeknum() {
			return weeknum;
		}

		public void setWeeknum(String weeknum) {
			this.weeknum = weeknum;
		}
		@Column(name = "createTime", nullable = false, length = 19)
		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		
		@Column(name = "publisher", nullable = false, length = 100)
		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		
		
}
