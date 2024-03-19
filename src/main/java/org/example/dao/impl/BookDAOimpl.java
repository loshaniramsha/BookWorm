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
   /* public Book search(String title) throws Exception {*/
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        Book book = session.get(Book.class, id);
       /* Book book = session.get(Book.class, title);*/
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

    @Override
    public String generateNextId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Corrected the query to use OFFSET instead of LIMIT for HQL
        Object object = session.createQuery("SELECT bookId FROM Book ORDER BY bookId DESC")
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResult();

        transaction.commit();
        session.close();

        if (object != null) {
            String currentId = object.toString();
            String[] split = currentId.split("B0");

            int id = Integer.parseInt(split[1]) + 1; // Increment the ID
            return "B" + String.format("%03d", id); // Format the ID with leading zeros
        } else {
            return "B001"; // If no previous ID found, return the default ID
        }
    }
}
