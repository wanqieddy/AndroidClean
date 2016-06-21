package com.eddy.util;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuj on 2016/5/20.
 */
public class StringUtils {

    public static String getUserAgent(Context context) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Tga/");
        String version = getVersionName(context);
        buffer.append(version);
        buffer.append(" Android(");
        String model = encodeChineseStr(Build.MODEL);
        buffer.append(model + "; ");
        String fingerprint = encodeChineseStr(Build.FINGERPRINT);
        buffer.append(fingerprint);
        buffer.append(")");
        return buffer.toString();
    }


    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 转码中文字符串
     *
     * @param srcStr
     * @return
     */
    public static String encodeChineseStr(String srcStr) {
        String dstStr = srcStr;
        if (!TextUtils.isEmpty(dstStr)) {
            if (srcStr.length() < srcStr.getBytes().length) {
                try {
                    dstStr = URLEncoder.encode(dstStr, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return dstStr;
    }


    /*
     * 字符拆分成数组
     */
    public static String getStrSplitByCondition(String str, String split, String condition) {

        String result = "";
        if(str != null && split != null && condition != null){
            String[] cookieArr = str.split(split);

            for (int i = 0; i < cookieArr.length; i++) {
                if (cookieArr[i].contains(condition)) {
                    return cookieArr[i];
                }
            }
        }

        return result;
    }

    /*
     * MD5 加密
     */
    public static String getSign(String signStr) {

        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signStr.getBytes());
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String sign = buf.toString();

        return sign;
    }


    /**
     * @param obj
     * @return 拼接后的字符串
     */
    public static String copy(Object... obj) {
        StringBuffer mStringBuffer = new StringBuffer();
        for (int i = 0; i < obj.length; i++) {
            mStringBuffer.append(obj[i]);
        }
        return mStringBuffer.toString();
    }

    /**
     * 替换字符串
     *
     * @param strSc  需要进行替换的字符串
     * @param oldStr 源字符串
     * @param newStr 替换后的字符串
     * @return 替换后对应的字符串
     * @since 1.1
     */
    public static String replace(String strSc, String oldStr, String newStr) {
        String ret = strSc;
        if (ret != null && oldStr != null && newStr != null) {
            ret = strSc.replaceAll(oldStr, newStr);
        }
        return ret;
    }

    public static boolean isContain(String strSc, String str, String splitStr) {
        String split = ",";
        if (!isNull(splitStr)) {
            split = splitStr;
        }
        if (!isNull(strSc, str)) {
            String[] strs = strSc.split(split);
            for (String newStr : strs) {
                if (newStr.trim().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断多个参数是否都为空
     *
     * @param strArray
     * @return
     */
    public static boolean isNull(Object... strArray) {
        boolean result = false;
        for (Object str : strArray) {
            if (isEmpty(str)) {
                result = true;
                break;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * 判断多个参数是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return ("").equals(str) || str == null;
    }

    /**
     * 替换字符串，修复java.lang.String类的replaceAll方法时第一参数是字符串常量正则时(如："address".
     * replaceAll("dd","$");)的抛出异常：java.lang.StringIndexOutOfBoundsException:
     * String index out of range: 1的问题。
     *
     * @param strSc  需要进行替换的字符串
     * @param oldStr 源字符串
     * @param newStr 替换后的字符串
     * @return 替换后对应的字符串
     * @since 1.2
     */
    public static String replaceAll(String strSc, String oldStr, String newStr) {
        int i = -1;
        while ((i = strSc.indexOf(oldStr)) != -1) {
            strSc = new StringBuffer(strSc.substring(0, i)).append(newStr)
                    .append(strSc.substring(i + oldStr.length())).toString();
        }
        return strSc;
    }

    /**
     * 将字符串转换成HTML格式的字符串
     *
     * @param str 需要进行转换的字符串
     * @return 转换后的字符串
     * @since 1.1
     */
    public static String toHtml(String str) {
        String html = str;
        if (str == null || str.length() == 0) {
            return "";
        } else {
            html = replace(html, "&", "&amp;");
            html = replace(html, "<", "&lt;");
            html = replace(html, ">", "&gt;");
            html = replace(html, "\r\n", "\n");
            html = replace(html, "\n", "<br>\n");
            html = replace(html, "\"", "&quot;");
            html = replace(html, " ", "&nbsp;");
            return html;
        }
    }

    /**
     * 将HTML格式的字符串转换成常规显示的字符串
     *
     * @param str 需要进行转换的字符串
     * @return 转换后的字符串
     * @since 1.1
     */
    public static String toText(String str) {
        String text = str;
        if (str == null || str.length() == 0) {
            return "";
        } else {
            text = replace(text, "&amp;", "&");
            text = replace(text, "&lt;", "<");
            text = replace(text, "&gt;", ">");
            text = replace(text, "<br>\n", "\n");
            text = replace(text, "<br>", "\n");
            text = replace(text, "&quot;", "\"");
            text = replace(text, "&nbsp;", " ");
            text = replace(text, "&ldquo;", "“");
            text = replace(text, "&rdquo;", "”");
            return text;
        }
    }

    /**
     * 将一字符串数组以某特定的字符串作为分隔来变成字符串
     *
     * @param strs  字符串数组
     * @param token 分隔字符串
     * @return 以token为分隔的字符串
     * @since 1.0
     */
    public static String join(String[] strs, String token) {
        if (strs == null)
            return null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            if (i != 0)
                sb.append(token);
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    /**
     * 将一字符串以某特定的字符串作为分隔来变成字符串数组
     *
     * @param str   需要拆分的字符串("@12@34@56")
     * @param token 分隔字符串("@")
     * @return 以token为分隔的拆分开的字符串数组
     * @since 1.0
     */
    public static String[] split(String str, String token) {
        String temp = str.substring(1, str.length());
        return temp.split(token);
    }

    /**
     * 验证字符串合法性
     *
     * @param str  需要验证的字符串
     * @param test 非法字符串（如："~!#$%^&*()',;:?"）
     * @return true:非法;false:合法
     * @since 1.0
     */
    public static boolean check(String str, String test) {
        if (str == null || str.equals(""))
            return true;
        boolean flag = false;
        for (int i = 0; i < test.length(); i++) {
            if (str.indexOf(test.charAt(i)) != -1) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 将数值型字符串转换成Integer型
     *
     * @param str 需要转换的字符型字符串
     * @param ret 转换失败时返回的值
     * @return 成功则返回转换后的Integer型值；失败则返回ret
     * @since 1.0
     */
    public static Integer String2Integer(String str, Integer ret) {
        if (TextUtils.isEmpty(str)) {
            return ret;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return ret;
        }
    }

    public static long getLongValue(Object o, long defaultValue) {
        if (!isNull(o)) {
            try {
                return Long.parseLong(String.valueOf(o));
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    /**
     * 将数值型转换成字符串
     *
     * @param it  需要转换的Integer型值
     * @param ret 转换失败的返回值
     * @return 成功则返回转换后的字符串；失败则返回ret
     * @since 1.0
     */
    public static String Integer2String(Integer it, String ret) {
        try {
            return Integer.toString(it);
        } catch (NumberFormatException e) {
            return ret;
        }
    }

    /**
     * 比较两字符串大小(ASCII码顺序)
     *
     * @param str1 参与比较的字符串1
     * @param str2 参与比较的字符串2
     * @return str1>str2:1;str1<str2:-1;str1=str2:0
     * @since 1.1
     */
    public static int compare(String str1, String str2) {//
        if (str1.equals(str2)) {
            return 0;
        }
        int str1Length = str1.length();
        int str2Length = str2.length();
        int length = 0;
        if (str1Length > str2Length) {
            length = str2Length;
        } else {
            length = str1Length;
        }
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                return 1;
            }
        }
        return -1;
    }

    /**
     * 将阿拉伯数字的钱数转换成中文方式
     *
     * @param num 需要转换的钱的阿拉伯数字形式
     * @return 转换后的中文形式
     * @since 1.1
     */
    public static String num2Chinese(double num) {
        String result = "";
        String str = Double.toString(num);
        if (str.contains(".")) {
            String begin = str.substring(0, str.indexOf("."));
            String end = str.substring(str.indexOf(".") + 1, str.length());
            byte[] b = begin.getBytes();
            int j = b.length;
            for (int i = 0, k = j; i < j; i++, k--) {
                result += getConvert(begin.charAt(i));
                if (!"零".equals(result.charAt(result.length() - 1) + "")) {
                    result += getWei(k);
                }
                System.out.println(result);

            }
            for (int i = 0; i < result.length(); i++) {
                result = result.replaceAll("零零", "零");
            }
            if ("零".equals(result.charAt(result.length() - 1) + "")) {
                result = result.substring(0, result.length() - 1);
            }
            result += "元";
            byte[] bb = end.getBytes();
            int jj = bb.length;
            for (int i = 0, k = jj; i < jj; i++, k--) {
                result += getConvert(end.charAt(i));
                if (bb.length == 1) {
                    result += "角";
                } else if (bb.length == 2) {
                    result += getFloat(k);
                }
            }
        } else {
            byte[] b = str.getBytes();
            int j = b.length;
            for (int i = 0, k = j; i < j; i++, k--) {
                result += getConvert(str.charAt(i));
                result += getWei(k);
            }
        }
        return result;
    }

    public static String getString(String str, int count) {
        if (!(str.charAt(count - 1) >= 'a' && str.charAt(count - 1) <= 'z' || str.charAt(count - 1) >= 'A' && str.charAt(count - 1) <= 'Z'))
            return str.substring(0, count - 1);
        else
            return str.substring(0, count);
    }

    private static String getConvert(char num) {
        if (num == '0') {
            return "零";
        } else if (num == '1') {
            return "一";
        } else if (num == '2') {
            return "二";
        } else if (num == '3') {
            return "三";
        } else if (num == '4') {
            return "四";
        } else if (num == '5') {
            return "五";
        } else if (num == '6') {
            return "六";
        } else if (num == '7') {
            return "七";
        } else if (num == '8') {
            return "八";
        } else if (num == '9') {
            return "九";
        } else {
            return "";
        }
    }

    private static String getFloat(int num) {
        if (num == 2) {
            return "角";
        } else if (num == 1) {
            return "分";
        } else {
            return "";
        }
    }

    private static String getWei(int num) {
        if (num == 1) {
            return "";
        } else if (num == 2) {
            return "十";
        } else if (num == 3) {
            return "百";
        } else if (num == 4) {
            return "千";
        } else if (num == 5) {
            return "万";
        } else if (num == 6) {
            return "十";
        } else if (num == 7) {
            return "百";
        } else if (num == 8) {
            return "千";
        } else if (num == 9) {
            return "亿";
        } else if (num == 10) {
            return "十";
        } else if (num == 11) {
            return "百";
        } else if (num == 12) {
            return "千";
        } else if (num == 13) {
            return "兆";
        } else {
            return "";
        }
    }

    /**
     * 将字符串的首字母改为大写
     *
     * @param str 需要改写的字符串
     * @return 改写后的字符串
     * @since 1.2
     */
    public static String firstToUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 判断list中是否包含某一个字符串
     *
     * @param str1
     * @return
     */
    public static boolean listContain(List list, String str1) {
        if (list == null || list.size() == 0) {
            return false;
        } else {
            return list.contains(str1);
        }
    }

    /**
     * list转String
     *
     * @param list
     * @param sign 分隔符号
     * @return
     */
    public static String List2String(List<String> list, String sign) {
        if (list == null || list.size() == 0)
            return null;
        StringBuffer sb = new StringBuffer();
        for (String string : list) {
            sb.append(string).append(sign);
        }
        return sb.substring(0, sb.length() - 1).toString();
    }

    /**
     * String转list 去除null 空串
     *
     * @param target
     * @param sign   分隔符号
     * @return
     */
    public static List<String> String2List(String target, String sign) {
        List<String> usersList = new ArrayList<String>();
        if (!isEmpty(target)) {
            String[] vs = target.split(sign);
            for (int i = 0; i < vs.length; i++) {
                if (!isEmpty(vs[i]))
                    usersList.add(vs[i]);
            }
        }
        return usersList;
    }

    public static String escapeHtmlSign(String value) {
        if (value == null)
            return null;

        if (value instanceof String) {
            String result = value.toString();
            // "'<>&
            result = result.replaceAll("&", "&amp;").replaceAll(">", "&gt;")
                    .replaceAll("<", "&lt;").replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#39;");
            return result;
        } else {
            return value.toString();
        }
    }

    public static String unEscapeHtmlSign(String value) {
        if (value == null)
            return null;

        if (value instanceof String) {
            String result = value.toString();
            // "'<>&
            result = result.replaceAll("&amp;", "&").replaceAll("&gt;", ">")
                    .replaceAll("&lt;", "<").replaceAll("&quot;", "\"")
                    .replaceAll("&#39;", "'");
            return result;
        } else {
            return value.toString();
        }
    }

    /**
     * 根据Resource ID获取字符串
     *
     * @param resId
     * @return
     */
    public static String getStringFromId(Application app, int resId) {
        return app.getString(resId);
    }

    /**
     * 将浮点数进行四舍五入
     *
     * @return 改写后的字符串
     */
    public static String doubleToString(double str) {
        return doubleToString(str, 2);
    }


    public static String formatNum(float num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(num);
    }

    public static String numToString(int str) {
        return doubleToString(str, 2);
    }

    public static String doubleToString(double str, int offset) {
        return new BigDecimal(str + "").setScale(offset,
                BigDecimal.ROUND_HALF_UP).toString();
    }

    public static Date stringDateTodate(String date) {
        String time = date.substring(6, date.length() - 7);
        return new Date(Long.parseLong(time));
    }


    /**
     * 去除字符串前后的空格
     *
     * @param text
     * @return
     */
    public static String trimString(String text) {
        if (!TextUtils.isEmpty(text)) {
            text = text.replaceAll("[ |　]", " ").trim(); //替换全角空格为半角，然后过滤
        }
        return text;
    }

    /**
     * 去除转义
     *
     * @param text
     * @return
     */
    public static String escapeString(String text) {
        try {
            if (!TextUtils.isEmpty(text)) {
                text = text.replaceAll("[\\n\\r]*", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(text)) text = "";
        return text;
    }

    /**
     * 添加url参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String appendUrlParams(String url, String params) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        Pattern pattern = Pattern.compile("\\?[\\w]*=");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return url + "&" + params;
        } else {
            return url + "?" + params;
        }
    }

    public static void main(String[] args) {
    }

    /**
     * 设置一段不同颜色的文字
     *
     * @param colors：颜色数组，按顺序取，getColor所得的值
     * @param index：切换颜色对应的位置（第几个内容需要变色）
     * @param s：文字数组
     */
    public static SpannableStringBuilder getColorSpan(Integer[] index, int[] colors, CharSequence... s) {
        List<Integer> indexList = Arrays.asList(index);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        int colorIndex = 0;
        for (int i = 0; i < s.length; i++) {
            if (indexList.contains(Integer.valueOf(i))) {
                SpannableString spanString = new SpannableString(s[i]);
                if (colorIndex >= colors.length) {
                    colorIndex = colors.length - 1;
                }
                ForegroundColorSpan span = new ForegroundColorSpan(colors[colorIndex]);
                colorIndex++;
                spanString.setSpan(span, 0, s[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                stringBuilder.append(spanString);

            } else {
                stringBuilder.append(s[i]);
            }
        }
        return stringBuilder;
    }

    /**
     * 字符拆分
     * @param str 原字符
     * @param split 分隔符
     * @return
     */
    public static String getSubStrSplit(String str, String split){

        if (!TextUtils.isEmpty(split) && !TextUtils.isEmpty(str) && str.contains(split)) {
            str = str.substring(0, str.indexOf(split));
        }
        return str;
    }

    /**
     * 截取指定size的String
     */
    public static String resizeContent(String content, int size) {
        StringBuilder resizeContent = new StringBuilder();
        if (!TextUtils.isEmpty(content) && content.length() > size) {
            resizeContent.append(content.substring(0, size));
            resizeContent.append("...");
        } else {
            resizeContent.append(content);
        }
        return resizeContent.toString();
    }
}
