package me.xueyao;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

	private static Map<String, String> map = new HashMap<String, String>();

	@Test
	public void contextLoads() throws IOException {
		//获得连接，并获取当前的Document对象
		Document document = Jsoup.connect("https://en.wikipedia.org/").get();
		System.out.println(document.title());
		Elements select = document.select("#mp-topbanner div");
		System.out.println(select);
	}

	@Test
	public void parsingDocument() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		//解析页面
		Document parse = Jsoup.parse(html);
		//获得标题
		System.out.println(parse.title());
		//获得解析对象的Body部分
		System.out.println(parse.body());
		//根据标签p,获得元素对象
		Elements p = parse.body().getElementsByTag("p");
		//获得元素对象里的内容
		System.out.println(p.html());
	}

	@Test
	public void parseBodyFragment() {
		String html = "<div><p>Lorem ipsum.</p>";
		//解析不完成的Html文档
		Document document = Jsoup.parseBodyFragment(html);

		System.out.println(document.body().getElementsByTag("p").html());
	}

	@Test
	public void loadDocumentFromUrl() throws IOException {
		Document document = Jsoup.connect("https://www.baidu.com/").get();
		System.out.println(document.title());
		map.put("lp-sign", "d12827513329c0b957da8078b15182cf");
		map.put("nonce-str", "3hx37quPhnk9M9sZfHeovjvLmnfgyqb3");
		map.put("current-timestamp", "1531811780498");

		Document post = Jsoup.connect("http://192.168.2.166:8899/jersey/sys/send/validate/sms") //连接Url
				.data("mobile", "18896544516") //设置数据
				.userAgent("Mozilla")  //用户代理
				.cookie("auth", "token")  //Cookie
				.headers(map)  //请求头
				.ignoreContentType(true)  //忽略内容类型
				.timeout(3000)   //超时
				.post();  //POST请求
		System.out.println(post.body().html());
	}

	@Test
	public void loadDocumentFromFile() throws IOException {
		File file = new File("F:\\Git-Reposity\\spider\\index.html");
		Document parse = Jsoup.parse(file, "UTF-8", "http://www.baidu.com");
		System.out.println(parse.body().html());
	}

}
