package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldConstantTest {
    byte[] bytes = new byte[]{0, 3, 0, 26};

    @Test
    public void FieldTest() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        FieldConstant fieldConstant = new FieldConstant(byteContainer, ConstantKind.CONSTANT_Fieldref, 1);
        int classIndex = fieldConstant.getClassIndex();
        assertEquals(3, classIndex);
        int nameAndTypeIndex = fieldConstant.getNameAndTypeIndex();
        assertEquals(26, nameAndTypeIndex);
    }

}