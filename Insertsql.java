package Pachong1;

import java.sql.*;

public class Insertsql {

    public static void insertsql(String sql)

    {

        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

//将Test换成你自己的数据库名

        String dbURL = "jdbc:sqlserver://172.24.1.64:1433;DatabaseName=Kfsjj";

        String userName = "jsj";

//将密码改成自己设置的密码

        String userPwd = "Zqsb_123abc";

        Connection dbConn = null;

        try

        {

            Class.forName(driverName);

            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);

            System.out.println("successfully connected");

            Statement stat = dbConn.createStatement();

            //String sql = "insert into tb_mdd " +
            //        "values('2022年06月23日', '14880.06','915.52','15795.58','2.29%','822.24','73.81','896.06','8.33%','94','11702','624.2','43453','271656','1532801','49292.83','2537.69','51830.52','280.4%')";

            stat.execute(sql);

            System.out.println("successfully insert");
        }

        catch (SQLException e)

        {

            e.printStackTrace();



        }catch (Exception e){
            e.printStackTrace();

            System.out.print("false");

        }

    }

}
