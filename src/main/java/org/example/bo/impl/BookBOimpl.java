package org.example.bo.impl;

import org.example.bo.custom.BookBO;
import org.example.bo.custom.BranchBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BookDAO;
import org.example.dao.custom.BranchDAO;
import org.example.dto.Bookdto;
import org.example.dto.Branchdto;
import org.example.entity.Book;
import org.example.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BookBOimpl implements BookBO {

    BookDAO bookDAO= (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BOOK);
    BranchDAO branchDAO= (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BRANCH);

    @Override
    public boolean saveBook(Bookdto dto) throws Exception {
        Branch search = branchDAO.search(dto.getBranchId());
        System.out.println("branch id"+dto.getBranchId());
        return bookDAO.save(new Book(dto.getBookId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getStatus(), search,null));
    }

    @Override
    public boolean updateBook(Bookdto dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteBook(String id) throws Exception {
        return false;
    }

    @Override
    public Bookdto searchBook(String id) throws Exception {
        return null;
    }

    @Override
    public List<Bookdto> getAllBook() throws Exception {
     List<Book> all= bookDAO.getAll();
     if (all != null) {
         List<Bookdto> list=new ArrayList<>();
         for (Book book : all) {
             list.add(new Bookdto(book.getBookId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getStatus(),book.getBranch().getBranchId()));
         }
         return list;
     } else {
         return null;
     }

 }
}
