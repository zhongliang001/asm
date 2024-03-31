package com.zl.asm.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BitUtilsTest {

    @Test
    void checkBit() {
        boolean b = BitUtils.checkBit(1, 1);
        assertTrue(b);
        boolean b1 = BitUtils.checkBit(4, 1);
        assertFalse(b1);
    }
}