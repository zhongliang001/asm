package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicConstantTest {

    byte[] bytes = new byte[]{0, 0, 0, 93};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        DynamicConstant dynamicConstant = new DynamicConstant(byteContainer, ConstantKind.CONSTANT_InvokeDynamic_info, 1);
        int nameAndTypeIndex = dynamicConstant.getNameAndTypeIndex();
        assertEquals(93, nameAndTypeIndex);
        int bootstrapMethodAttrIndex = dynamicConstant.getBootstrapMethodAttrIndex();
        assertEquals(0, bootstrapMethodAttrIndex);
    }

}