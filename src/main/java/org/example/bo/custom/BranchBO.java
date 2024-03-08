package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.Branchdto;

import java.util.List;

public interface BranchBO extends SuperBO {
    boolean saveBranch(Branchdto dto) throws Exception;
    boolean updateBranch(Branchdto dto) throws Exception;
    boolean deleteBranch(String id) throws Exception;
    Branchdto searchBranch(String id) throws Exception;

    List<Branchdto> getAllBranch() throws Exception;
}
