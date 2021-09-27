package by.it_academy.jd2.one_to_many.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory==null){
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure("hibernate2.cfg.xml").build();
                Metadata metadata = new MetadataSources(serviceRegistry)
                        .getMetadataBuilder()
                        .build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        } catch (Throwable e) {
            throw  new  ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}
