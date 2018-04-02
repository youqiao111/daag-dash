package daag.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param s
     * @return 空返回true,非空返回false
     */
    public static boolean isEmpty(String s) {
        if (null == s || s.trim().equals("") || s.trim().equals("null") || s.trim().equals("undefined")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     * @param o
     * @return 空返回true,非空返回false
     */
    public static boolean isEmpty(Object o) {
        if (null == o) {
            return true;
        }
        String s = o.toString();
        if (s.trim().equals("") || s.trim().equals("null") || s.trim().equals("undefined")) {
            return true;
        }
        return false;
    }

    /**
     * 判断int 是否o
     * @param s
     * @return 空返回true,非空返回false
     */
    public static boolean isEmpty(int s) {
        if (s == 0) {
            return true;
        }
        return false;
    }

    /**
     * 全角转半角
     * @param input
     *            String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);
        return returnString;
    }

    /**
     * 半角转全角
     * @param input
     *            String.
     * @return 全角字符串.
     */
    public static String ToSBC(String input) {
        String hz = "";
        if (input.indexOf(".") != -1) {
            hz = input.substring(input.indexOf("."), input.length());
            input = input.substring(0, input.indexOf("."));
        }

        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.print((int) c[i]);
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\47' && c[i] > '\33') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c) + hz;
    }

    /**
     * 解码URL中的中文
     *            ，默认使用UTF-8进行解码
     * @param str
     *            需要解码的字符串
     * @return 解码之后的字符串。如果解码失败，返回解码前的字符串
     */
    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public static String judgeCharCod(String str) {
        String type = null;
        String[] encoding = { "GBK", "GB2312", "ISO8859-1", "UTF-8" };
        for (int i = 0; i < encoding.length; i++) {
            String decode;
            try {
                decode = new String(str.getBytes(encoding[i]));
                String encode = new String(decode.getBytes(), encoding[i]);
                if (encode.equals(str)) {
                    type = encoding[i];
                    break;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return type;
    }

    private final static String[] hex = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12",
                                          "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25",
                                          "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38",
                                          "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B",
                                          "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E",
                                          "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71",
                                          "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84",
                                          "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97",
                                          "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
                                          "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD",
                                          "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0",
                                          "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3",
                                          "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6",
                                          "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };

    private final static byte[]   val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
                                          0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
                                          0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

    /**
     * 编码
     * 
     * @param s
     * @return
     */
    public static String escape(String s) {
        StringBuffer sbuf = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {
                sbuf.append((char) ch);
            } else if (ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch <= 0x007F) {
                sbuf.append('%');
                sbuf.append(hex[ch]);
            } else {
                sbuf.append('%');
                sbuf.append('u');
                sbuf.append(hex[(ch >>> 8)]);
                sbuf.append(hex[(0x00FF & ch)]);
            }
        }
        return sbuf.toString();
    }

    /**
     * 解码 说明：本方法保证 不论参数s是否经过escape()编码，均能得到正确的“解码”结果。另外如果出现了Erro————Parameters:
     * Character decoding failed. 请使用escape(escape(s))来处理在客户端
     * 
     * @param s
     * @return
     */
    public static String unescape(String s) {
        StringBuffer sbuf = new StringBuffer();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int ch = s.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {
                sbuf.append((char) ch);
            } else if (ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch == '%') {
                int cint = 0;
                if ('u' != s.charAt(i + 1)) {
                    cint = (cint << 4) | val[s.charAt(i + 1)];
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    i += 2;
                } else {
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    cint = (cint << 4) | val[s.charAt(i + 3)];
                    cint = (cint << 4) | val[s.charAt(i + 4)];
                    cint = (cint << 4) | val[s.charAt(i + 5)];
                    i += 5;
                }
                sbuf.append((char) cint);
            } else {
                sbuf.append((char) ch);
            }
            i++;
        }
        return sbuf.toString();
    }

    /**
     * 转换字节数组为十六进制字符串
     * 
     * 字节数组
     * @return 十六进制字符串
     */
    @SuppressWarnings("unused")
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /** 将一个字节转化成十六进制形式的字符串 */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * Double类型数据非科学计数法表示
     * 
     * @param obj
     * @return 返回格式化后的Double数据类型
     */
    public static String formatDouble(Object obj) {
        DecimalFormat fmt = new DecimalFormat("###0.00");
        String str = String.valueOf(obj);
        if (obj instanceof Double) {
            return fmt.format(obj);
        } else if (str.matches("^[-\\+]?\\d+(\\.\\d+)?$")) {
            return fmt.format(Double.valueOf(str));
        } else {
            return toStringWithZero(obj);
        }
    }

    /**
     * 转换对象为字符串，如果对象为null，则返回“0”
     * 
     * @param obj
     *            需要转换的对象
     * @return 转换后的字符串
     */
    public static String toStringWithZero(Object obj) {
        return obj == null ? "0" : String.valueOf(obj);
    }

    /**
     * 判断是否数值型
     * 
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        if (string == null || string.equals("")) {
            return false;
        }
        if (string.indexOf(".") == 0 || string.indexOf(".") == string.length() - 1) {
            return false;
        }
        String validateStr = "0123456789.";
        for (int i = 0; i < string.length(); i++) {
            if (validateStr.indexOf(string.substring(i, i + 1)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否整数型
     * 
     * @param string
     * @return
     */
    public static boolean isInteger(String string) {
        if (string == null || string.equals("")) {
            return false;
        }
        String validateStr = "0123456789";
        for (int i = 0; i < string.length(); i++) {
            if (validateStr.indexOf(string.substring(i, i + 1)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将整数转换成字符串(支持数字前补零)
     * 
     * @param number
     *            数字
     * @param length
     *            字符串长度
     * @return String
     * */
    public static String numberToString(int number, int length) {

        String numberStr = String.valueOf(number);

        if (numberStr.length() == length) {
            return numberStr;
        } else if (numberStr.length() > length) {
            return numberStr.substring(numberStr.length() - length);
        }

        StringBuffer value = new StringBuffer();
        for (int i = 0; i < length - numberStr.length(); i++) {
            value.append("0");
        }
        value.append(numberStr);
        return value.toString();
    }

    // 不同编码转换
    public static String formatStringEncoding(String str, String encoding) {
        try {
            return new String(str.getBytes(), encoding);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();

        }
        return str;
    }

    /**
     * 全角转半角
     * 
     * @param str
     * @return
     */
    public static final String QBchange(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * String转换Long[]
     * 
     * @param
     * @return
     */
    public static Long[] strChange(String str) {
        String[] list = str.split(",");
        Long[] list1 = new Long[list.length];
        for (int i = 0; i < list.length; i++) {
            list1[i] = Long.valueOf(list[i]);
        }
        return list1;
    }

    /**
     * 获取uuid
     * 
     * @param
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // 十六进制下数字到字符的映射数组
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


    /**
     * 获取字符串长度
     * 
     * @param s
     * @return
     */
    public static int getWordCount(String s) {
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    public static String convert(String s) throws Exception {
        byte[] temp = s.getBytes("ISO-8859-1");
        String result = new String(temp, "UTF-8");
        return result;
    }

    public static String Utf8ConvertIso(String s) throws Exception {
        byte[] temp = s.getBytes("UTF-8");
        String result = new String(temp, "ISO-8859-1");
        return result;
    }

    public static String nullToString(String s) {
        if (null == s || "".equals(s.trim()) || "null".equals(s)) {
            return "";
        }
        return s;
    }

    public static String nullToString(Object s) {
        if (null == s || "".equals(s.toString().trim()) || "null".equals(s)) {
            return "";
        }
        return s.toString();
    }

    public static int nullToInt(Integer i) {
        if (null == i) {
            return 0;
        }
        return i;
    }

    //校验字符串是否为空
    public static boolean validateNull(Object str) {
        if (null == str || "".equals(str.toString().trim()) || "null".equalsIgnoreCase(str.toString().trim())
            || "\"null\"".equalsIgnoreCase(str.toString().trim()))
            return true;
        return false;
    }

    // 产生6位长度的验证码
    public static String getCheckCode() {
        String checkCode = "";
        for (int i = 0; i < 6; i++) {
            String code = (int) Math.floor(Math.random() * 10) + "";
            checkCode += code;
        }
        return checkCode;
    }



    public static String delHtml(String str) {
        // 配置html标记。
        Pattern p = Pattern.compile("<(\\S*?)[^>]*>.*?| <.*? />");
        Matcher m = p.matcher(str);
        String rs = new String(str);

        // 找出所有html标记。
        while (m.find()) {
            rs = rs.replace(m.group(), "");// 删除html标记。
        }
        rs = rs.replace("\n", "").replace("\t", "").replace("\r", "");
        rs = rs.replace("&quot;", "'").replace("&nbsp;", " ");
        return rs;
    }

    /**
     * String的编码格式转为utf-8
     * @param s
     * @return
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * Descript:为用逗号分开的字符串添加引号，能在sql语句中直接使用
     * Created on：2015-1-12  上午10:09:05
     * Author：caox
     * @param oString
     * @return
     */
    public static String getSqlString(String oString) {
        String dString = "";
        String[] split = oString.split(",");
        for (String string : split) {
            if (StringUtil.isNotEmpty(string)) {
                string = "\'" + string + "\',";
                dString = dString + string;
            }
        }
        dString = dString.substring(0, dString.length() - 1);
        return dString;
    }

    /**
     * Descript:传入流水起始字符串，返回随机流水号
     * Created on：2015-1-12  上午10:07:12
     * Author：caox
     * @param startNo
     * @return
     */
    public static String getSerialNumber(String startNo) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String resultTime = format.format(date);

        // 生成3位随机码
        int randomNum = (int) (Math.random() * 900) + 100;
        // 交易流水号，格式：000+当前年月日时分秒+三位随机码
        String resultSn = startNo + resultTime + randomNum;
        return resultSn;
    }

    /**
     * Descript:拼接卡号
     * Created on：2015-1-7  下午6:05:01
     * Author：caox
     * @param cardNo
     * @return
     */
    public static String getCardNo(String cardNo) {
        if (StringUtil.isNotEmpty(cardNo)) {
            String dcardNo = ";;" + cardNo + "=1009?";
            return dcardNo;
        }
        return cardNo;
    }

    /**
     * Descript:拼接排序字段字符串
     * Created on：2014-12-29  上午10:53:46
     * Author：caox
     * @param prop
     * @return
     */
    public static String transColumn(String prop) {
        StringBuffer prop1 = new StringBuffer(prop);
        String descProp = null;
        if (prop1.indexOf("_") == -1) {
            for (int i = 0; i < prop1.length(); i++) {
                char c = prop1.charAt(i);
                if (Character.isUpperCase(c)) {
                    prop1.insert(i, "_");
                    i++;
                }
            }
            descProp = prop1.toString();
        } else {
            descProp = prop;
        }
        return descProp;
    }

    /**
     * 判断给定的参数是否为空,当且仅当所有的参数都不为空时才返回true
     * 
     * @param values
     *            给定的不定长参数
     * @return value ==null || "".equals(value) 返回false,当且仅当所有的参数都不为空时才返回true
     */
    public static boolean isNotEmpty(Object... values) {
        for (Object value : values) {
            if (null == value) {
                return false;
            }
            if (value instanceof List) {

                if (((List) value).size() <= 0) {
                    return false;
                }
            } else if (value instanceof String) {
                if ("".equals(value)) {
                    return false;
                }
                if ("".equals(((String) value).trim())) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 判断两个值是否相等，可支持字符串和double类型的比较
     * 
     * @param value1
     *            参数1，数据类型以此为参照
     * @param value2
     *            参数2
     * @return <ul>
     *         <li>1、如果其中有一个null则返回false</li>
     *         <li>2、如果value1是double类型则调用Double.compare()方法比较两个double值是否相等</li>
     *         <li>3、如果value1是String类型则调用String.equals()方法比较两个String值是否相等</li>
     *         <li>4、如果value1是int类型则将value2转换为int类型然后比较两个int值是否相等/></li>
     *         <li>5、其他类型直接比较value1==value2</li> </li>
     *         <ul>
     * 
     */
    public static boolean equals(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1 instanceof Double) {
            return Double.compare((Double) value1, Double.valueOf(String.valueOf(value2))) == 0;

        } else if (value1 instanceof String) {
            return value1 == value2 || ((String) value1).equals(value2);
        } else if (value1 instanceof Integer) {
            return (Integer) value1 == Integer.parseInt(String.valueOf(value2));
        } else {
            return value1.equals(value2);
        }
    }

    /**
     * 判断两个值是否相等，可支持字符串(忽略大小写)和double类型的比较
     * 
     * @param value1
     *            参数1，数据类型以此为参照
     * @param value2
     *            参数2
     * @return <ul>
     *         <li>1、如果其中有一个null则返回false</li>
     *         <li>2、如果value1是double类型则调用Double.compare()方法比较两个double值是否相等</li>
     *         <li>3、如果value1是String类型则调用String.equals()方法比较两个String值是否相等</li>
     *         <li>4、如果value1是int类型则将value2转换为int类型然后比较两个int值是否相等/></li>
     *         <li>5、其他类型直接比较value1==value2</li> </li>
     *         <ul>
     * 
     */
    public static boolean equalsIgnoreCase(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1 instanceof Double) {
            return Double.compare((Double) value1, Double.valueOf(String.valueOf(value2))) == 0;

        } else if (value1 instanceof String) {
            return value1 == value2 || ((String) value1).equalsIgnoreCase((String) value2);
        } else if (value1 instanceof Integer) {
            return (Integer) value1 == Integer.parseInt(String.valueOf(value2));
        } else {
            return value1.equals(value2);
        }
    }

    /**
     * 将给定的字符串按","分隔成list
     * 
     * @param groups
     *            给定的字符串，形如'a,b,c,d'
     * @return List，如果给定的字符串为空，则返回null
     */
    public static List<String> splitToList(String groups) {

        return splitToList(groups, ",");
    }

    /**
     * 将给定的List中第一个字符串按","分隔成list
     * 
     * @param groups
     * @return
     */
    public static List<String> splitToList(List<String> groups) {
        if (groups != null && isNotEmpty(groups.get(0))) {
            return splitToList(groups.get(0), ",");
        } else {
            return null;
        }

    }

    /**
     * 将字符串按给定的分隔符分隔成list
     * 
     * @param groups
     *            要分隔的字符串
     * @param split 分隔符
     * @return List，如果给定的字符串为空，则返回null
     */
    public static List<String> splitToList(String groups, String split) {
        List<String> list = null;
        if (StringUtil.isNotEmpty(groups)) {

            list = Arrays.asList(groups.split(split));
        }
        return list;
    }

    /**
     * 返回给定的值是否在集合之中
     * @param arg 要判断的参数
     * @param values 范围
     * @return 要判断的值与给定的值中的某一个相等则返回true，当且仅当不在集合内返回false
     */
    public static boolean isArgIn(String arg, String... values) {
        if (isNotEmpty(arg)) {
            for (String value : values) {
                if (arg.equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 返回给定的值是否在集合之中
     * @param arg 要判断的参数
     * @param values 范围
     * @return 要判断的值与给定的值中的某一个相等则返回true，当且仅当不在集合内返回false
     */
    public static boolean isArgInIgnoreCase(String arg, String... values) {
        if (isNotEmpty(arg)) {
            for (String value : values) {
                if (arg.equalsIgnoreCase(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 返回values是否为给定值的一部分
     * @param arg 要判断的参数
     * @param values 范围
     * @return 要判断的值与给定的值中的某一个相等则返回true，当且仅当不在集合内返回false
     */
    public static boolean isArgIncludeValues(String arg, String... values) {
        if (isNotEmpty(arg)) {
            for (String value : values) {
                if (arg.indexOf(value) != -1) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取WEB-INF文件夹的绝对路径
     * @return
     */
    public static String getWebContentPath() {
        String path = StringUtil.class.getResource("/").getPath();
        if (path.startsWith("/") && isWindows()) {
            path = path.substring(1);
        }
        if (path.endsWith("WEB-INF/classes/")) {
            path = path.substring(0, path.length() - "WEB-INF/classes/".length());
        }
        if (path.indexOf("%20") != -1) {
            path = path.replace("%20", " ");
        }
        return path;
    }

    /**
     * 比较两个String值的大小
     * @param value1
     * @param value2
     * @return
     */
    //    public static int compareTo(String value1,String value2){
    //        return Double.compare(NumberUtil.str2Double(value1),NumberUtil.str2Double(value2));
    //    }

    /**
     * 判断字符串是否在给定的范围之内
     * @param arg
     * @param from
     * @param end
     * @return
     */
    //    public static boolean isArgsInRange(String arg,String from,String end){
    //        if(!isNotEmpty(arg)){
    //            return false;
    //        }
    //        if(isArgIn(arg, from,end)){
    //            return true;
    //        }
    //        
    //        if(compareTo(arg,from)>=0&&compareTo(arg,end)<=0){
    //            return true;
    //        }
    //        return false;
    //    }

    /**
     * 判断系统是否windows，windows返回true，否则返回false
     * @return
     */
    public static boolean isWindows() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 让文字自动换行
     * @param str 显示的字符串内容
     * @param width 一行显示几个字符
     * @author zhaowy
     * @date 2012.11.29
     */
    public static String getMultiRowTxt(String str, int width) {
        if (str == null)
            return "";
        if (width <= 0)//数字太小
            width = 6;
        int len = str.length();
        if (len <= width)
            return str;
        String strR = "";
        int nn = len / width;
        for (int i = 0; i < nn; i++) {
            strR += str.substring(i * width, (i + 1) * width) + "<br/>";
        }
        if (len > nn * width)
            strR += str.substring(nn * width);
        return strR;
    }

    /**
     * 如果给定的变量为null或者为空则用给定的值赋值
     * @param arg1
     * @param value
     * @return
     */
    public static String setValuesIfNull(String arg1, String value) {
        if (!isNotEmpty(arg1)) {
            arg1 = value;
        }
        return arg1;
    }

    /**
     * 返回给定变量是否以给定的值中的一个结尾
     * @param arg1
     * @param values
     * @return
     */
    public static boolean isEndWith(String arg1, String... values) {
        for (String value : values) {
            if (arg1.endsWith(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断参数是否为空
     * @param str
     * @return boolean
     */
    public static boolean isNull(String str) {
        if (str == null || str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * 将字符串转为Double
     * @param str
     * @return
     */
    public static Double toDouble(String str) {
        if (str == null || str.equals(""))
            return null;
        else
            return Double.parseDouble(str);
    }

    /**
     * 判断参数是否为空
     * @param obj
     * @return boolean
     * @author wangzhibin
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString().trim());
    }

    /**
     * 判断参数是否为空,为空则返回"";
     * @param obj
     * @return String
     * @author wangzhibin
     */
    public static String toNotNull(Object obj) {
        if (isNullOrEmpty(obj))
            return "";
        return obj.toString();
    }

    public static String toString(Object obj) {
        if (obj == null)
            return "null";
        return obj.toString();
    }

    public static String join(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

    public static String appendAddKG(String ob) {
        return String.valueOf("  " + ob + "  ");
    }

    /* public static void main(String[] args) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            formatDouble("20.998764212122", 4);
        }
        System.out.println(System.currentTimeMillis() - time);
    }*/
}
