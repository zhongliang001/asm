package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageConstantTest {
    static byte[] bytes = new byte[]{0, 21};
    static ByteContainer byteContainer;
    static PackageConstant packageConstant;

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        packageConstant = new PackageConstant(byteContainer, ConstantKind.CONSTANT_Package_info, 1);
    }

    @Test
    public void test() {
        int nameIndex = packageConstant.getNameIndex();
        assertEquals(21, nameIndex);
    }

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        packageConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }
}