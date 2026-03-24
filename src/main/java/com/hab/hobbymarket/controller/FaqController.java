package com.hab.hobbymarket.controller;

import com.hab.hobbymarket.dao.FaqDAO;
import com.hab.hobbymarket.view.faqview.FaqInputView;
import com.hab.hobbymarket.view.faqview.FaqOutputView;

import java.util.List;

/**
 * <역할>
 *     FAQ 등록/목록/상세/수정/삭제 흐름을 제어하는 컨트롤러
 * <패턴>
 *     MVC + DAO 패턴
 * <흐름 구조>
 *     사용자 입력
 *     FaqInputView : 번호 / 질문 / 답변 / 카테고리 입력
 *     FaqController : 흐름 제어 ( 입력 검증 + DAO 호출 + 출력 연결 )
 *     FaqDAO : DB 접근 (SQL 실행)
 *     FaqOutputView : 결과 출력
 * <예외 처리 원칙>
 *     1. ID 입력 오류 : inputFaqId() 가 -1 반환 시 showError() + return
 *     2. 질문 입력 오류 : inputQuestion() 가 null 반환 시 showError() + return
 *     3. 답변 입력 오류 : inputAnswer() 가 null 반환 시 showError() + return
 *     4. 카테고리 : null 허용 (faqs 테이블 nullable) - 검증 없음
 */

public class FaqController {

    private final FaqDAO faqDAO = new FaqDAO();
    private final FaqInputView inputView = new FaqInputView();
    private final FaqOutputView outputView = new FaqOutputView();

    // FAQ-001 : FAQ 등록

    /* comment.
     *  <역할>
     *     FAQ 등록
     *  <흐름도>
     *     1. 질문 입력받기     → null 이면 showError() + return
     *     2. 답변 입력받기     → null 이면 showError() + return
     *     3. 카테고리 입력받기 → null 허용 (빈 값이면 DB에 NULL 저장)
     *     4. faqDAO.addFaq()  → DB INSERT
     *     5. showFaqAdded()   → 등록 성공 출력
     */

    public void addFaq() {
        String question = inputView.inputQuestion();
        // question 이 null 값일 경우 아래의 출력문 출력
        if (question == null) {
            outputView.showError("올바른 질문을 입력해주세요.");
            return;
        }

        String answer = inputView.inputAnswer();
        // answer 값이 null 일 경우 아래의 출력문 출력
        if (answer == null) {
            outputView.showError("올바른 답변을 입력해주세요.");
            return;
        }

        String category = inputView.inputCategory();

        faqDAO.addFaq(question, answer, category);
        outputView.showFaqAdded();
    }

    // FAQ-002 : FAQ 목록 조회

    /* comment.
        <역할>
         FAQ 전체 목록 조회 및 출력
         <흐름도>
          1. faqDAO.getFaqs() -> DB SELECT (created_at DESC 정렬)
          2. outputView.showFaqs() -> 콘솔 출력
     */

    public void showFaqs() {
        List<String[]> faqs = faqDAO.getFaqs();
        outputView.showFaqs(faqs);
    }

    // FAQ-003 : FAQ 상세 조회

    /* comment.
        <역할>
        FAQ 상세 조회
        <흐름도>
        1. showFaqs() -> 목록 먼저 보여주기 (재사용)
        2. inputFaqId() -> FAQ 번호 입력받기
        3. faqId == -1 이면 -> showError() + return
        4. faqDAO.getFaqDetail() -> DB SELECT (단건)
        5. showFaqDetail() -> 상세 출력
     */

    public void showFaqDetail() {
        showFaqs();

        int faqId = inputView.inputFaqId();
        // 만약 faqId가 -1일 경우 아래의 출력문 출력
        if (faqId == -1) {
            outputView.showError("올바른 FAQ 번호를 입력해주세요.");
            return;
        }

        String[] faq = faqDAO.getFaqDetail(faqId);
        outputView.showFaqDetail(faq);
    }

    // FAQ-004 : FAQ 수정

    /* comment.
        <역할>
        FAQ 질문 + 답변 + 카테고리 수정
        <흐름도>
        1. showFaqs() -> 목록 먼저 보여주기 (재사용)
        2. inputFaqId() -> FAQ 번호 입력받기
        3. faqId == -1 이면 -> showError() + return 반환
        4. inputQuestion() -> 새 질문 입력받기
        5. newQuestion == null 이면 -> showError() + return
        6. inputAnswer() -> 새 답변 입력받기
        7. newAnswer == null 이면 → showError() + return
        8. inputCategory()        → 새 카테고리 입력받기 (null 허용)
        9. faqDAO.updateFaq()     → DB UPDATE
        10. showFaqUpdated()       → 수정 성공 출력
     */

    public void updateFaq() {
        showFaqs();

        int faqId = inputView.inputFaqId();
        // 만약 faqId 가 -1일 경우
        /* comment.
            왜 우리는 -1을 지정하는가.
            팀의 약속에 보면 -1이면 오류값으로 사용하기로 지정했기 때문이다.
         */
        if (faqId == -1) {
            outputView.showError("올바른 FAQ 번호를 입력해주세요.");
            return;
        }

        String newQuestion = inputView.inputQuestion();
        // newQuestion 값이 null 값이라면 아래의 출력문을 출력
        if (newQuestion == null) {
            outputView.showError("올바른 질문을 입력해주세요.");
            return;
        }

        String newAnswer = inputView.inputAnswer();
        if (newAnswer == null) {
            // 새로운 답변이 null 값이라면 아래의 출력문을 출력
            outputView.showError("올바른 답변을 입력해주세요.");
            return;
        }

        String newCategory = inputView.inputCategory();

        faqDAO.updateFaq(faqId, newQuestion, newAnswer, newCategory);
        outputView.showFaqUpdated();
    }

    // FAQ-005 : FAQ 삭제

    /* comment.
        <역할>
        FAQ 삭제
        <흐름도>
        1. showFaqs() -> 목록 먼저 보여주기 (재사용)
        2. inputFaqId() -> FAQ 번호 입력받기
        3. faqId == -1 이면 -> showError() + return
        4. faqDAo.deleteFaq() -> DB DELETE
        5. showFaqDeleted() -> 삭제 성공 출력
     */

    public void deleteFaq() {
        showFaqs();

        int faqId = inputView.inputFaqId();
        // 만약 -1일 경우 아래의 출력문을 출력
        if (faqId == -1) {
            outputView.showError("올바른 FAQ 번호를 입력해주세요.");
            return;
        }

        faqDAO.deleteFaq(faqId);
        outputView.showFaqDeleted();
    }
}