package org.regex;

import com.alibaba.fastjson.JSONArray;

public class ApplyRegex {
    //static  String json = "[{\"uid\": 34},{\"uid\": 32}]";
    static String json = "{\"uid\": 34},{\"uid\": 32}";


    /**
     * 多选书签，之前各端每发送一次数据只包含一条书签信息，形如{uid:20,neid:109}
     * 现其他端以json形式一次发送多条信息,形如[{},{}]
     * 为了兼容，所以用次正则做判断
     */
    public static void apply() {
        boolean isjson = RegexUtils.isJSON(ApplyRegex.json);
        System.out.println(isjson);
        if (!isjson) {
            json = "[" + json + "]";
        }
        JSONArray jsonArray = JSONArray.parseArray(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.get(i));
        }
    }

    public static void main(String[] args) {
        apply();
    }
}

