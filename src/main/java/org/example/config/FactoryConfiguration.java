package org.example.config;

import org.example.entity.Book;
import org.example.entity.Branch;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;
    //  private Connection connection;

    private FactoryConfiguration(){
        Configuration configuration=new Configuration()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Branch.class);
        sessionFactory= configuration.buildSessionFactory();

    }
    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null)?factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
