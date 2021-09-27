package by.it_academy.jd2.one_to_one.service;

import by.it_academy.jd2.one_to_one.model.Department;
import by.it_academy.jd2.one_to_one.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Department department = new Department();
        department.setName("Бухгалтерия");

        sessionOne.save(department);

        Employee employee = new Employee();
        employee.setName("Сергей");
        employee.setSalary(585);
        employee.setDepartment(department);

        sessionOne.save(employee);

        System.out.println(employee.toString());

        sessionOne.getTransaction().commit();

        HibernateUtil.shutdown();


    }
}
