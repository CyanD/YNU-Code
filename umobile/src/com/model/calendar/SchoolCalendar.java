package com.model.calendar;

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
 * BusRoute entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "school_calendar", catalog = "umobile")
public class SchoolCalendar implements java.io.Serializable {

	// Fields
	private Long id;
	private CalendarCategory calendarCategory;
	private String acttime;
	private String createTime;
	private String publisher;
	private Set<SchoolCalendarSub> schoolCalendarSubs = new HashSet<SchoolCalendarSub>(0);
	// Constructors

	/** default constructor */
	public SchoolCalendar() {
		
	}

	/** minimal constructor */
	public SchoolCalendar(CalendarCategory calendarCategory,String createTime, String publisher) {
		this.calendarCategory = calendarCategory;
		this.createTime = createTime;
		this.publisher = publisher;
	}

	/** full constructor */
	public SchoolCalendar(CalendarCategory calendarCategory,String acttime, String createTime,
			String publisher, Set<SchoolCalendarSub> schoolCalendarSubs) {
		this.calendarCategory = calendarCategory;
		this.acttime = acttime;
		this.createTime = createTime;
		this.publisher = publisher;
		this.schoolCalendarSubs = schoolCalendarSubs;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolCalendar")
	public Set<SchoolCalendarSub> getSchoolCalendarSubs() {
		return this.schoolCalendarSubs;
	}

	public void setSchoolCalendarSubs(Set<SchoolCalendarSub> schoolCalendarSubs) {
		this.schoolCalendarSubs = schoolCalendarSubs;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid", nullable = false)
	public CalendarCategory getCalendarCategory() {
		return calendarCategory;
	}

	public void setCalendarCategory(CalendarCategory calendarCategory) {
		this.calendarCategory = calendarCategory;
	}
	
	
}