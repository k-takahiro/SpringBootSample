package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.User;

@Controller
@RequestMapping("userList")
public class UserListController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUser(Model model) {
        List<User> userList = selectMany();

        // 画面表示
        model.addAttribute("userList", userList);
        return "userList";
    }

    // Userテーブルの全データを取得.
    public List<User> selectMany() throws DataAccessException {

        // M_USERテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbcTemplate.queryForList("SELECT id, user_name FROM user_master_tbl");

        // 結果返却用の変数
        List<User> userList = new ArrayList<>();

        // 取得したデータを結果返却用のListに格納していく
        // 結果返却用のListに追加
        for (Map<String, Object> map : getList) {
            User user = new User();
            user.setId((String) map.get("id").toString());
            user.setName((String) map.get("user_name"));
            // user.setEmail((String) map.get("e_mail"));
            // user.setAge(Integer.parseInt((String) map.get("age").toString()));
            // user.setInputDate((String) map.get("use_start_date").toString());
            // user.setUserId((String) map.get("user_id"));
            // user.setPassword((String) map.get("password"));
            userList.add(user);
        }
        return userList;
    }

    // /**
    //  * ユーザー情報詳細画面を表示
    //  * 
    //  * @param id    表示するユーザーID
    //  * @param model Model
    //  * @return ユーザー情報詳細画面
    //  */
    // @GetMapping("/user/{id}")
    // public String displayView(int id, Model model) {
    //     // M_USERテーブルのデータを全件取得
    //     Map<String, Object> map = jdbcTemplate
    //             .queryForMap("SELECT * FROM user_master_tbl WHERE id = " + id);
    //     User user = new User();
    //     user.setId((String) map.get("id").toString());
    //     user.setName((String) map.get("user_name"));
    //     user.setEmail((String) map.get("e_mail"));
    //     user.setAge(Integer.parseInt((String) map.get("age").toString()));
    //     user.setInputDate((String) map.get("use_start_date").toString());
    //     user.setUserId((String) map.get("user_id"));
    //     user.setPassword((String) map.get("password"));

    //     model.addAttribute("userDetails", user);
    //     return "userDetails";
    // }
}
