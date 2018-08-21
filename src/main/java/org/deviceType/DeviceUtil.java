package org.deviceType;

import eu.bitwalker.useragentutils.UserAgent;

public class DeviceUtil {
    public static String BROWSER360 = "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; InfoPath.2; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; 360SE) ";
    public static String BROWSERIE = "User-Agent:Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0 ";
    public static String BROWSERCHROME = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
    public static String BROWSERLIEBAO = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11";

    public static void getDeviceInfo(String agentString){
         UserAgentUtils userAgentUtils = new UserAgentUtils();
        UserAgent userAgent = userAgentUtils.getUserAgent(agentString);
        System.out.println(userAgentUtils.BrowserInfo(userAgent));
        System.out.println(userAgentUtils.getOS(userAgent));
    }

    public static void main(String[] args) {
        getDeviceInfo(BROWSERLIEBAO);
    }
}
