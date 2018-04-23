package com.utils;

public class StringUtil {
	 /**
     * 棣栧瓧姣嶈浆灏忓啓
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder())
                    .append(Character.toLowerCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
        }
    }

    /**
     * 棣栧瓧姣嶈浆澶у啓
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder())
                    .append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
        }
    }

    /**
     * replace:(鏇挎崲瀛楃涓插嚱鏁�)
     * 
     * @param strSource
     *            婧愬瓧绗︿覆
     * @param strFrom
     *            瑕佹浛鎹㈢殑瀛愪覆
     * @param strTo
     *            鏇挎崲涓虹殑瀛楃涓�
     * @return
     * @since JDK 1.7
     */
    public static String replace(String strSource, String strFrom,
            String strTo) {
        // 濡傛灉瑕佹浛鎹㈢殑瀛愪覆涓虹┖锛屽垯鐩存帴杩斿洖婧愪覆
        if (strFrom == null || strFrom.equals(""))
            return strSource;
        String strDest = "";
        // 瑕佹浛鎹㈢殑瀛愪覆闀垮害
        int intFromLen = strFrom.length();
        int intPos;
        // 寰幆鏇挎崲瀛楃涓�
        while ((intPos = strSource.indexOf(strFrom)) != -1) {
            // 鑾峰彇鍖归厤瀛楃涓茬殑宸﹁竟瀛愪覆
            strDest = strDest + strSource.substring(0, intPos);
            // 鍔犱笂鏇挎崲鍚庣殑瀛愪覆
            strDest = strDest + strTo;
            // 淇敼婧愪覆涓哄尮閰嶅瓙涓插悗鐨勫瓙涓�
            strSource = strSource.substring(intPos + intFromLen);
        }
        // 鍔犱笂娌℃湁鍖归厤鐨勫瓙涓�
        strDest = strDest + strSource;
        // 杩斿洖
        return strDest;
    }
}
