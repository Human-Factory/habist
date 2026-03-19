package com.hab.hobbymarket.service;

import com.hab.hobbymarket.dao.WishlistDAO;
import com.hab.hobbymarket.model.Wishlist;

import java.util.List;

public class WishlistService {


    // DAO 객체 생성
     private final WishlistDAO wishlistDAO = new WishlistDAO();
     // 서비스가 DAO 호출해서 결과 넘겨주기
     public List<Wishlist> findByMemberId(int memberId) {

         // DAO 호출해서 관심목록 조회
         return wishlistDAO.findByMemberId(memberId);

     }
}
