package com.octopus.report;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2017-12-29
 * Time: 10:07
 */
public class RegexTest {
    @Test
    public void testRegex(){
        String sql = "select username,password from tb_test where username=@Username and dsfjhsdk=@Username";
        Map<String,String> map = new HashMap<>();
        map.put("Username","user");
        map.put("Password","pwd");
        Set<Map.Entry<String, String>> entryseSet=map.entrySet();
        //String[] params=new String[entryseSet.size()];
        List<String> paramsList = new ArrayList<>();


        String regex = "(@([a-zA-Z]+))(,|\\s|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        Boolean find = matcher.find();
        while (find) {
            String placeholder = matcher.group(1);
            String column = matcher.group(2);
            sql = sql.replaceFirst(placeholder,"?");
            paramsList.add(map.get(column));
            matcher = pattern.matcher(sql);
            find = matcher.find();
        }
        String[] params=paramsList.toArray(new String[paramsList.size()]);

        System.out.println(sql);
        for (int i = 0; i <params.length ; i++) {
            System.out.println(params[i]);
        }
    }
}
