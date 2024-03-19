package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.Bookdto;
import org.example.dto.BorrowDto;

import java.util.List;

public interface BookBO extends SuperBO {
boolean saveBook(Bookdto dto) throws Exception;
boolean updateBook(Bookdto dto) throws Exception;
boolean deleteBook(String id) throws Exception;
Bookdto searchBook(String id) throws Exception;
/*Bookdto searchBook(String title) throws Exception;*/
    List<Bookdto> getAllBook() throws Exception;
    String generateNextId() throws Exception;
}
