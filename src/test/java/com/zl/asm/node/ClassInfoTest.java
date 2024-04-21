package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassInfoTest {
    static byte[] bytes = new byte[]{4, 33, 0, 3, 0, 4, 0, 2, 0, 5, 0, 6};
    static ByteContainer byteContainer = new ByteContainer(bytes);
    static ClassInfo classInfo = new ClassInfo(byteContainer);

    @BeforeAll
    public static void init() {
        byteContainer = new ByteContainer(bytes);
        classInfo = new ClassInfo(byteContainer);
    }

    @Test
    public void test() {
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

    @Test
    public void getLog() {
        StringBuilder builder = new StringBuilder();
        classInfo.getLog(builder);
        System.out.println(builder);
        System.out.println("end");
    }
}