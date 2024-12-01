package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import com.example.demo.model.Workbooks;

@Controller
public class ListController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/workbookList")
	public String roadFile(Model model, @ModelAttribute("formModel") Workbooks bookdata)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		// insertWrookbook();

		List<Map<String, Object>> getList = jdbcTemplate
				.queryForList("SELECT title, link, category, insert_date, update_date FROM workbook_tbl");

		List<Workbooks> workbookList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			Workbooks workbooks = new Workbooks();
			workbooks.setTitle((String) map.get("title"));
			workbooks.setLink((String) map.get("link"));
			workbooks.setCategory((String) map.get("category"));
			workbooks.setInsert_date((String) map.get("insert_date").toString());
			workbooks.setUpdate_date((String) map.get("update_date").toString());

			workbookList.add(workbooks);
		}

		// 画面表示
		model.addAttribute("workbookList", workbookList);

		return "workbookList";
	}

	private void insertWrookbook()
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		String sqlText = """
				INSERT
				INTO public.workbook_tbl(
					title
					, link
					, category
					, insert_date
					, update_date
				)
				VALUES (
					?
					, ?
					, ?
					, ?
					, ?
				)
						""";

		org.jsoup.nodes.Document document = Jsoup
				.parse(new File("C:\\workspace\\SpringBootSample\\demo\\work\\storage\\test.html"), "UTF-8");
		Elements links = document.getElementsByTag("a");
		for (Element link : links) {
			var nowDate = new Date();
			jdbcTemplate.update(sqlText, link.text(), link.attr("href"), "", nowDate, nowDate);
		}
	}

	// @GetMapping("/book")
	@RequestMapping("/book")
	public String search(@ModelAttribute("formModel") Workbooks wbData, Model model)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		List<Map<String, Object>> getList = jdbcTemplate
				.queryForList("SELECT title, link, category, insert_date, update_date FROM workbook_tbl where title ILIKE '%"
						+ wbData.getTitle() + "%'");

		List<Workbooks> workbookList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			Workbooks workbooks = new Workbooks();
			workbooks.setTitle((String) map.get("title"));
			workbooks.setLink((String) map.get("link"));
			workbooks.setCategory((String) map.get("category"));
			workbooks.setInsert_date((String) map.get("insert_date").toString());
			workbooks.setUpdate_date((String) map.get("update_date").toString());

			workbookList.add(workbooks);
		}
		// 画面表示
		model.addAttribute("workbookList", workbookList);

		return "workbookList";
	}
}