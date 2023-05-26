package Pachong1;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 文件进行比较是否相同，只要用于图片的比较，其它文件也可以的
 * 比较的思路，首先比较两个文件大小是否相同，大小不相等肯定不是同一个文件
 * 然后再比较文件的md5值 ，md5值不相等的两个文件肯定也不是同一个文件
 * 用途：如做爬虫抓取图片，可以根据网址路径进行判断 ，但还是有网址路径不同，但图片内容是相同的，那就可以避免重复下载
 * 网址：https://www.wyxbc.com/html/epAAaAA6y8tx0jAAaAAzLuiBQpEAAaAA6Q==.html
 * @author wyxbc.com it技术博客网
 *
 */
public class TxtMD5 {

    public static Boolean tcheck(){
        File file1 = new File("contentNew.text");
        File file2 = new File("content.text");

        //System.out.println("文件长度输出===>");
        //System.out.println(file1.length());
        //System.out.println(file2.length());

        //System.out.println("文件md5输出===>");
        String str1 = getFileMD5(file1);
        String str2 = getFileMD5(file2);

        //System.out.println(str1);
        //System.out.println(str2);

        //System.out.println(str1.compareTo(str2) == 0?"true":"false");
        return str1.compareTo(str2) == 0;
    }

    public static void main(String[] args) {
        File file1 = new File("contentNew.text");
        File file2 = new File("content.text");

        //System.out.println("文件长度输出===>");
        //System.out.println(file1.length());
        //System.out.println(file2.length());

        //System.out.println("文件md5输出===>");
        String str1 = getFileMD5(file1);
        String str2 = getFileMD5(file2);

        //System.out.println(str1);
        //System.out.println(str2);

        System.out.println(str1.compareTo(str2) == 0?"true":"false");
    }

    /**
     * 获取文件的md5值
     * @param file 文件
     * @return 返回文件的md5值字符串
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[8192];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}