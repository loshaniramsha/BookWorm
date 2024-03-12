package org.example.dao.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BookDAO;
import org.example.dao.custom.BorrowDAO;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowDAOimpl implements BorrowDAO {
    @Override
    public boolean save(Borrow dto) throws Exception {
    Session session=FactoryConfiguration.getInstance().getSession();
    Transaction transaction= session.beginTransaction();

   // Borrow borrow=new Borrow(dto.getBorrowId(),dto.getBorrowDate(),dto.getDueDate(),dto.getUser(),dto.getBook());
    session.persist(dto);

    transaction.commit();
    session.close();
    return  true;
    }

    @Override
    public boolean update(Borrow dto) throws Exception {
       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction= session.beginTransaction();

       Borrow borrow=new Borrow(dto.getBorrowId(),dto.getBorrowDate(),dto.getDueDate(),dto.getUser(),dto.getBook());
       session.update(borrow);

       transaction.commit();
       session.close();
       return  true;
    }

    @Override
    public boolean delete(String id) throws Exception {
       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction= session.beginTransaction();

       Borrow borrow= session.get(Borrow.class,id);
       session.remove(borrow);

       transaction.commit();
       session.close();
       return  true;
    }

    @Override
    public Borrow search(String id) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        Borrow borrow = session.get(Borrow.class, id);
        transaction.commit();
        session.close();
        return borrow;
    }

    @Override
    public List<Borrow> getAll() throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        List<Borrow> borrowList=session.createQuery("from Borrow ").list();
        transaction.commit();
        session.close();
        return borrowList;
    }
}
