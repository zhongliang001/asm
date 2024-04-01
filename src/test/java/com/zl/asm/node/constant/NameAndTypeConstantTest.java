package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameAndTypeConstantTest {
    byte[] bytes = new byte[]{0, 12, 0, 13};

    @Test
    public void Test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        NameAndTypeConstant methodConstant = new NameAndTypeConstant(byteContainer, ConstantKind.CONSTANT_NameAndType, 1);
        int nameIndex = methodConstant.getNameIndex();
        assertEquals(12, nameIndex);
        int descriptorIndex = methodConstant.getDescriptorIndex();
        assertEquals(13, descriptorIndex);
    }
}