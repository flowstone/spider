package me.xueyao;

/**
 * Description:
 * User: Simon.Xue
 * Date: 2018/10/23.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtractDataTests {

    @Test
    public void domNavigation() throws IOException {
        File file = new File("F:\\Git-Reposity\\spider\\index.html");
        Document doc = Jsoup.parse(file, "UTF-8", "http://www.xueyao.me");
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String text = link.text();
            System.out.println(linkHref+text);
        }

    }

    @Test
    public void selectorSyntax() throws IOException {
        File file = new File("F:\\Git-Reposity\\spider\\index.html");
        Document doc = Jsoup.parse(file, "UTF-8", "http://www.xueyao.me");

        Elements links = doc.select("a[href]");
        Elements pngs = doc.select("img[src$=.png]");
        Element masthead = doc.select("div.masthead").last();
        Elements resultLinks = doc.select("h3.r > a");

        for (Element link : links) {
            System.out.println(link.text());
        }

        for (Element png : pngs) {
            System.out.println(png.attr("src"));
        }

        System.out.println(masthead.text());

        for (Element resultLink : resultLinks) {
            System.out.println(resultLink.text());
        }
    }

    @Test
    public void attributesText() {
        String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
        Document document = Jsoup.parse(html);
        Element link = document.select("a").first();
        System.out.println(link.text());

        String text = document.body().text();
        String linkHref = link.attr("href");
        String linkText = link.text();
        System.out.println(text + " " + linkHref + " " + linkText);

        String linkOuterH = link.outerHtml();
        System.out.println(linkOuterH);
        System.out.println(link.html());
    }
}
