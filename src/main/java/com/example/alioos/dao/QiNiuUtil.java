package com.example.alioos.dao;

import com.alibaba.fastjson.JSONArray;

import java.sql.*;

/**
 * date 2020/8/10
 */
public class QiNiuUtil {

    private static String jdbcUrl = "jdbc:sqlserver://192.168.31.10;databaseName=botao";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "123456";

    public static JSONArray insert(String coverPath, String videoPath, String title, String seconds) {
        Connection connection = null;
        PreparedStatement statement = null;
        JSONArray array = new JSONArray();
        try {
            //加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //建立连接
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            //预编译
            StringBuffer sb = new StringBuffer();
            sb.append(" INSERT INTO [yzcm_videos_1] ( ");
            sb.append(" [cmc_account_id], ");
            sb.append(" [cmc_bgm_id], ");
            sb.append(" [cmc_cover_path], ");
            sb.append(" [cmc_creat_time], ");
            sb.append(" [cmc_like_counts], ");
            sb.append(" [cmc_remarks], ");
            sb.append(" [cmc_status], ");
            sb.append(" [cmc_video_desc], ");
            sb.append(" [cmc_video_label], ");
            sb.append(" [cmc_video_path], ");
            sb.append(" [cmc_video_seconds], ");
            sb.append(" [cmc_video_title], ");
            sb.append(" [cmc_comments_counts], ");
            sb.append(" [cmc_gift_counts], ");
            sb.append(" [cmc_share_counts] ");
            sb.append(" ) ");
            sb.append(" VALUES ");
            sb.append(" ( ");
            sb.append(" 0, ");
            sb.append(" 0, ");
            sb.append(" '"+coverPath+"', ");
            sb.append(" GETDATE(), ");
            sb.append(" 0, ");
            sb.append(" NULL, ");
            sb.append(" 1, ");
            sb.append(" NULL, ");
            sb.append(" '默认', ");
            sb.append(" '"+videoPath+"', ");
            sb.append(seconds+", ");
            sb.append(" '"+title+"', ");
            sb.append(" 0, ");
            sb.append(" 0, ");
            sb.append(" 0 ");
            sb.append(" ); ");

            System.out.println("coverPath::"+coverPath.length());
            System.out.println("videoPath::"+videoPath.length());
            System.out.println("title::"+title.length());

            statement = connection.prepareStatement(sb.toString());

            statement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return array;
    }


    public static int countByCover(String coverName) {
        Connection connection = null;
        PreparedStatement statement = null;
        JSONArray array = new JSONArray();
        try {
            //加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //建立连接
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            //预编译
            String sql = "select count(1) from yzcm_videos_1 where cmc_cover_path like '%"+coverName+"%'";

            statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
