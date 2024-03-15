package org.example.bo;

import org.example.bo.impl.*;
import org.example.dao.DAOFactory;
import org.example.dao.SuperDAO;
import org.example.dao.impl.BookDAOimpl;
import org.example.dao.impl.BranchDAOimpl;
import org.example.dao.impl.UserDAOimpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
   public static BoFactory getBoFactory(){
        return (boFactory==null)?boFactory=new BoFactory():boFactory;
    }
    public enum BOType{
        USER,BOOK,BRANCH,BORROW,BOOK2
    }
    public SuperBO getBO(BOType boType){
        switch (boType){
            case USER:
                return new UserBOimpl();
            case BOOK:
                return new BookBOimpl();
            case BRANCH:
                return new BranchBOimpl();
                case BORROW:
                    return new BorrowBOimpl();
                case BOOK2:
                    return new Book2BOimpl();
            default:
                return null;
        }
    }
}
