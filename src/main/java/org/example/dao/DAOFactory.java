package org.example.dao;

import org.example.bo.impl.UserBOimpl;
import org.example.dao.custom.BookDAO;
import org.example.dao.impl.BookDAOimpl;
import org.example.dao.impl.BorrowDAOimpl;
import org.example.dao.impl.BranchDAOimpl;
import org.example.dao.impl.UserDAOimpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType{
        USER,BOOK,BRANCH,BORROW
    }
    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case USER:
                return new UserDAOimpl();
            case BOOK:
                return new BookDAOimpl();
            case BRANCH:
                return new BranchDAOimpl();
            case BORROW:
                return new BorrowDAOimpl();
            default:
                return null;
        }
    }
}
