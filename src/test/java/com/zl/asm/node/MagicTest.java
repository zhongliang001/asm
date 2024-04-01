package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagicTest {
    byte[] bytes = new byte[]{-54, -2, -70, -66};

    @Test
    public void magic() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        Magic magic = new Magic(byteContainer);
        String value = magic.getValue();
        assertEquals("cafebabe", value);
    }

}