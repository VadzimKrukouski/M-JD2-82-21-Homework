package by.it_academy.jd2.many_to_one_and_one_to_many.service;

import by.it_academy.jd2.many_to_one_and_one_to_many.model.Department;
import by.it_academy.jd2.many_to_one_and_one_to_many.model.Employee;
import org.hibernate.Session;

import java.util.List;

public class StartUp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Department department = new Department();
        department.setName("Financial");

        Employee employee = new Employee();
        employee.setName("Rich");
        employee.setSalary(8);

        department.addEmployee(employee);

        session.save(department);
        session.getTransaction().commit();

        List<Employee> employeeList = department.getEmployeeList();
        for (Employee employee1 : employeeList) {
            System.out.println(employee1.toString());
        }

        HibernateUtil.shutdown();


    }
}
