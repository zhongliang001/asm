package com.zl.asm.util;

public class ByteUtils {

    public static String toHexString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            // bytes[i] & 0xFF 将byte前面8补位
            String hexString = Integer.toHexString(bytes[i] & 0xFF);
            if (hexString.length() < 2) {
                s.append("0");
            }
            s.append(hexString);
        }
        return s.toString();
    }

    public static int bytesToInt(byte[] bytes) {
        int result = 0;
        int len = bytes.length;
        for (int i = 0; i < bytes.length; i++, len--) {
            result += (bytes[i] & 0xFF) << ((len - 1) * 8);
        }
        return result;
    }

    public static int byteToUnsignedInt(byte bt) {

        return bt & 0xFF;
    }

    public static String toOctString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            // bytes[i] & 0xFF 将byte前面8补位
            String octString = Integer.toOctalString(bytes[i] & 0xFF);
            int length = octString.length();
            while (length < 2) {
                s.append("0");
                length++;
            }
            s.append(octString);
        }
        return "0x" + s.toString();
    }
}
