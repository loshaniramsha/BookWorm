package org.example.dao;

import org.example.entity.Branch;

import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    boolean save(T dto) throws Exception;
    boolean update(T dto) throws Exception;
    boolean delete(String id) throws Exception;
    T search(String id) throws Exception;

    List<T> getAll() throws Exception;
}
