package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Book;

public interface BookDAO extends CrudDAO<Book> {
    String generateNextId() throws Exception;
}
