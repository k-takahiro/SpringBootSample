package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.demo.model.Item;

@Controller
public class ListController {

	// 新規登録画面へ遷移
	@GetMapping("/list")
	public String roadFile() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		// org.jsoup.nodes.Document document =
		// Jsoup.connect("http://www.google.co.jp").get();
		// System.out.println(document.html());

		org.jsoup.nodes.Document document = Jsoup
				.parse(new File("C:\\workspace\\SpringBootSample\\demo\\work\\storage\\test.html"), "UTF-8");
		
		Elements links = document.getElementsByTag("a");
		for (Element link : links) {
			String linkHref = link.attr("href");
			String linkText = link.text();
			System.out.println(linkHref);
			System.out.println(linkText);
		}

		return "list";
	}
}
