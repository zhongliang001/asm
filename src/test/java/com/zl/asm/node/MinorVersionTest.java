package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinorVersionTest {

    byte[] bytes = new byte[]{0, 0, 0, 55, 0};

    @Test
    public void minor() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        MinorVersion minorVersion = new MinorVersion(byteContainer);
        String value = minorVersion.getValue();
        assertEquals("55.0", value);
    }

}