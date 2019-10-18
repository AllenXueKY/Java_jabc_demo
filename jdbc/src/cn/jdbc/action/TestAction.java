package cn.jdbc.action;

import cn.jdbc.dao.TestDao;
import cn.jdbc.model.Test;

import java.sql.SQLException;
import java.util.*;

public class TestAction {
    public void add(Test test) throws SQLException {
        TestDao dao = new TestDao();
        dao.addTest(test);
    }
    public Test get(Integer id) throws SQLException {
        TestDao dao = new TestDao();
        return dao.get(id);
    }
    public void edit(Test test) throws SQLException {
        TestDao dao = new TestDao();
        dao.updateTest(test);
    }
    public void del(Integer id) throws SQLException {
        TestDao dao = new TestDao();
        dao.delTest(id);
    }
    public List<Test>  query() throws SQLException {
        TestDao dao = new TestDao();
        return dao.query();
    }
    public List<Test> query(List<Map<String,Object>> params) throws SQLException {
        TestDao dao = new TestDao();
        return dao.query(params);
    }
//    public static void main(String[] args) throws SQLException {
//        TestDao t = new TestDao();
////        List<Test> result = t.query("鱼鱼");
//
//        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
//        Map<String, Object> param = new HashMap<>();
//        param.put("name","user_name");
//        param.put("rela","like");
//        param.put("value","'%宣%'");
//        params.add(param);
//        param = new HashMap<>();
//        param.put("name","mobile");
//        param.put("rela","like");
//        param.put("value","'%5201314%'");
//        params.add(param);
//        List<Test> result = t.query(params);
//        for (int i = 0; i < result.size();i++){
//            System.out.println(result.get(i).toString());
//        }
//        Test t1 = new Test();
//        t1.setUser_name("鱼鱼");
//        t1.setAge(20);
//        t1.setSex(1);
//        t1.setBirthday(new Date());
//        t1.setEmail("yy@163.com");
//        t1.setMobile("13052013140");
//        t1.setCreate_user("ADMIN");
//        t1.setUpdate_user("ADMIN");
//        t1.setIsdel(1);
//        t1.setId(1);
////        Test t2 = t.get(3);
////        System.out.println(t2.toString());
////        t.delTest(2);
////        t.updateTest(t1);
////       t.addTest(t1);
//        List<Test> test = t.query();
//
//        for (Test tests : test) {
//            System.out.println(tests.getUser_name() + "," + tests.getAge());
//        }
//    }
}
