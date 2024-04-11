package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 70, 0, 5, 0, 0, 0, 0, 0, 2, 0, 6, -128, 0, 0, 7, 0, 8, 0, 0, 0, 9, 0, 7, 0, 10, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        ModuleAttribute attribute = new ModuleAttribute(byteContainer, 1);
        int exportsCount = attribute.getExportsCount();
        assertEquals(7, exportsCount);
    }

}