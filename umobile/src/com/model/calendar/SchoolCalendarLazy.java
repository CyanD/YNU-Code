package com.model.calendar;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusRoute entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "school_calendar", catalog = "umobile")
public class SchoolCalendarLazy implements java.io.Serializable {

	// Fields

		private Long id;
		private Long cid;
		private String acttime;
		private String createTime;
		private String publisher;
		
		// Constructors

		/** default constructor */
		public SchoolCalendarLazy() {
		}

		/** minimal constructor */
		public SchoolCalendarLazy(String createTime, String publisher) {
			this.createTime = createTime;
			this.publisher = publisher;
		}

		/** full constructor */
		public SchoolCalendarLazy(String acttime, String createTime,
				String publisher) {
			this.acttime = acttime;
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


		@Column(name = "acttime", length = 100)
		public String getActtime() {
			return this.acttime;
		}

		public void setActtime(String acttime) {
			this.acttime = acttime;
		}

		@Column(name = "createTime", nullable = false, length = 19)
		public String getCreateTime() {
			return this.createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}


		@Column(name = "publisher", nullable = false, length = 100)
		public String getPublisher() {
			return this.publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		@Column(name = "cid", nullable = false)
		public Long getCid() {
			return cid;
		}

		public void setCid(Long cid) {
			this.cid = cid;
		}


}