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

import java.util.ArrayList;
import java.util.List;

public class BorrowBOimpl implements BorrowBO {

    BorrowDAO borrowDAO= (BorrowDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BORROW);
    BookDAO bookDAO= (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BOOK);
    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);
    @Override
    public boolean saveBorrow(BorrowDto dto) throws Exception {
        Book search = bookDAO.search(dto.getBookId());
        System.out.println(dto.getBookId());
        User search1 = userDAO.search(dto.getUserId());
        System.out.println(dto.getUserId());
        Borrow borrow=new Borrow(dto.getBorrowId(),dto.getBorrowDate(),dto.getDueDate(),search1,search);
        return borrowDAO.save(borrow);
      /*  Book search = bookDAO.search(dto.getBookId());
        User search1 = userDAO.search(dto.getUserId());*/

    }

    @Override
    public boolean updateBorrow(BorrowDto dto) throws Exception {
      User search=userDAO.search(dto.getUserId());
        System.out.println(dto.getUserId());
      Book search1=bookDAO.search(dto.getBookId());
        System.out.println(dto.getBookId());
        return borrowDAO.update(new Borrow(dto.getBorrowId(),dto.getBorrowDate(),dto.getDueDate(),search,search1));
    }

    @Override
    public boolean deleteBorrow(String id) throws Exception {
       return  borrowDAO.delete(id);
    }

    @Override
    public BorrowDto searchBorrow(String id) throws Exception {
        Borrow search = borrowDAO.search(id);
      return new BorrowDto(search.getBorrowId(),search.getBorrowDate(),search.getDueDate(),search.getUser().getU_id(),search.getBook().getBookId());
    }

    @Override
    public List<BorrowDto> getAllBorrow() throws Exception {
       List<Borrow> all= borrowDAO.getAll();
       List<BorrowDto> list=new ArrayList<>();
       for (Borrow borrow : all) {
           list.add(new BorrowDto(borrow.getBorrowId(),borrow.getBorrowDate(),borrow.getDueDate(),borrow.getUser().getU_id(),borrow.getBook().getBookId()));
       }
       return list;
    }
}
