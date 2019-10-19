package cn.jdbc.test;

import cn.jdbc.action.TestAction;
import cn.jdbc.model.Test;

import java.sql.SQLException;
import java.util.*;

/**
 * 测试控制层
 */
public class TestActionDemo {
    public static void main(String[] args) throws SQLException {
        TestAction testAction = new TestAction();
        Test test = new Test();
//
//        test.setUser_name("小园");
//        test.setSex(2);
//        test.setAge(19);
//        test.setBirthday(new Date());
//        test.setEmail("873661417@qq.com");
//        test.setMobile("13572566666");
//        test.setIsdel(0);
//        test.setId(6);
//        testAction.add(test);
//        testAction.edit(test);
//        testAction.del(1);
        List<Map<String,Object>> params = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","user_name");
        map.put("rela","=");
        map.put("value","'宣宣'");

        params.add(map);

        List<Test> result = testAction.query(params);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getId()+
                    ":" +result.get(i).getUser_name() );
        }/*查询*/
    }
}
