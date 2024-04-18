package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicConstantTest {

    static byte[] bytes = new byte[]{0, 0, 0, 93};
    static ByteContainer byteContainer;
    static DynamicConstant dynamicConstant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        dynamicConstant = new DynamicConstant(byteContainer, ConstantKind.CONSTANT_InvokeDynamic_info, 1);
    }

    @Test
    public void test() {
        int nameAndTypeIndex = dynamicConstant.getNameAndTypeIndex();
        assertEquals(93, nameAndTypeIndex);
        int bootstrapMethodAttrIndex = dynamicConstant.getBootstrapMethodAttrIndex();
        assertEquals(0, bootstrapMethodAttrIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        dynamicConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }


}