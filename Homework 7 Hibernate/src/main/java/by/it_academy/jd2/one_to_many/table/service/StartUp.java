package by.it_academy.jd2.one_to_many.table.service;

import by.it_academy.jd2.one_to_many.table.model.Department;
import by.it_academy.jd2.one_to_many.table.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Department department = new Department();
        department.setName("Отдел продаж");

        Employee employee1 = new Employee();
        employee1.setName("COOL");
        employee1.setSalary(5);

        Employee employee2 = new Employee();
        employee2.setName("BAD");
        employee2.setSalary(9);

        department.getEmployees().add(employee1);
        department.getEmployees().add(employee2);

        session.save(department);

        session.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
