package com.model.addressbook.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AddressbookDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="addressbook_dept"
    ,catalog="umobile"
)

public class AddressbookDeptTreeGrid  implements java.io.Serializable {


    // Fields    

     public AddressbookDeptTreeGrid(Long id, Long _parentId, String name,
			String remark, String isSystem,Integer orders) {
		super();
		this.id = id;
		if(_parentId==0l)_parentId=null;
		this._parentId = _parentId;
		this.name = name;
		this.remark = remark;
		this.isSystem = isSystem;
		this.orders=orders;
	}


	private Long id;

	private Long _parentId;
    private String name;
    private String remark;
    private Integer orders;
	@Column(name = "orders")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	private String createTime;
	private String publisher;
	private String isSystem;
	/** default constructor */
    public AddressbookDeptTreeGrid() {
    }

	@Column(name="pid")
     public Long get_parentId() {
		return _parentId;
	}
	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	// Property accessors
    @Id @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }

	@Column(name = "isSystem", length = 2)
	public String getIsSystem() {
		return this.isSystem;
	}

	@Column(name="name", nullable=false, length=100)

    public String getName() {
        return this.name;
    }


	@Column(name = "publisher", nullable = false, length = 20)
	public String getPublisher() {
		return this.publisher;
	}

    @Column(name="remark", length=50)

    public String getRemark() {
        return this.remark;
    }

	public void set_parentId(Long _parentId) {
		if(_parentId==null)_parentId=0l;
		this._parentId = _parentId;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
    public void setId(Long id) {
        this.id = id;
    }
    public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}