package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    // RowMapperの実装
    private static final RowMapper<User> ROW_MAPPER = (rs, i) -> {
        String name = rs.getString("name");
        String password = rs.getString("password");
        return new User(name, password);
    };

    /**
     * ユーザー名とパスワードからユーザーを取得する.
     * 
     * @param name     ユーザー名
     * @param password パスワード
     * @return ユーザー情報、存在しない場合は空のOptional
     */
    public Optional<User> findByUser(String name, String password) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", name).addValue("password", password);
        String sql = "SELECT * FROM users WHERE name = :name AND password = :password";
        List<User> userList = jdbcTemplate.query(sql, param, ROW_MAPPER);
        return userList.stream().findFirst();
    }
}
