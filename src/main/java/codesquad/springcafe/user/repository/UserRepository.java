package codesquad.springcafe.user.repository;


import codesquad.springcafe.user.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findByUserId(String userId);
    List<User> findAll();
    void update(User user);
}
