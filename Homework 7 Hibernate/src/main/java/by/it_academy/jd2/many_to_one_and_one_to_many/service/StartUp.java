package by.it_academy.jd2.many_to_one_and_one_to_many.service;

import by.it_academy.jd2.many_to_one_and_one_to_many.model.Department;
import by.it_academy.jd2.many_to_one_and_one_to_many.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Department department = new Department();
        department.setName("Financial");

        session.save(department);

        Employee employee = new Employee();
        employee.setName("Rich");
        employee.setSalary(8);
        employee.setDepartment(department);

        department.addEmployee(employee);

        session.save(employee);
        session.getTransaction().commit();

        HibernateUtil.shutdown();


    }
}
