package org.example.dao.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BookDAO;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookDAOimpl implements BookDAO {
    @Override
    public boolean save(Book dto) throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        Book book=new Book(dto.getBookId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getStatus(),dto.getBranch(),null,  null);
        session.persist(book);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Book dto) throws Exception {
       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction= session.beginTransaction();

       Book book=new Book(dto.getBookId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getStatus(),dto.getBranch(),null,  null);
       session.update(book);

       transaction.commit();
       session.close();
       return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        session.remove(session.get(Book.class,id));

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Book search(String id) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        Book book = session.get(Book.class, id);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public List<Book> getAll() throws Exception {
       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction= session.beginTransaction();

       List<Book> bookList=session.createQuery("from Book ").list();
       transaction.commit();
       session.close();
       return bookList;
    }
}
