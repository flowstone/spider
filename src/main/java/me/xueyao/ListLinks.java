package me.xueyao;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Description:
 * User: Simon.Xue
 * Date: 2018/10/23.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];
        System.out.println("Fetching "+url+"...");

        Document document = Jsoup.connect(url).get();
        Elements links = document.select("a[href]");
        Elements media = document.select("[src]");
        Elements imports = document.select("link[href]");

        System.out.println(media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")) {

            }
        }
    }
}
