package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.Branchdto;
import org.example.dto.Userdto;
import org.example.entity.User;

import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(Userdto dto) throws Exception;
    boolean updateUser(Userdto dto) throws Exception;
    boolean deleteUser(String id) throws Exception;
    Userdto searchUser(String id) throws Exception;

    List<Userdto> getAllUser() throws Exception;
    Userdto searchByEmail(String mail) throws Exception;
}
