import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhangjd
 * @Date: 2018/9/25 09:21
 * @Description:
 */
public class test {
    public static void main(String[] args) {
        //String reg = ".+(?=</span>)";
        String reg = "\\d+(?=</span>)";
        String test = "<span class=\"read-count\">阅读数：641</span>";
        Pattern pattern = Pattern.compile(reg);
        Matcher mc = pattern.matcher(test);
        while (mc.find()) {
            System.out.println("匹配结果：");
            System.out.println(mc.group());
        }
    }
}
