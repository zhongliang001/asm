package com.zl.asm.util;

public class BitUtils {

    public static boolean checkBit(int num, int index) {
        if (index > Integer.SIZE) {
            return false;
        }
        int shift = index - 1;
        int shiftVale = num >> shift;
        return (shiftVale & 0x01) == 1;
    }
}
