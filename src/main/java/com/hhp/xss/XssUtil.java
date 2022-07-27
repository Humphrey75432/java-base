package com.hhp.xss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssUtil {

    private static final Pattern[] XSS_PATTERNS = new Pattern[] {
            // Script fragments
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            // src='...'
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // lonely script tags
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // eval(...)
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // expression(...)
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // javascript:...
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            // vbscript:...
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            // 空格英文单双引号
            Pattern.compile("[\\s\'\"]+", Pattern.CASE_INSENSITIVE),
            // onload(...)=...
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // alert
            Pattern.compile("alert(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("<", Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(">", Pattern.MULTILINE | Pattern.DOTALL),
            //Checks any html tags i.e. <script, <embed, <object etc.
            Pattern.compile("(<(script|iframe|embed|frame|frameset|object|img|applet|body|html|style|layer|link|ilayer|meta|bgsound))")
    };

    public static String stripXss(String value) {
        value = value.replaceAll("\0", "");
        for (Pattern scriptPattern : XSS_PATTERNS) {
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }

    public static boolean checkIsXss(String value) {
        boolean isXss = false;
        if (value == null) {
            return isXss;
        }
        for (Pattern scriptPattern : XSS_PATTERNS) {
            Matcher matcher = scriptPattern.matcher(value);
            if (matcher.find()) {
                isXss = true;
                break;
            }
        }
        return isXss;
    }

    public static void main(String[] args) {
        String str = "这是正常字符，正常得再十分不过了";
        boolean result = XssUtil.checkIsXss(str);
        System.out.println(str + " 是否包含字符: " + result);

        String str2 = "这是一段包含XSS的字符\'\"<script>alert(11111)</script>";
        boolean result2 = XssUtil.checkIsXss(str2);
        System.out.println(str2 + " 是否包含字符: " + result2);

        String str3 = "<img/src=1 onerror=alert(1)>";
        boolean result3 = XssUtil.checkIsXss(str3);
        System.out.println(str3 + " 是否包含字符: " + result3);
    }
}
