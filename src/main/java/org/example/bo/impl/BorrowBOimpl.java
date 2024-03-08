package org.example.bo.impl;

import org.example.bo.custom.BorrowBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BookDAO;
import org.example.dao.custom.BorrowDAO;
import org.example.dao.custom.UserDAO;
import org.example.dto.BorrowDto;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.entity.User;

import java.util.List;

public class BorrowBOimpl implements BorrowBO {

    BorrowDAO borrowDAO= (BorrowDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BORROW);
    BookDAO bookDAO= (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BOOK);
    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);
    @Override
    public boolean saveBorrow(BorrowDto dto) throws Exception {
      /*  Book search = bookDAO.search(dto.getBookId());
        User search1 = userDAO.search(dto.getUserId());*/
        return false;
    }

    @Override
    public boolean updateBorrow(BorrowDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteBorrow(String id) throws Exception {
        return false;
    }

    @Override
    public BorrowDto searchBorrow(String id) throws Exception {
        return null;
    }

    @Override
    public List<BorrowDto> getAllBorrow() throws Exception {
        return null;
    }
}
