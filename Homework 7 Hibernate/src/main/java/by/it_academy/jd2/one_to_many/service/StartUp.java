package by.it_academy.jd2.one_to_many.service;

import by.it_academy.jd2.one_to_many.model.Department;
import by.it_academy.jd2.one_to_many.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Department department = new Department();
        department.setName("Отдел продаж");

        session.save(department);

        Employee employee = new Employee();
        employee.setName("COOL");
        employee.setSalary(5);
        employee.setDepartment(department);

        session.save(employee);

        session.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
