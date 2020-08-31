package com.example.alioos.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * date 2020/5/12
 */
public class JDBCUtil {
    private static String jdbcUrl = "jdbc:sqlserver://192.168.31.10;databaseName=botao";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "123456";

    public static JSONArray getData(String sql, int column) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        JSONArray array = new JSONArray();
        try {
            //加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //建立连接
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            //预编译
            statement = connection.prepareStatement(sql);
            //查询结果
            ResultSet resultSet = statement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()){
                    JSONObject jsonObject = new JSONObject();
                    for (int i =1;i<=column;i++){
                        jsonObject.put(i+"",resultSet.getString(i));
                    }
                    array.add(jsonObject);
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
        return array;
    }

    public static List<String> getOneCloumnData(String sql) {
        List<String> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //建立连接
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            //预编译
            statement = connection.prepareStatement(sql);
            //查询结果
            ResultSet resultSet = statement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()){
                    list.add(resultSet.getString(1));
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
        return list;
    }

    public static String getString1(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //建立连接
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            //预编译
            statement = connection.prepareStatement(sql);
            //查询结果
            ResultSet resultSet = statement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()){
                    return resultSet.getString(1);
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
        return null;
    }
}
