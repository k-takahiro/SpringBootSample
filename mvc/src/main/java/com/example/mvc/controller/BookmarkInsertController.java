package com.example.mvc.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.xml.sax.SAXException;

import com.example.mvc.model.BookmarkDto;

@Controller
public class BookmarkInsertController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

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

}