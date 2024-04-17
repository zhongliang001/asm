package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageConstantTest {
    byte[] bytes = new byte[]{0, 21};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        PackageConstant packageConstant = new PackageConstant(byteContainer, ConstantKind.CONSTANT_Package_info, 1);
        int nameIndex = packageConstant.getNameIndex();
        assertEquals(21, nameIndex);
    }
}