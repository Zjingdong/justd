package org.one;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Maptest {
    public static void main(String[] args) {
        JSONArray ja= new JSONArray();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("个人",String.valueOf(1));
        jsonObject.put("geren2",String.valueOf(2));
        JSONObject js = new JSONObject();
        js.put("tuandui",String.valueOf(3));
        js.put("tuamdio2",String.valueOf(4));
        JSONObject jss = new JSONObject();
        jss.put("1",jsonObject);
        jss.put("2",js);
        HashMap hm = new HashMap();
        hm.put("geren",jsonObject);
        hm.put("22",js);
        System.out.println(jss);

        People p = new People(10,"33");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("1",p);
        jsonObject1.put("aa","dds");
        System.out.println(jsonObject1);
    }
}
