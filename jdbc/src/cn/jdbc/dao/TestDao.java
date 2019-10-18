package cn.jdbc.dao;

import cn.jdbc.db.DBUtil;
import cn.jdbc.model.Test;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 增删改查
 */
public class TestDao {

    public void addTest(Test test) throws SQLException {
        //连接数据库
        Connection conn = DBUtil.getConnection();
        //sql语句
        String sql = "" +
                "insert into java_Test" +
                "(user_name,sex,age,birthday,email,mobile," +
                "create_user,create_date,update_user,update_date,isdel,id)" +
                "values(" +
                "?,?,?,?,?,?,?,current_date(),?,current_date(),?,?)";//问号表示下面添加的参数，相当于占位符
        //预编译SQL语句
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //给上面的占位符传参
        ptmt.setString(1,test.getUser_name());
        ptmt.setInt(2,test.getSex());
        ptmt.setInt(3,test.getAge());
        ptmt.setDate(4,new Date(test.getBirthday().getTime()));
        ptmt.setString(5,test.getEmail());
        ptmt.setString(6,test.getMobile());
        ptmt.setString(7,test.getCreate_user());
        ptmt.setString(8,test.getUpdate_user());
        ptmt.setInt(9,test.getIsdel());
        ptmt.setInt(10,test.getId());
        //执行
        ptmt.execute();
    }

    public void updateTest(Test test) throws SQLException {
        //连接数据库
        Connection conn = DBUtil.getConnection();
        //sql语句
        String sql = "" +
                " update java_Test " +
                " set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?, " +
                " update_user=?,update_date=current_date(),isdel=? " +
                " where id=?";//问号表示下面添加的参数，相当于占位符
        //预编译SQL语句
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //给上面的占位符传参
        ptmt.setString(1,test.getUser_name());
        ptmt.setInt(2,test.getSex());
        ptmt.setInt(3,test.getAge());
        ptmt.setDate(4,new Date(test.getBirthday().getTime()));
        ptmt.setString(5,test.getEmail());
        ptmt.setString(6,test.getMobile());
        ptmt.setString(7,test.getUpdate_user());
        ptmt.setInt(8,test.getIsdel());
        ptmt.setInt(9,test.getId());
        //执行
        ptmt.execute();
    }

    public void delTest(Integer id) throws SQLException {
        //连接数据库
        Connection conn = DBUtil.getConnection();
        //sql语句
        String sql = "" +
                " delete from java_Test " +
                " where id=?";//问号表示下面添加的参数，相当于占位符
        //预编译SQL语句
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //给上面的占位符传参
        ptmt.setInt(1,id);
        //执行
        ptmt.execute();
    }

    public List<Test> query() throws SQLException {
        List<Test> test = new ArrayList<Test>();
        //连接数据库
        Connection conn = DBUtil.getConnection();
        //创建sql语句
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select id,user_name,age from java_Test");
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(stringBuilder.toString());
        //执行并产生接口集
        ResultSet resultSet = ptmt.executeQuery();
        Test t = null;
        //遍历接口集
        while (resultSet.next()){
            t = new Test();
            t.setId(resultSet.getInt("id"));
            t.setUser_name(resultSet.getString("user_name"));
            t.setAge(resultSet.getInt("age"));

            test.add(t);
           // System.out.println(resultSet.getString("user_name") + "," + resultSet.getInt("age"));
        }
        return test;
    }

    public List<Test> query(String name) throws SQLException {
        List<Test> result = new ArrayList<Test>();
        Connection conn = DBUtil.getConnection();
        /**
         * 第一种方法
         */
        //第一种方法也可以执行sql语句
//        Statement stmt = conn.createStatement();
//        ResultSet resultSet = stmt.executeQuery("select user_name,age from java_Test");
//
//        List<Test> test = new ArrayList<Test>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from java_Test ");
        stringBuilder.append(" where user_name=? ");
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(stringBuilder.toString());
        ptmt.setString(1,name);
        //执行并产生接口集
        ResultSet rs = ptmt.executeQuery();
        Test test = null;
        while (rs.next()){
            test = new Test();
            test.setId(rs.getInt("id"));
            test.setUser_name(rs.getString("user_name"));
            test.setAge(rs.getInt("age"));
            test.setSex(rs.getInt("sex"));
            test.setBirthday(rs.getDate("birthday"));
            test.setEmail(rs.getString("email"));
            test.setMobile(rs.getString("mobile"));
            test.setCreate_date(rs.getDate("create_date"));
            test.setCreate_user(rs.getString("create_user"));
            test.setUpdate_date(rs.getDate("update_date"));
            test.setUpdate_user(rs.getString("update_user"));
            test.setIsdel(rs.getInt("isdel"));

            result.add(test);
        }
        return result;
    }
    //传入的参数是键值对，Map
    public List<Test> query(List<Map<String, Object>> params) throws SQLException {
        List<Test> result = new ArrayList<Test>();
        Connection conn = DBUtil.getConnection();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from java_Test where 1=1");//小技巧：where 1=1，让后面的and连接的语句能够执行

        if (params != null && params.size() > 0){
            for (int i = 0; i < params.size(); i++) {
                Map<String ,Object> map = params.get(i);
                stringBuilder.append(" and " + map.get("name") + " " + map.get("rela") + " " + map.get("value") + " ");
            }
        }
        System.out.println(stringBuilder.toString());
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(stringBuilder.toString());

        //执行并产生接口集
        ResultSet rs = ptmt.executeQuery();
        Test test = null;
        while (rs.next()){
            test = new Test();
            test.setId(rs.getInt("id"));
            test.setUser_name(rs.getString("user_name"));
            test.setAge(rs.getInt("age"));
            test.setSex(rs.getInt("sex"));
            test.setBirthday(rs.getDate("birthday"));
            test.setEmail(rs.getString("email"));
            test.setMobile(rs.getString("mobile"));
            test.setCreate_date(rs.getDate("create_date"));
            test.setCreate_user(rs.getString("create_user"));
            test.setUpdate_date(rs.getDate("update_date"));
            test.setUpdate_user(rs.getString("update_user"));
            test.setIsdel(rs.getInt("isdel"));

            result.add(test);
        }
        return result;
    }

    public Test get(Integer id) throws SQLException {
        Test test = null;
        //连接数据库
        Connection conn = DBUtil.getConnection();
        //sql语句
        String sql = "" +
                " select * from java_Test " +
                " where id=?";//问号表示下面添加的参数，相当于占位符
        //预编译SQL语句
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //给上面的占位符传参
        ptmt.setInt(1,id);
        //这里不能用excute()命令执行，用executeQuery()产生一个接口集
        ResultSet rs = ptmt.executeQuery();
        //遍历接口集
        while (rs.next()){
            test = new Test();
            test.setId(rs.getInt("id"));
            test.setUser_name(rs.getString("user_name"));
            test.setAge(rs.getInt("age"));
            test.setSex(rs.getInt("sex"));
            test.setBirthday(rs.getDate("birthday"));
            test.setEmail(rs.getString("email"));
            test.setMobile(rs.getString("mobile"));
            test.setCreate_date(rs.getDate("create_date"));
            test.setCreate_user(rs.getString("create_user"));
            test.setUpdate_date(rs.getDate("update_date"));
            test.setUpdate_user(rs.getString("update_user"));
            test.setIsdel(rs.getInt("isdel"));
        }
        return test;
    }
}
