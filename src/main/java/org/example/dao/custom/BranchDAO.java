package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Branch;

public interface BranchDAO extends CrudDAO<Branch> {
    String generateNextId() throws Exception;
}
