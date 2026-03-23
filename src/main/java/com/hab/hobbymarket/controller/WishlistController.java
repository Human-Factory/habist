package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.model.Member;
import com.hab.hobbymarket.model.Wishlist;
import com.hab.hobbymarket.service.WishlistService;
import com.hab.hobbymarket.session.SessionManager;
import com.hab.hobbymarket.view.wishlistview.WishlistOutputView;

import java.util.List;

public class WishlistController {
    // 비즈니스 로직 담당
    private final WishlistService wishlistService;

    // 조회 결과를 출력하는 뷰
    private final WishlistOutputView wishlistOutputView = new WishlistOutputView();

    // 생성자 주입
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // --------------------------------------------------------------------
    // 1) 관심목록 추가
    // 영상/강의 상세에서 lectureId를 받아서 관심목록에 저장
    // --------------------------------------------------------------------
    public void addWishlist(int lectureId) {

        // 로그인 여부 먼저 확인
        // 로그인 안 한 상태면 관심목록 저장 불가
        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        // 현재 로그인한 사용자 정보 가져오기
        Member currentUser = SessionManager.getCurrentUser();
        // 현재 회원 번호 추출
        // 프로젝트의 memberId 타입에 따라 intValue() 사용
        int memberId = currentUser.getMemberId().intValue();

        // 서비스에 회원번호 + 강의번호 전달해서 저장
        boolean result = wishlistService.addWishlist(memberId, lectureId);

        // 결과 출력
        if (result) {
            System.out.println("관심목록에 추가되었습니다.");
        } else {
            System.out.println("이미 관심목록에 있는 강의이거나 추가에 실패했습니다.");
        }
    }

    // --------------------------------------------------------------------
    // 2) 내 관심목록 조회
    // --------------------------------------------------------------------
    public void showMyWishlist() {

        // 로그인 안 되어 있으면 접근 차단
        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        // 현재 로그인 회원 가져오기
        Member currentUser = SessionManager.getCurrentUser();

        // 회원 번호 추출
        int memberId = currentUser.getMemberId().intValue();

        // 서비스로 관심목록 조회 요청
        List<Wishlist> list = wishlistService.getWishlist(memberId);

        // 출력 뷰에 리스트 넘겨서 화면 표시
        wishlistOutputView.showWishlist(list);
    }

    // --------------------------------------------------------------------
    // 3) 관심목록 삭제
    // 사용자가 입력한 wishlistId를 기준으로 삭제
    // --------------------------------------------------------------------
    public void deleteWishlist(int wishlistId) {

        // 로그인 체크
        if (!SessionManager.isLoggedIn()) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        // 현재 로그인 회원 가져오기
        Member currentUser = SessionManager.getCurrentUser();

        // 회원 번호 추출
        int memberId = currentUser.getMemberId().intValue();

        // 삭제 요청
        // memberId도 같이 넘겨서 본인 데이터만 삭제 가능하게 만듦
        boolean result = wishlistService.removeWishlist(memberId, wishlistId);

        // 삭제 결과 메시지
        if (result) {
            System.out.println("관심목록 삭제 완료");
        } else {
            System.out.println("삭제할 관심목록이 없거나 본인 목록이 아닙니다.");
        }
    }
}
