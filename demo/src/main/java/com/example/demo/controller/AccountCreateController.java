package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.demo.model.User;

@Controller
public class AccountCreateController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/accountCreateFormView")
    public String getView(@Validated User user, BindingResult result) {
        return "accountCreateForm";
    }

    @PostMapping("/accountCreateForm")
    public String Confirm(@ModelAttribute @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            // エラーの場合は再度入力画面へ
            return "accountCreateForm";
        }
        model.addAttribute("user", user);
        // エラーなしは確認画面へ
        return "accountCreateFormConfirm";
    }


   // @PostMapping("/accountCreateForm")
    public String userInsert(Model model, User user) throws ParseException {

        String sqlText = """
                INSERT
                INTO public.user_master_tbl(
                	id
                    ,user_name
                	, e_mail
                	, age
                	, use_start_date
                	, user_phone
                	, user_id
                	, password
                )
                VALUES (
                	?
                    ,?
                	, ?
                	, ?
                	, ?
                	, ?
                	, ?
                	, ?
                )
                                """;

        // ユーザIDを作成する。
        // ユーザIDはシーケンス値を利用するため、SQLクエリを作成する。
        // ユーザIDの重複を防ぐために、取得する際はシーケンス値の最大値から"+1"を指定する。
        // 取得したシーケンス値の評価をする。
        // シーケンス値がnullの場合は、ユーザIDは設定しないため、アプリ継続不可として異常終了させる。
        String str = "SELECT setval(pg_get_serial_sequence('user_master_tbl', 'id'), (SELECT MAX(id) FROM user_master_tbl) + 1);";
        Integer userId = jdbcTemplate.queryForObject(str, Integer.class) ;
        if (Objects.isNull(userId)){
            throw new IllegalArgumentException("ユーザID取得の際にエラーが発生したため異常終了：" + userId );
        }
        
        // 利用開始日の日付フォーマットをテーブル定義に合わせて変換する。
        // フォーマット）yyyy/MM/dd → yyyy-MM-dd
        // 例）9999/11/02 → 9999-11-02
        var useStartDay = user.getInputDate().replace("-", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = dateFormat.parse(useStartDay);

        // ユーザ情報を登録へ
        jdbcTemplate.update(sqlText, (userId), user.getName(), user.getEmail(), user.getAge(), date, user.getUser_phone(),
                user.getUserId(), user.getPassword());

        return "redirect:/userList";

    }
}
 