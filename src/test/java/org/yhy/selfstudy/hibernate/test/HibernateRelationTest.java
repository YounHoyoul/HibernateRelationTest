package org.yhy.selfstudy.hibernate.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yhy.selfstudy.hibernate.Department;
import org.yhy.selfstudy.hibernate.Employee;
import org.yhy.selfstudy.hibernate.EmployeeDetail;
import org.yhy.selfstudy.hibernate.HibernateUtil;
import org.yhy.selfstudy.hibernate.Meeting;
import org.yhy.selfstudy.hibernate.Owner;
import org.yhy.selfstudy.hibernate.Person;

public class HibernateRelationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		assertNotNull(sessionFactory);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testRelation(){
		//fail("Not yet implemented");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List empList = session.createQuery("from Employee").list();
		List empDetailList = session.createQuery("from EmployeeDetail").list();
		
		assertEquals(0,empList.size());
		assertEquals(0,empDetailList.size());
		
		Department department = new Department();
        department.setDepartmentName("Sales");
		
		EmployeeDetail employeeDetail = new EmployeeDetail("10th Street", "LA", "San Francisco", "U.S.");
        Employee employee1 = new Employee("Nina", "Mayers", new Date(121212),"114-857-965");
        employee1.setEmployeeDetail(employeeDetail);
        employeeDetail.setEmployee(employee1);
		
        EmployeeDetail employeeDetail2 = new EmployeeDetail("210th Street", "LA2", "San Francisco2", "U.S.2");
        Employee employee2 = new Employee("Larry", "Page", new Date(121212),"2114-857-965");
        employee2.setEmployeeDetail(employeeDetail2);
        employeeDetail2.setEmployee(employee2);
        
        Employee employee3 = new Employee("Marrisa", "Mayer", new Date(121212),"2114-857-965");
        Employee employee4 = new Employee("Matt", "Cutts", new Date(121212),"2114-857-965");
        
        Meeting meeting1 = new Meeting("Quaterly Sales meeting");
        Meeting meeting2 = new Meeting("Weekly Status meeting");
        
        employee1.getMeetings().add(meeting1);
        employee1.getMeetings().add(meeting2);
        employee2.getMeetings().add(meeting1);
        
        department.getEmployees().add(employee1);
        department.getEmployees().add(employee2);
        employee1.setDepartment(department);
        employee2.setDepartment(department);
        
        Employee manager1 = new Employee("Chuck", "Norris", new Date(121212),"114-857-965");
        manager1.getSubordinates().add(employee1);
        manager1.getSubordinates().add(employee2);
        
        employee1.setManager(manager1);
        employee2.setManager(manager1);
        
        employee1.getColleagues().add(employee3);
        employee1.getColleagues().add(employee4);
        employee2.getColleagues().add(employee4);
        employee3.getColleagues().add(employee4);
        employee4.getColleagues().add(employee1);
        employee4.getColleagues().add(employee3);
        
        Owner owner = new Owner("Bill", "Gates", 300L, 20L);
        
        Person person1 = new Person("TestUserFirstName1","TestUserLastName1");
        Person person2 = new Person("TestUserFirstName2","TestUserLastName2");
        
        session.beginTransaction();
        
        session.save(person1);
        session.save(person2);
        session.save(department);
        session.save(manager1);
        session.save(employee1);
        session.save(employee2);
        session.save(employee3);
        session.save(employee4);
        session.save(owner);
        
        List<Employee> employees = session.createQuery("from Employee").list();
        for (Employee emp : employees) {
            System.out.println(emp.getFirstname() + " , "
                    + emp.getLastname() + ", "
                    + emp.getEmployeeDetail().getState());
        }
 
        session.getTransaction().commit();
        session.close();
	}

}
