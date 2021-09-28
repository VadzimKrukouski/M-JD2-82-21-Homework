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
        session.save(department1);

        Employee employee1 = new Employee();
        employee1.setName("One");
        employee1.setSalary(8);




        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
