package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassInfoTest {
    byte[] bytes = new byte[]{4, 33, 0, 3, 0, 4, 0, 2, 0, 5, 0, 6};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        ClassInfo classInfo = new ClassInfo(byteContainer);
        int thisClass = classInfo.getThisClass();
        assertEquals(3, thisClass);
        int superClass = classInfo.getSuperClass();
        assertEquals(4, superClass);
        int interfaceCount = classInfo.getInterfaceCount();
        assertEquals(2, interfaceCount);
        int[] interFaces = classInfo.getInterFaces();
        assertArrayEquals(new int[]{5, 6}, interFaces);
        AccessFlag accessFlag = classInfo.getAccessFlag();
        List<String> accessFlags = accessFlag.getAccessFlags();
        assertArrayEquals(new String[]{"ACC_PUBLIC", "ACC_SUPER", "ACC_ABSTRACT"}, accessFlags.toArray());

    }
}