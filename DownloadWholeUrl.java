package Pachong1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class DownloadWholeUrl {

    public static String getContentFromUrl(String myUrl, String charset) {
        StringBuffer sb = new StringBuffer();
        URL url;
        try {
            url = new URL(myUrl);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            Scanner sc = new Scanner(is, charset);
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()).append("\r\n");
            }
            sc.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Read from Successfully");
        return sb.toString();
    }

    public static Boolean writeContentToFile(String content, String fileName) {
        boolean sign = false;
        // 把数据存入在文件中
        PrintWriter pw;
        try {
            pw = new PrintWriter(fileName);
            pw.println(content);
            pw.flush();
            pw.close();
            sign = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            sign = false;
        }
        System.out.println("Write content Successful");
        return sign;

    }

//    public static void main(String[] args){
//        String content = getContentFromUrl("http://www.csf.com.cn/publish/main/1022/1024/1127/index.html","UTF-8");
//        writeContentToFile(content,"content.text");
//
//    }
}