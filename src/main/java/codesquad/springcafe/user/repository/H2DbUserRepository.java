package codesquad.springcafe.user.repository;

import codesquad.springcafe.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class H2DbUserRepository implements UserRepository {
    private static final String USER_ID = "userId";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2DbUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(USER_ID, user.getUserId());
        parameters.put(PASSWORD, user.getPassword());
        parameters.put(NAME, user.getName());
        parameters.put(EMAIL, user.getEmail());

        jdbcInsert.execute(parameters);

        return user;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return jdbcTemplate.query("select * from USERS where userId = ?", userRowMapper(), userId)
                .stream()
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from USERS", userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User(
                    rs.getString(USER_ID),
                    rs.getString(PASSWORD),
                    rs.getString(NAME),
                    rs.getString(EMAIL)
            );
            return user;
        };
    }
    
    public void update(User user){
        String sql = "UPDATE users SET password = ?, name = ?, email = ? WHERE userId =?";
        jdbcTemplate.update(sql,
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getUserId());
    }


}


