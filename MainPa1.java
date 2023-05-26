package Pachong1;

import com.spire.pdf.exporting.xps.schema.Fallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static Pachong1.DownloadWholeUrl.getContentFromUrl;
import static Pachong1.DownloadWholeUrl.writeContentToFile;

public class MainPa1 {

    public static  void MyThread(){
        new Thread(){
            public void run(){
                boolean flg = false;
                while(!flg){
                    try{
                        //获取新网页
                        String content = getContentFromUrl("http://www.csf.com.cn/publish/main/1022/1024/1127/index.html","UTF-8");
                        writeContentToFile(content,"contentNew.text");

                        //与旧网页比较
                        Boolean Whether_Uploaded=TxtMD5.tcheck();

                        //如果数据更新了
                        if (Whether_Uploaded==false){
                            //数据更新
                            Worm_for_newxls.Newxls();
                            //WriteExcel.Writeinexcel();
                            System.out.println("数据更新完毕");

                            //加入数据库中
                            String[] needin = ExcelUtil.takeoutfromnew();
                            String sql = "insert into tb_mdd " +
                                    "values('"+needin[1]+"', '"+needin[2]+"','"+needin[3]+"','"+needin[4]+"','"+needin[5]+"','"+needin[6]+"','"+needin[7]+"','"+needin[8]+"','"+needin[9]+
                                    "','"+needin[10]+"','"+needin[11]+"','"+needin[12]+"','"+needin[13]+"','"+needin[14]+"','"+needin[15]+"','"+needin[16]+"','"+needin[17]+"','"+needin[18]+"','"+needin[19]+"')";

                            Insertsql.insertsql(sql);

                        }else {
                            System.out.println("No new data");
                        }

                        //删除老文件，给新文件改名字
                        File file1 = new File("content.text");
                        try {
                            if (file1.delete()){
                                System.out.println("delete old version");
                            }else{
                                System.out.println("failed to delete old version");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        RenameFile.Rename("contentNew.text",".//content.text");
                        System.out.println("new web is saved");

                        //如果保留每日下载副本，如果不需要就注释掉
                        Date date=new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
                        //System.out.println(w);

                        if (w!=1){
                            calendar.add(Calendar.DAY_OF_MONTH, -1);  // 在当前日基础上-1
                            SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
                            String getdatapath = ".//融资融券市场每日数据统计"+format.format((calendar.getTime()))+".xls";


                            File file2 = new File(getdatapath);
                            file2.delete();
                        }else {
                            calendar.add(Calendar.DAY_OF_MONTH, -3);  // 在当前日基础上-1
                            SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
                            String getdatapath = "融资融券市场每日数据统计"+format.format((calendar.getTime()))+".xls";


                            File file2 = new File(getdatapath);
                            file2.delete();

                        }
                    Thread.sleep(3600000);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) throws Exception {

        File file = new File("content.text");

        File file2 = new File("融资融券市场每日数据统计汇总.xlsx");

        if (!file.exists()){
            String content = getContentFromUrl("http://www.csf.com.cn/publish/main/1022/1024/1127/index.html","UTF-8");
            writeContentToFile(content,"content.text");
        }


        MyThread();

    }
}
