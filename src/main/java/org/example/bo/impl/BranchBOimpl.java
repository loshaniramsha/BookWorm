package org.example.bo.impl;

import org.example.bo.custom.BranchBO;
import org.example.config.FactoryConfiguration;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BranchDAO;
import org.example.dto.Branchdto;
import org.example.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBOimpl implements BranchBO {
    BranchDAO branchDAO= (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BRANCH);
    @Override
    public boolean saveBranch(Branchdto dto) throws Exception {
       return branchDAO.save(new Branch(dto.getBranchId(),dto.getLocation(),null));

    }

    @Override
    public boolean updateBranch(Branchdto dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteBranch(String id) throws Exception {
        return false;
    }

    @Override
    public Branchdto searchBranch(String id) throws Exception {
        return null;
    }

    @Override
    public List<Branchdto> getAllBranch() throws Exception {
        List<Branch> all= branchDAO.getAll();
        List<Branchdto> list=new ArrayList<>();
        for (Branch branch : all) {
            list.add(new Branchdto(branch.getBranchId(),branch.getLocation()));
        }
        return list;
    }
}
