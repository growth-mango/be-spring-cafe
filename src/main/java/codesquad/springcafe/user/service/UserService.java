package codesquad.springcafe.user.service;

import codesquad.springcafe.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User signup(User user);
    Optional<User> findUserByUserId(String userId); // null 이 있을 수도 있을 경우... null 안정성
    List<User> findAllUsers();
    User update(String userId, User user);

    // 로그인
    Optional<User> login(String userId, String password);
}
