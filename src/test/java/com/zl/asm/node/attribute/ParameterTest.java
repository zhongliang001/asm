package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterTest {

    byte[] bytes = new byte[]{0, 29, 0, 16};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        Parameter parameter = new Parameter(byteContainer);
        int nameIndex = parameter.getNameIndex();
        assertEquals(29, nameIndex);
        AccessFlag accessFlag = parameter.getAccessFlag();
        List<String> accessFlags = accessFlag.getAccessFlags();
        assertArrayEquals(new String[]{"ACC_FINAL"}, accessFlags.toArray());
    }

}