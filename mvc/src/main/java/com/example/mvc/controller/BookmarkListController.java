package com.example.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.xml.sax.SAXException;

import com.example.mvc.model.BookmarkDto;

@Controller
public class BookmarkListController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/bookmarkListSearch")
	public String getAllBookmarkTitleList(Model model, @ModelAttribute("formModel") BookmarkDto bookdata)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		List<Map<String, Object>> getList = jdbcTemplate
				.queryForList("SELECT title, link, category, insert_date, update_date FROM workbook_tbl");

		List<BookmarkDto> workbookList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			BookmarkDto workbooks = new BookmarkDto();
			workbooks.setTitle((String) map.get("title"));
			workbooks.setLink((String) map.get("link"));
			workbooks.setCategory((String) map.get("category"));
			workbooks.setInsert_date((String) map.get("insert_date").toString());
			workbooks.setUpdate_date((String) map.get("update_date").toString());

			workbookList.add(workbooks);
		}

		// 画面表示
		model.addAttribute("titleList", workbookList);

		return "BookmarkTitleList";
	}
	
	@PostMapping(path = "/bookmarkListSearch")
	public String search(@ModelAttribute("formModel") BookmarkDto wbData, Model model)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		List<Map<String, Object>> getList = jdbcTemplate
				.queryForList("SELECT title, link, category, insert_date, update_date FROM workbook_tbl where title ILIKE '%"
						+ wbData.getTitle() + "%'");

		List<BookmarkDto> workbookList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			BookmarkDto workbooks = new BookmarkDto();
			workbooks.setTitle((String) map.get("title"));
			workbooks.setLink((String) map.get("link"));
			workbooks.setCategory((String) map.get("category"));
			workbooks.setInsert_date((String) map.get("insert_date").toString());
			workbooks.setUpdate_date((String) map.get("update_date").toString());

			workbookList.add(workbooks);
		}
		// 画面表示
		model.addAttribute("titleList", workbookList);

		return "BookmarkTitleList";
	}

}