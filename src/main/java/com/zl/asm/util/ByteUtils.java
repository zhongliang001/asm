package com.zl.asm.util;

import java.nio.ByteBuffer;

public class ByteUtils {

    public static String toHexString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (byte aByte : bytes) {
            // bytes[i] & 0xFF 将byte前面8补位
            String hexString = Integer.toHexString(aByte & 0xFF);
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

    public static double bytesToDouble(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getDouble();
    }

    public static float bytesToFloat(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getFloat();
    }

    public static long bytesToLong(byte[] bytes) {
        long result = 0;
        int len = bytes.length;
        for (int i = 0; i < bytes.length; i++, len--) {
            result += (long) (bytes[i] & 0xFF) << ((len - 1) * 8);
        }
        return result;
    }

    public static int byteToUnsignedInt(byte bt) {

        return bt & 0xFF;
    }

    public static String toOctString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (byte aByte : bytes) {
            // bytes[i] & 0xFF 将byte前面8补位
            String octString = Integer.toOctalString(aByte & 0xFF);
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
