package by.it_academy.jd2.one_to_many.join.service;

import by.it_academy.jd2.one_to_many.join.model.Department;
import by.it_academy.jd2.one_to_many.join.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Department department = new Department();
        department.setName("Бухгалтерия");

        Employee employee1 = new Employee();
        employee1.setName("Vad");
        employee1.setSalary(89);

        Employee employee2 = new Employee();
        employee2.setName("Lick");
        employee2.setSalary(741);

        department.getEmployees().add(employee1);
        department.getEmployees().add(employee2);

        session.save(department);

        session.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
