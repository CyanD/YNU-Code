package com.model.calendar;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "calendar_category", catalog = "umobile")
public class CalendarCategory implements java.io.Serializable{
	// Fields
	private Long id;
	private String title;
	private String beginDate;
	private String weeknum;
	private String createTime;
	private String publisher;
	private Set<SchoolCalendar> schoolCalendars = new HashSet<SchoolCalendar>(0);
	// Constructors
	
	/** default constructor */
	public CalendarCategory() {
		
	}

	/** minimal constructor */
	public CalendarCategory(String createTime, String publisher) {
		this.createTime = createTime;
		this.publisher = publisher;
	}

	/** full constructor */
	public CalendarCategory(String title, String beginDate,String weeknum,String createTime,
			String publisher, Set<SchoolCalendar> schoolCalendars) {
		this.title = title;
		this.beginDate = beginDate;
		this.weeknum = weeknum;
		this.createTime = createTime;
		this.publisher = publisher;
		this.schoolCalendars = schoolCalendars;
	}
	
	// Property accessors
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
	
	@Column(name = "weeknum", length = 20)
	public String getWeeknum() {
		return weeknum;
	}

	public void setWeeknum(String weeknum) {
		this.weeknum = weeknum;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "calendarCategory")
	public Set<SchoolCalendar> getSchoolCalendars() {
		return schoolCalendars;
	}

	public void setSchoolCalendars(Set<SchoolCalendar> schoolCalendars) {
		this.schoolCalendars = schoolCalendars;
	}
	

}
