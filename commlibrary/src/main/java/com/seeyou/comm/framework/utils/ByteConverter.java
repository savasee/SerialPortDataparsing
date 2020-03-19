package com.seeyou.comm.framework.utils;

/**
 * Created by seeyou
 */

public class ByteConverter {
    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。
     *
     * @param value 要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytesLitter(int value) {
        byte[] byte_src = new byte[4];
        byte_src[3] = (byte) ((value & 0xFF000000) >> 24);
        byte_src[2] = (byte) ((value & 0x00FF0000) >> 16);
        byte_src[1] = (byte) ((value & 0x0000FF00) >> 8);
        byte_src[0] = (byte) ((value & 0x000000FF));
        return byte_src;
    }

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。
     *
     * @param value 要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytesLitters(int value, byte[] bytes, int offset) {
        bytes[3 + offset] = (byte) ((value & 0xFF000000) >> 24);
        bytes[2 + offset] = (byte) ((value & 0x00FF0000) >> 16);
        bytes[1 + offset] = (byte) ((value & 0x0000FF00) >> 8);
        bytes[0 + offset] = (byte) ((value & 0x000000FF));
        return bytes;
    }


    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。
     *
     * @param value 要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytesBigs(int value, byte[] bytes, int offset) {
        bytes[3 + offset] = (byte) ((value & 0xFF000000) >> 24);
        bytes[2 + offset] = (byte) ((value & 0x00FF0000) >> 16);
        bytes[1 + offset] = (byte) ((value & 0x0000FF00) >> 8);
        bytes[0 + offset] = (byte) ((value & 0x000000FF));
        return bytes;
    }

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。
     *
     * @param value 要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytesBig(int value) {
        byte[] byte_src = new byte[4];
        byte_src[0] = (byte) ((value & 0xFF000000) >> 24);
        byte_src[1] = (byte) ((value & 0x00FF0000) >> 16);
        byte_src[2] = (byte) ((value & 0x0000FF00) >> 8);
        byte_src[3] = (byte) ((value & 0x000000FF));
        return byte_src;
    }

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。
     *
     * @param value 要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytesBig(int value, byte[] bytes, int offset) {
        bytes[offset + 0] = (byte) ((value & 0xFF000000) >> 24);
        bytes[offset + 1] = (byte) ((value & 0x00FF0000) >> 16);
        bytes[offset + 2] = (byte) ((value & 0x0000FF00) >> 8);
        bytes[offset + 3] = (byte) ((value & 0x000000FF));
        return bytes;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序。
     *
     * @param ary    byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToIntLitter(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset] & 0xFF)
                | ((ary[offset + 1] << 8) & 0xFF00)
                | ((ary[offset + 2] << 16) & 0xFF0000)
                | ((ary[offset + 3] << 24) & 0xFF000000));
        return value;
    }

    /**
     * byte数组中取int数值，本方法适用于(高位在前，低位在后)的顺序。
     *
     * @param ary    byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToIntBig(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset + 3] & 0xFF)
                | ((ary[offset + 2] << 8) & 0xFF00)
                | ((ary[offset + 1] << 16) & 0xFF0000)
                | ((ary[offset + 0] << 24) & 0xFF000000));
        return value;
    }

    /**
     * @param ary
     * @param offset
     * @return
     */
    public static int bytesToIntBigPluse(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset + 2] & 0xFF)
                | ((ary[offset + 3] << 8) & 0xFF00)
                | ((ary[offset + 0] << 16) & 0xFF0000)
                | ((ary[offset + 1] << 24) & 0xFF000000));
        return value;
    }

    /**
     * @param ary
     * @param offset
     * @return 4个字节数组转long型
     */
    public static long bytesToLongLitter(byte[] ary, int offset) {
        long value;
        value = (long) ((ary[offset] & 0x0FF)
                | ((ary[offset + 1] << 8) & 0x0FF00)
                | ((ary[offset + 2] << 16) & 0x0FF0000)
                | ((ary[offset + 3] << 24) & 0x0FF000000));
        return value;
    }

    /**
     * @param ary
     * @param offset
     * @return 4个字节数组转long型
     */
    public static long bytesToLongBit(byte[] ary, int offset) {
        long value;
        value = (long) ((ary[offset + 3] & 0x0FF)
                | ((ary[offset + 2] << 8) & 0x0FF00)
                | ((ary[offset + 1] << 16) & 0x0FF0000)
                | ((ary[offset + 0] << 24) & 0x0FF000000));
        return value;
    }


    /**
     * @param b
     * @return int
     */
    public static int byteArrayToInt(byte[] b) {
        byte[] a = new byte[4];
        int i = a.length - 1, j = b.length - 1;
        for (; i >= 0; i--, j--) {//从b的尾部(即int值的低位)开始copy数据
            if (j >= 0)
                a[i] = b[j];
            else
                a[i] = 0;//如果b.length不足4,则将高位补0
        }
        int v0 = (a[0] & 0xff) << 24;//&0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        int v1 = (a[1] & 0xff) << 16;
        int v2 = (a[2] & 0xff) << 8;
        int v3 = (a[3] & 0xff);
        return v0 + v1 + v2 + v3;
    }


    /**
     * @param ary
     * @param offset
     * @return byte数组转long类型
     */
    public static int bytesToIntLowerbyte(byte[] ary, int offset) {
        int value = 0;
        int s1 = ary[1 + offset] & 0x0ff;// 最低位
        int s3 = ary[3 + offset] & 0x0ff;
        int s5 = ary[5 + offset] & 0x0ff;// 最低位
        int s7 = ary[7 + offset] & 0x0ff;

        // s7不变
        s5 <<= 8 * 1;
        s3 <<= 8 * 2;
        s1 <<= 8 * 3;
        value = (s1 | s3 | s5 | s7);
        return value;
    }

    /**
     * 将short数值转换为占2个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。
     *
     * @param value 要转换的short值
     * @return byte数组
     */
    public static byte[] shortToBytes(short value) {
        byte[] byte_src = new byte[2];
        byte_src[1] = (byte) ((value & 0xFF00) >> 8);
        byte_src[0] = (byte) ((value & 0x00FF));
        return byte_src;
    }

    /**
     * 将short数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。
     *
     * @param value 要转换的short值
     * @return byte数组
     */
    public static byte[] shortToBytesBig(short value) {
        byte[] byte_src = new byte[2];
        byte_src[0] = (byte) ((value & 0xFF00) >> 8);
        byte_src[1] = (byte) ((value & 0x00FF));
        return byte_src;
    }

    /**
     * 将short数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。
     *
     * @param value 要转换的short值
     * @return byte数组
     */
    public static byte[] shortToBytesBig(short value, byte[] bytes, int offset) {
        bytes[offset] = (byte) ((value & 0xFF00) >> 8);
        bytes[offset + 1] = (byte) ((value & 0x00FF));
        return bytes;
    }

    /**
     * 将short数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。
     *
     * @param value 要转换的short值
     * @return byte数组
     */
    public static byte[] shortToBytesLiter(short value, byte[] bytes, int offset) {
        bytes[offset] = (byte) ((value & 0x00FF));
        bytes[offset + 1] = (byte) ((value & 0xFF00) >> 8);
        return bytes;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序。
     * 小端
     *
     * @param ary    byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static short bytesToShortLiterEnd(byte[] ary, int offset) {
        short value;
        value = (short) ((ary[offset] & 0xFF)
                | ((ary[offset + 1] << 8) & 0xFF00));
        return value;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序。
     * 小端
     *
     * @param ary    byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int doubleBytesToIntLiterEnd(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset] & 0xFF)
                | ((ary[offset + 1] << 8) & 0xFF00)
                | 0x00000000);
        return value;
    }

    /**
     * byte字节型转换为int型
     * 小端
     *
     * @param ary byte
     * @return int数值
     */
    public static int byteToInt(byte ary) {
        int value;
        value = (int) ((ary & 0xFF)
                | 0x00000000);
        return value;
    }

    /**
     * byte数组中取int数值，本方法适用于(高位在前，低位在后)的顺序。
     * 大端
     *
     * @param ary    byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToShortBigEnd(byte[] ary, int offset) {
        int value;
        value = (short) ((ary[offset + 1] & 0x0FF)
                | ((ary[offset] << 8) & 0x0FF00));
        return value;
    }

    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i = begin; i < begin + count; i++) bs[i - begin] = src[i];
        return bs;
    }

    /**
     * @param b byte字节数
     * @explain 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    /**
     * @param data
     * @return 返回无符号byte型数据
     */
    public static byte getUnsignedByteFromInt(int data) {
        String strData = Integer.toHexString(data);
        byte bytedata = Integer.valueOf(strData, 16).byteValue();
        return bytedata;
    }

    /**
     * @param data
     * @return 返回无符号short型数据
     */
    public static short getUnsignedShortByteInt(int data) {
        String strData = Integer.toHexString(data);
        short shortData = Integer.valueOf(strData, 16).shortValue();
        return shortData;
    }

    /**
     * @param data
     * @return //将data字节型数据转换为0~255 (0xFF 即BYTE)。
     */
    public static int getUnsignedByteFromByte(byte data) {
        return data & 0x0FF;
    }

    /**
     * @param data
     * @return //将data字节型数据转换为0~65535 (0xFFFF 即 WORD)。
     */
    public static int getUnsignedShort(short data) {
        return (data & 0x0FFFF);
    }

    /**
     * @param data
     * @return 将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)。
     */
    public static long getUnsignedInteger(int data) {
        return (data & 0x0FFFFFFFFl);
    }

}
