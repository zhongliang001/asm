package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodConstantTest {

    static byte[] bytes = new byte[]{0, 4, 0, 25};

    static ByteContainer byteContainer;
    static MethodConstant methodConstant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        // todo constantPoolNode
        methodConstant = new MethodConstant(byteContainer, null, ConstantKind.CONSTANT_Methodref, 1);
    }

    @Test
    public void Test() {
        int classIndex = methodConstant.getClassIndex();
        assertEquals(4, classIndex);
        int nameAndTypeIndex = methodConstant.getNameAndTypeIndex();
        assertEquals(25, nameAndTypeIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        methodConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }

}