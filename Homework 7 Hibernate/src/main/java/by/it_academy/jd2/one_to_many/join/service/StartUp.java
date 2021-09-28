package by.it_academy.jd2.one_to_many.join.service;

import by.it_academy.jd2.one_to_many.table.model.Department;
import by.it_academy.jd2.one_to_many.table.model.Employee;
import org.hibernate.Session;

public class StartUp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        session.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
