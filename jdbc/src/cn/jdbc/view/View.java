package cn.jdbc.view;

import cn.jdbc.action.TestAction;
import cn.jdbc.model.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final String CONTEXT="欢迎来到女神禁区：\n" +
            "下面是女神禁区的功能列表：\n" +
            "[MAIN/M]:主菜单\n" +
            "[QUERY/Q]:查看全部女神的信息\n" +
            "[GET/G]:查看某位女神的详细信息\n" +
            "[ADD/A]:添加女神信息\n" +
            "[UPDATE/U]:更新女神信息\n" +
            "[DELETE/D]:删除女神信息\n" +
            "[SEARCH/S]:查询女神信息(根据姓名、手机号来查询)\n" +
            "[EXIT/E]:退出女神禁区\n" +
            "[BREAK/B]:退出当前功能，返回主菜单";

    private static final String OPERATION_MAIN="MAIN";
    private static final String OPERATION_QUERY="QUERY";
    private static final String OPERATION_GET="GET";
    private static final String OPERATION_ADD="ADD";
    private static final String OPERATION_UPDATE="UPDATE";
    private static final String OPERATION_DELETE="DELETE";
    private static final String OPERATION_SEARCH="SEARCH";
    private static final String OPERATION_EXIT="EXIT";
    private static final String OPERATION_BREAK="BREAK";

    public static void main(String[] args) {
        System.out.println(CONTEXT);

        Scanner input =new Scanner(System.in);
        Test test = new Test();
        TestAction action = new TestAction();
        //标记
        String prenious = null;
        Integer step = 1;
        while (input.hasNext()){
            String in = input.next().toString();
            if (OPERATION_EXIT.equals(in)||
                    OPERATION_EXIT.substring(0,1).equals(in.toUpperCase())){
                System.out.println("您已成功退出女神禁区。");
                break;
            }else if (OPERATION_QUERY.equals(in)||
                    OPERATION_QUERY.substring(0,1).equals(in.toUpperCase())){
                try {
                   List<Test> list = action.query();
                    for (Test t : list) {
                        System.out.println(t.getId() + ",姓名：" + t.getUser_name());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (OPERATION_ADD.equals(in)||
                    OPERATION_ADD.substring(0,1).equals(in.toUpperCase())||
                    OPERATION_ADD.equals(prenious)){
                prenious = OPERATION_ADD;
                //新增
                if (1 == step){
                    System.out.println("请输入女神的【姓名】");
                }else if (2 == step){
                    test.setUser_name(in);
                    System.out.println("请输入女神的【年龄】");
                }else if (3 == step){
                    test.setAge(Integer.valueOf(in));
                    System.out.println("请输入女神的【生日】，格式如：yyyy-MM-dd");
                }else if (4 == step){
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthday = null;
                    try {
                        birthday = sf.parse(in);
                        test.setBirthday(birthday);
                        System.out.println("请输入女神的【邮箱】");
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("您输入的格式有误，请重新输入");
                        step = 3;
                    }
                }else if (5 == step){
                    test.setEmail(in);
                    System.out.println("请输入女神的【手机号】");
                }else if (6 == step){
                    test.setMobile(in);
                    try {
                        action.add(test);
                        System.out.println("新增女神成功");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("新增女神失败");
                    }
                }
                if (OPERATION_ADD.equals(prenious)){
                    step++;
                }

            }else {
                System.out.println("您输入的值为：" + input.next().toString());
            }

        }
    }
}
