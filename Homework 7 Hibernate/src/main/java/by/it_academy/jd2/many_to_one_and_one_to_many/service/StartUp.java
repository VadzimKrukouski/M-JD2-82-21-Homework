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

        Employee employee1 = new Employee();
        employee1.setName("Rich");
        employee1.setSalary(8);

        Employee employee2 = new Employee();
        employee2.setName("Matt");
        employee2.setSalary(78);

        department.addEmployee(employee1);
        department.addEmployee(employee2);

        session.save(department);
        session.getTransaction().commit();

        HibernateUtil.shutdown();
    }
}
