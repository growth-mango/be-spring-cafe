package codesquad.springcafe.advice;

import codesquad.springcafe.dto.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// 모든 요청에 대해 사용자 세션을 확인하고 정보를 모든 뷰에 자동으로 추가하기
@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("currentUser")
    public User getCurrentUser(HttpSession session){
        return (User) session.getAttribute("currentUser");
    }

    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(HttpSession session){
        return session.getAttribute("currentUser") != null;
    }
}
