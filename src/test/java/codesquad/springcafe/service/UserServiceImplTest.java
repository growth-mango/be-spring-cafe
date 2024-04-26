package codesquad.springcafe.service;

import codesquad.springcafe.user.dto.User;
import codesquad.springcafe.user.repository.UserRepository;
import codesquad.springcafe.user.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock // 지정된 클래스의 모의 객체 생성
    private UserRepository userRepository;

    @InjectMocks // 모의 객체를 테스트 대상 객체에 주입
    private UserServiceImpl userService;

    @Test
    @DisplayName("회원가입 시, 유저 정보를 정상적으로 등록하고, 등록된 정보를 올바르게 반환한다")
    void testSignup() {
        User user = new User("username", "password", "name", "email@example.com"); // 테스트 시 사용할 가짜 더미 데이터
        when(userRepository.save(any(User.class))).thenReturn(user); // save 메소드가 호출될 때 어떤 User 객체라도 인자로 받는다면 미리 생성한 user 객체를 반환

        User createdUser = userService.signup(user); // 앞에서 생성한 더미데이터인 user 를 등록

        assertNotNull(createdUser);
        assertThat(createdUser.getUserId()).isEqualTo("username");
    }

    @Test
    @DisplayName("중복된 아이디로 회원가입 시, 가입이 되지 않는다.")
    void testDuplicateUser(){
        // given - 데이터 준비
        User user = new User("username2", "password", "name", "email@example.com");

        // when - 상황
        when(userRepository.findByUserId("username2")).thenReturn(Optional.of(user));

        // then - 결과
        assertThrows(IllegalStateException.class, () -> {
            userService.signup(user);
        });

        // userRepository.save 가 호출되지 않았음을 확인
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testFindUserById() {
        // given
        User user = new User("username", "password", "name", "email@example.com");
        when(userRepository.findByUserId("username")).thenReturn(Optional.of(user));

        // when
        Optional<User> foundUser = userService.findUserByUserId("username");

        // then
        assertTrue(foundUser.isPresent());
        assertThat(foundUser.get().getName()).isEqualTo("name");
    }

    @Test
    void testFindAllUsers() {
        List<User> users = Arrays.asList(
                new User("username1", "password1", "name1", "email1@example.com"),
                new User("username2", "password2", "name2", "email2@example.com")
        );

        when(userRepository.findAll()).thenReturn(users);

        List<User> userList = userService.findAllUsers();

        assertThat(userList.size()).isEqualTo(2);
        assertThat(userList.get(0).getName()).isEqualTo("name1");
    }
}