package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleConstantTest {
    byte[] bytes = new byte[]{0, 20};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        ModuleConstant moduleConstant = new ModuleConstant(byteContainer, ConstantKind.CONSTANT_Module_info, 1);
        int nameIndex = moduleConstant.getNameIndex();
        assertEquals(20, nameIndex);
    }
}