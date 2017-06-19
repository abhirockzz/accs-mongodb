package com.oracle.cloud.acc.mongodb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EMPLOYEES")
@XmlRootElement

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private Integer empId; 
    
    @Column(name = "NAME")
    @Basic(optional = false)
    private String name;
    
    @Column(name = "EMAIL")
    @Basic(optional = false)
    private String email;
    
    public Employee() {
    }

    public Employee(Integer empId, String fullName, String email) {
        this.empId = empId;
        this.name = fullName;
        this.email = email;
    }

    
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setFullName(String fullName) {
        this.name = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" + "empId=" + empId + ", fullName=" + name + ", email=" + email + '}';
    }

}
