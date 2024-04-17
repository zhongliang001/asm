package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnclosingMethodAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 4, 0, 28, 0, 29};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(byteContainer, 1);
        int methodIndex = enclosingMethodAttribute.getMethodIndex();
        int classIndex = enclosingMethodAttribute.getClassIndex();
        assertEquals(29, methodIndex);
        assertEquals(28, classIndex);
    }

}