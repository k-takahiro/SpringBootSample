// package com.example.demo.config;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// // 全てのコントローラーに対して共通処理を行う
// @Configuration
// public class UserListPages implements WebMvcConfigurer {

// 	// 設定を補完する情報のことをリゾルバ(resolver)と呼ぶ
// 	@Override
// 	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

// 			PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
// 			// 1ページに表示する最大件数(10件)を設定する
// 			resolver.setMaxPageSize(10);
// 			argumentResolvers.add(resolver);
// 	}
// }

// 理解できずに一旦コメントアウトする。
// 参考にした記事
// SpringBoot + Thymeleafでページングを実現する
// https://qiita.com/tanibuchi12/items/6c8fedbc19bdb277d6f2 