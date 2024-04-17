package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class UnkownAttributeTest {

    byte[] bytes = new byte[]{0, 0, 0, 8, 0, 0, 0, 31, 0, 0, 4, 8};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        UnkownAttribute unkownAttribute = new UnkownAttribute(byteContainer, 1);
        byte[] bytes1 = unkownAttribute.getBytes();
        byte[] target = new byte[]{0, 0, 0, 31, 0, 0, 4, 8};
        assertArrayEquals(target, bytes1);

    }


}