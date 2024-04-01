package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodConstantTest {

    byte[] bytes = new byte[]{0, 4, 0, 25};

    @Test
    public void Test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        MethodConstant methodConstant = new MethodConstant(byteContainer, ConstantKind.CONSTANT_Methodref, 1);
        int classIndex = methodConstant.getClassIndex();
        assertEquals(4, classIndex);
        int nameAndTypeIndex = methodConstant.getNameAndTypeIndex();
        assertEquals(25, nameAndTypeIndex);
    }

}