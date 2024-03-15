package org.example.bo.impl;

import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;
import org.example.dto.Userdto;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOimpl implements UserBO {

    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);
    @Override
    public boolean saveUser(Userdto dto) throws Exception {
        return userDAO.save(new User(dto.getU_id(),dto.getU_name(),dto.getEmail(),dto.getPassword(),dto.getStatus(),null,null));
    }

    @Override
    public boolean updateUser(Userdto dto) throws Exception {
        return userDAO.update(new User(dto.getU_id(),dto.getU_name(),dto.getEmail(),dto.getPassword(), dto.getStatus(), null, null));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public Userdto searchUser(String id) throws Exception {
        User user=userDAO.search(id);
        return new Userdto(user.getU_id(), user.getU_name(), user.getEmail(), user.getPassword(), user.getStatus());
    }

    @Override
    public List<Userdto> getAllUser() throws Exception {
        List<User> all=userDAO.getAll();
        List<Userdto> list=new ArrayList<>();
        for (User user:all){
            list.add(new Userdto(user.getU_id(),user.getU_name(),user.getEmail(),user.getPassword(),user.getStatus()));
        }
        return list;
    }

    @Override
    public Userdto searchByEmail(String mail) throws Exception {
        User user=userDAO.searchByEmail(mail);
        if (user==null){
            return null;
        } else {
            return new Userdto(user.getU_id(),user.getU_name(),user.getEmail(),user.getPassword(),user.getStatus());
        }
    }
}
