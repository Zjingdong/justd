package org.deviceType;

import com.alibaba.fastjson.JSONArray;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentUtils {

    /**
     * 根据UserAgent获取对象
     * @param userAgent
     * @return
     */
    public UserAgent getUserAgent(String userAgent){
        return UserAgent.parseUserAgentString(userAgent);
    }

    /**
     * 获取操作系统信息
     * @param userAgent
     * @return
     */
    public JSONArray getOS(UserAgent userAgent){
        JSONArray jsonArray = new JSONArray();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();

        jsonArray.add(operatingSystem.getDeviceType());
        jsonArray.add(operatingSystem.getGroup());
        jsonArray.add(operatingSystem.getId());
        jsonArray.add(operatingSystem.getManufacturer());
        jsonArray.add(operatingSystem.getName());
        jsonArray.add(operatingSystem.getDeclaringClass());
        return jsonArray;
    }





    public JSONArray BrowserInfo(UserAgent userAgent){
        JSONArray jsonArray = new JSONArray();
        Browser browser = userAgent.getBrowser();
        jsonArray.add(browser.getBrowserType());
        jsonArray.add(browser.getGroup());
        jsonArray.add(browser.getId());
        jsonArray.add(browser.getManufacturer());
        jsonArray.add(browser.getRenderingEngine());
        jsonArray.add(browser.getName());
        jsonArray.add(browser.getDeclaringClass());
        return jsonArray;
    }

}
