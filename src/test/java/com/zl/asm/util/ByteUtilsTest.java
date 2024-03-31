package com.zl.asm.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteUtilsTest {

    @Test
    public void toHexString() {
        byte[] bytes = new byte[]{-54, -2, -70, -66};
        String hexString = ByteUtils.toHexString(bytes);
        assertEquals("cafebabe", hexString);
    }

    @Test
    void toOctString() {
        byte[] bytes = new byte[]{0, 33};
        String hexString = ByteUtils.toOctString(bytes);
        assertEquals("0x0041", hexString);
    }


    @Test
    void bytesToInt() {
        byte[] bytes = new byte[]{1, 1};
        int i = ByteUtils.bytesToInt(bytes);
        assertEquals(257, i);
    }


}