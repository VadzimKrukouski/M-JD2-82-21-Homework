package by.it_academy.jd2.many_to_many.service;

import by.it_academy.jd2.many_to_many.model.Department;
import by.it_academy.jd2.many_to_many.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Department department1 = new Department();
        department1.setName("Управление");

        Department department2 = new Department();
        department2.setName("Бухгалтерия");

        Employee employee1 = new Employee();
        employee1.setName("One");
        employee1.setSalary(8);

        Employee employee2 = new Employee();
        employee2.setName("Two");
        employee2.setSalary(878);

        employee1.addDepartment(department1);
        employee1.addDepartment(department2);

        employee2.addDepartment(department1);

        session.save(employee1);
        session.save(employee2);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
