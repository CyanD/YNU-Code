package com.model.addressbook;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * AddressbookPublic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "addressbook_public", catalog = "umobile")
public class AddressbookPublicTree implements java.io.Serializable {

	// Fields

	private Long id;
	private Long pid;
	private String iconCls;
	private String text;

	private Set<AddressbookPublicTree> children = new HashSet<AddressbookPublicTree>(
			0);

	public AddressbookPublicTree() {
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Set<AddressbookPublicTree> getChildren() {
		return children;
	}

	@Transient
	public String getIconCls() {
		return iconCls;
	}
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	public Long getPid() {
		return this.pid;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getText() {
		return text;
	}

	public void setChildren(Set<AddressbookPublicTree> children) {
		this.children = children;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setPid(Long pid) {
		this.pid = null;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

	
	



	

}