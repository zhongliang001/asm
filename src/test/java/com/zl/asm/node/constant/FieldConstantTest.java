package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldConstantTest {
    static byte[] bytes = new byte[]{0, 3, 0, 26};

    static ByteContainer byteContainer;

    static FieldConstant fieldConstant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        fieldConstant = new FieldConstant(byteContainer, ConstantKind.CONSTANT_Fieldref, 1);
    }

    @Test
    public void FieldTest() {
        int classIndex = fieldConstant.getClassIndex();
        assertEquals(3, classIndex);
        int nameAndTypeIndex = fieldConstant.getNameAndTypeIndex();
        assertEquals(26, nameAndTypeIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        fieldConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }

}