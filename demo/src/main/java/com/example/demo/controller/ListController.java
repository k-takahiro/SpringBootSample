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
import org.xml.sax.SAXException;

import com.example.demo.model.Workbooks;

@Controller
public class ListController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/workbookList")
	public String roadFile(Model model)
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

	// TODO: Date型とTimestamp型の違いについて
	// https://qiita.com/mumian1014/items/921ef11c7e5a937980fd
	// Timestampの実装一例は下記↓に示す。
	// 協定世界時のUTC 1970年1月1日深夜零時との差をミリ秒で取得
	// ミリ秒を引数としてTimestampオブジェクトを作成
	// Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	// String formattedDate = sdf.format(timestamp);

	@GetMapping("/book")
	public String search(Model model)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		//insertWrookbook();

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

}