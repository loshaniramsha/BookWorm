package org.example.dao.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BranchDAO;
import org.example.entity.Book;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.util.List;

public class BranchDAOimpl implements BranchDAO {
    @Override
    public boolean save(Branch dto) throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        Branch branch=new Branch(dto.getBranchId(),dto.getLocation(),null);
        session.persist(branch);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Branch dto) throws Exception {
       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction= session.beginTransaction();

       Branch branch=new Branch(dto.getBranchId(),dto.getLocation(),null);
       session.update(branch);

       transaction.commit();
       session.close();
       return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        session.remove(session.get(Branch.class,id));

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Branch search(String id) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        Branch branch = session.get(Branch.class, id);
        transaction.commit();
        session.close();
        return branch;
    }

    @Override
    public List<Branch> getAll() throws Exception {
       Session session= FactoryConfiguration.getInstance().getSession();
       Transaction transaction= session.beginTransaction();
       List<Branch> branchList= session.createQuery("from Branch").list();
       transaction.commit();
       session.close();
       return branchList;
    }
}
