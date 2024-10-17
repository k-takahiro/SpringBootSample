package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate oldjdbcTemplate;
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

 // Userテーブルの全データを取得.
   // @Override
    public List<User> selectMany() throws DataAccessException {

        // M_USERテーブルのデータを全件取得
        List<Map<String, Object>> getList = oldjdbcTemplate.queryForList("SELECT * FROM m_user");

        // 結果返却用の変数
        List<User> userList = new ArrayList<>();

        // 取得したデータを結果返却用のListに格納していく
        for (Map<String, Object> map : getList) {

            //Userインスタンスの生成
 //           User user = new User();

            // Userインスタンスに取得したデータをセットする
 //           user.setUserId((String) map.get("user_id")); //ユーザーID
 //           user.setPassword((String) map.get("password")); //パスワード
            // user.setUserName((String) map.get("user_name")); //ユーザー名
            // user.setBirthday((Date) map.get("birthday")); //誕生日
            // user.setAge((Integer) map.get("age")); //年齢
            // user.setMarriage((Boolean) map.get("marriage")); //結婚ステータス
            // user.setRole((String) map.get("role")); //ロール

            //結果返却用のListに追加
//            userList.add(user);
        }

        return userList;
    }

}
