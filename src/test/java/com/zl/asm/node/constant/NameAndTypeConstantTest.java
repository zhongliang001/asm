package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameAndTypeConstantTest {
    static byte[] bytes = new byte[]{0, 12, 0, 13};

    static ByteContainer byteContainer;
    static NameAndTypeConstant constant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        //TODO constantPoolNode
        constant = new NameAndTypeConstant(byteContainer, null, ConstantKind.CONSTANT_NameAndType, 1);
    }

    @Test
    public void Test() {
        int nameIndex = constant.getNameIndex();
        assertEquals(12, nameIndex);
        int descriptorIndex = constant.getDescriptorIndex();
        assertEquals(13, descriptorIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        constant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }
}