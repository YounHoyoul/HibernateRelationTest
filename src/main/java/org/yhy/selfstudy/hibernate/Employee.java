package org.yhy.selfstudy.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
//@DiscriminatorValue(value="E")
@PrimaryKeyJoinColumn(name="PERSON_ID")
public class Employee extends Person {    
	@Column(name="BIRTH_DATE")
    private Date birthDate;
     
	@Column(name="CELL_PHONE")
    private String cellphone;
    
	//One To One
	@OneToOne(mappedBy="employee", cascade=CascadeType.ALL)
    private EmployeeDetail employeeDetail;
    
	//Many To One
	@ManyToOne
    @JoinColumn(name="DEPT_ID")	
    private Department department;
    
	//Many To Many
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="EMPLOYEE_MEETING", 
               joinColumns={@JoinColumn(name="PERSON_ID")}, 
               inverseJoinColumns={@JoinColumn(name="MEETING_ID")})
    private Set<Meeting> meetings = new HashSet<Meeting>();
    
	//Self One To Many
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="MANAGER_ID")
    private Employee manager;

	@OneToMany(mappedBy="manager")
    private Set<Employee> subordinates = new HashSet<Employee>();
	
	//Self Many to Many
	@ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="EMPLOYEE_COLLEAGUE",
        joinColumns={@JoinColumn(name="PERSON_ID")},
        inverseJoinColumns={@JoinColumn(name="COLLEAGUE_ID")})
    private Set<Employee> colleagues = new HashSet<Employee>();
 
    @ManyToMany(mappedBy="colleagues")
    private Set<Employee> teammates = new HashSet<Employee>();
	
    public Employee(){
    	
    }
    
    public Employee(String firstname, String lastname, Date birthdate, String phone) {
        //this.firstname = firstname;
        //this.lastname = lastname;
    	super(firstname,lastname);
        this.birthDate = birthdate;
        this.cellphone = phone;
         
    }

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public EmployeeDetail getEmployeeDetail() {
		return employeeDetail;
	}

	public void setEmployeeDetail(EmployeeDetail employeeDetail) {
		this.employeeDetail = employeeDetail;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Set<Employee> getColleagues() {
		return colleagues;
	}

	public void setColleagues(Set<Employee> colleagues) {
		this.colleagues = colleagues;
	}

	public Set<Employee> getTeammates() {
		return teammates;
	}

	public void setTeammates(Set<Employee> teammates) {
		this.teammates = teammates;
	}
    
    
}
