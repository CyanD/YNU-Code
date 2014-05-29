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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * AddressbookDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="addressbook_dept"
    ,catalog="umobile"
)

public class AddressbookDept  implements java.io.Serializable {


    // Fields    

     private Long id;
     private AddressbookDept addressbookDept;
     private String name;
     private String remark;
     private Set<AddressbookDept> addressbookDepts = new HashSet<AddressbookDept>(0);


    // Constructors

    /** default constructor */
    public AddressbookDept() {
    }

	/** minimal constructor */
    public AddressbookDept(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public AddressbookDept(AddressbookDept addressbookDept, String name, String remark, Set<AddressbookDept> addressbookDepts) {
        this.addressbookDept = addressbookDept;
        this.name = name;
        this.remark = remark;
        this.addressbookDepts = addressbookDepts;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="pid")

    public AddressbookDept getAddressbookDept() {
        return this.addressbookDept;
    }
    
    public void setAddressbookDept(AddressbookDept addressbookDept) {
        this.addressbookDept = addressbookDept;
    }
    
    @Column(name="name", nullable=false, length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="remark", length=50)

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="addressbookDept")

    public Set<AddressbookDept> getAddressbookDepts() {
        return this.addressbookDepts;
    }
    
    public void setAddressbookDepts(Set<AddressbookDept> addressbookDepts) {
        this.addressbookDepts = addressbookDepts;
    }
}