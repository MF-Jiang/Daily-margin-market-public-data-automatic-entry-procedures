package Pachong1;//下载新的表

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;


public class Worm_for_newxls {
    private static String url = "http://www.csf.com.cn/publish/main/1022/1024/1127/index.html";

    public static void Newxls() throws IOException {

        //apacheHttpClients();

        Document document = Jsoup.connect(url).get();
//        //选择class为list_right_content的进行爬取,    页面上会有一个class多，得想办法删掉不然没法下载
//        Elements elements = document.select(".list_right_content");
        //System.out.println(elements.get(0));

        Elements elements = document.select(".list_content_one_container");
        //Elements elements = document.select(".list_right_content");

        //拿到其中是a[href]的语句
        Elements xlsEmement = elements.get(0).select("a[href]");
        System.out.println(elements.get(0));

        //拼接成完整网址
        String newurl = "http://www.csf.com.cn"+xlsEmement.attr("href");

        System.out.println(newurl);

        Connection.Response response = Jsoup.connect(newurl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.44")
                .ignoreContentType(true).execute();

        String name = xlsEmement.text();
        ByteArrayInputStream stream = new ByteArrayInputStream(response.bodyAsBytes());
        FileUtils.copyInputStreamToFile(stream,new File(""+name+".xls"));
    }

}
