package org.example.dao.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.Branch;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOimpl implements UserDAO {
    @Override
    public boolean save(User dto) throws Exception  {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        User user=new User(dto.getU_id(),dto.getU_name(),dto.getEmail(),dto.getPassword(),dto.getStatus(),null,null);
        session.persist(user);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User dto) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        User user=new User(dto.getU_id(), dto.getU_name(), dto.getEmail(), dto.getPassword(),dto.getStatus(), null, null);
        session.update(user);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction=session.beginTransaction();

       session.remove(session.get(User.class,id));
       transaction.commit();
       session.close();
       return true;
    }

    @Override
    public User search(String id) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        User user=session.get(User.class,id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        List<User> userList=session.createQuery("from User").list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public User searchByEmail(String mail) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        User user= (User) session.createQuery("from User where email = :email").setParameter("email", mail).uniqueResult();

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public String generateNextId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.createQuery("SELECT u_id FROM User ORDER BY u_id DESC LIMIT 1").uniqueResult();
        transaction.commit();
        session.close();

        if(object != null) {
            String CurrentId = object.toString();
            String[] split = CurrentId.split("U0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "U00" + id;
            } else {
                return "U0" + id;
            }
        } else {
            return "U001";
        }
    }
}
