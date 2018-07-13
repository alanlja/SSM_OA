package sqlTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class connectTest {
    public static void main(String[] args) {
        User userZhangsan = new User("root","123456");
        if(login(userZhangsan)) {
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }
    }

    private static boolean login(User userZhangsan) {

        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            //1、装载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //2、链接数据库，使用com.mysql.jdbc.Connection包会出错
            List<User> list=new ArrayList<User>();
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssm_oa2?user=root&password=123456&useSSL=false&serverTimezone=GMT%2B8");
            //3、创建连接语句
            st=conn.createStatement();
            //4、执行ＳＱＬ语句获得结果集
            rs=st.executeQuery("select * from sys_org ");
            //5、循环获得数据库字段生成对象
            //这种方法登录要把数据库数据都拿过来和login中数据比较，超级浪费资源
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //关闭结果集
            try {
                if(rs!=null) {
                    rs.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //关闭连接语句
            try {
                if(st!=null) {
                    st.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //关闭数据库连接
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;


    }

}