package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.BorrowDto;
import org.example.dto.Branchdto;

import java.util.List;

public interface BorrowBO extends SuperBO {
    boolean saveBorrow(BorrowDto dto) throws Exception;
    boolean updateBorrow(BorrowDto dto) throws Exception;
    boolean deleteBorrow(String id) throws Exception;
    BorrowDto searchBorrow(String id) throws Exception;

    List<BorrowDto> getAllBorrow() throws Exception;
}
