package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ConstantKind;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongConstantTest {

    static byte[] bytes = new byte[]{0, 0, 0, 0, -1, -1, -1, -1};
    static ByteContainer bc = new ByteContainer(bytes);
    static LongConstant longConstant = new LongConstant(bc, ConstantKind.CONSTANT_Long, 1);

    @BeforeAll
    public static void init() {
        bc = new ByteContainer(bytes);
        longConstant = new LongConstant(bc, ConstantKind.CONSTANT_Long, 1);
    }

    @Test
    public void test() {

        long highBytes = longConstant.getHighBytes();
        long lowBytes = longConstant.getLowBytes();
        assertEquals(0L, highBytes);
        assertEquals(4294967295L, lowBytes);
    }

    @Test
    void getLog() {
        StringBuilder builder = new StringBuilder();
        longConstant.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }

}