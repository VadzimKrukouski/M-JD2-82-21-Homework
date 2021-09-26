package by.it_academy.jd2.one_to_one.service;

import by.it_academy.jd2.one_to_one.model.Department;
import by.it_academy.jd2.one_to_one.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Department department = new Department();
        department.setName("Управление");

        sessionOne.save(department);

        Employee employee = new Employee();
        employee.setName("Vad");
        employee.setSalary(2225);
        employee.setDepartment(department);

        sessionOne.save(employee);

        sessionOne.getTransaction().commit();

        HibernateUtil.shutdown();

    }
}
