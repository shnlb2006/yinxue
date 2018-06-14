package org.yinxueframework.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 *
 * @author zengjian
 * @create 2018-04-11 17:11
 * @since 1.0.0
 */
public class StringUtil {

    /**
     * 从url地址中截取获得连接数据库名称<br>
     * <pre>
     *     url示例：jdbc:mysql://127.0.0.1:3306/family?useAffectedRows=true&useSSL=true
     *     截取规则：从最后 "/"开始截取，如果有"?"截取到"?"; 如果没有截取到末尾
     * </pre>
     *
     * @param url 数据库连接url
     * @return databaseName 数据库名称
     */
    public static String getDatabaseName(String url) {
        String databaseName = null;
        if (url.contains("?")) {
            databaseName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?"));
        } else {
            databaseName = url.substring(url.lastIndexOf("/") + 1);
        }
        return databaseName;
    }

    /**
     * 将表名进行转换处理<br>
     * 规则: 先转换为小写字母，然后将表名中除了小写字母和数字的部分替换为空<br>
     * 例如: temp_01 替换为 temp<br>
     *
     * @param tableName 表名
     * @return key 作为存储于map key的字符串
     */
    public static String convertToKey(String tableName) {
        String key = tableName.trim().toLowerCase();
        Matcher m = Pattern.compile("[^a-z0-9]+").matcher(key);
        while (m.find()) {
            key = key.replaceAll(m.group(), "");
        }
        return key;
    }

    /**
     * 字符串为空返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(final String str) {
        return null == str || "".equals(str.trim());
    }

    /**
     * 字符串不为空返回true
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(final String str) {
        return !isEmpty(str);
    }

    /**
     * 首字母小写 <br>
     * 如: InnerName --> innerName;
     *
     * @param str
     * @return
     */
    public static String firstCharToLowerCase(String str) {
        return str.substring(0, 1).toLowerCase().concat(str.substring(1));
    }


    /**
     * 首字母大写 <br>
     * 如: innerName --> InnerName;
     *
     * @param str
     * @return
     */
    public static String firstCharToUpperCase(String str) {
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }

    // pointSummaryAccountService.delete(pointSummaryAccountEntity.getCustNum(), null, pointSummaryAccountEntity.getPointType(),ss.get(123,321));
    // pointSummaryAccountService.delete(pointSummaryAccountEntity.getCustNum(), ss.get(ssf.get(1,2),321), null, pointSummaryAccountEntity.getPointType()),

    public static String[] extractEntryParam(String methodLine) {
        String subLine = methodLine.substring(methodLine.indexOf("(") + 1, methodLine.lastIndexOf(")"));
        // 采用 ","的方式来进行切分，判断一个","是否有效的条件为该","前是否第一个碰见的符号为( ,那说明是无效的",",继续查询
        List<String> list = new LinkedList<>();
        char[] cs = subLine.toCharArray();
        int cursor = 0;
        for (int i = 0; i < cs.length; i++) {
            int leftBracket = -1;
            int rightBracket = -1;
            if (cs[i] == ',') {
                // 往前寻找，计算( )的数量，如果( )的数量相等说明该,有效，如果不相等，说明为无效,，
                for (int j = i; j > 0; j--) {
                    if (cs[j] == '(') {
                        leftBracket++;
                    } else if (cs[j] == ')') {
                        rightBracket++;
                    }
                }
                if (leftBracket == rightBracket) {
                    list.add(new String(Arrays.copyOfRange(cs, cursor, i)).trim());
                    cursor = i + 1;
                }
            }
        }
        // 循环结束后 cusor已经到了最后的一个","上，那么就可以取得数组最后的部分
        list.add(new String(Arrays.copyOfRange(cs, cursor, cs.length)).trim());
        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        // getDatabaseName("jdbc:mysql://127.0.0.1:3306/family?useAffectedRows=true&useSSL=true");
        // extractEntryParam("pointSummaryAccountService.delete(pointSummaryAccountEntity.getCustNum(), null, pointSummaryAccountEntity.getPointType(),ss.get(123,321));");
        String s = "pointSummaryAccountService.delete(pointSummaryAccountEntity.getCustNum(), ss.get(ssf.get(1,2),321), null, pointSummaryAccountEntity.getPointType())";
        s = "pointSummaryAccountService.delete()";
        extractEntryParam(s);
    }
}
