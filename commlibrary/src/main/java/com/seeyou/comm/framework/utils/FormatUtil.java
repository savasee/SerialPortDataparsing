package com.seeyou.comm.framework.utils;

/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class FormatUtil {
    /**
     * 16进制字符串转换为byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase().replace(" ", "");
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /*
     * 十六进制转字符
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder StringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }

        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                StringBuilder.append(0);
            }

            StringBuilder.append(hv);
        }

        return StringBuilder.toString();
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
