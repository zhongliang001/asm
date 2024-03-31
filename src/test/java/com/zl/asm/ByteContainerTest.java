package com.zl.asm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteContainerTest {

    byte[] bytes = new byte[]{1, 2, 3, 4, 5};

    @Test
    void next() {
        ByteContainer bc = new ByteContainer(bytes);
        byte next = bc.next();
        assertEquals(1, next);
        byte next1 = bc.next();
        assertEquals(2, next1);
    }

    @Test
    void testNext() {
        ByteContainer bc = new ByteContainer(bytes);
        byte[] next1 = bc.next(2);
        byte[] bytes1 = new byte[]{1, 2};
        assertArrayEquals(bytes1, next1);
        byte[] next2 = bc.next(2);
        byte[] bytes2 = new byte[]{3, 4};
        assertArrayEquals(bytes2, next2);

    }
}