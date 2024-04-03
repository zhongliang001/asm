package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AccessFlagTest {

    byte[] bytes = new byte[]{4, 33};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);
        AccessFlag accessFlag = new AccessFlag(byteContainer, AccessFlagType.CLASS_ACCESS_FLAG);
        List<String> accessFlags = accessFlag.getAccessFlags();
        assertArrayEquals(new String[]{"ACC_PUBLIC", "ACC_SUPER", "ACC_ABSTRACT"}, accessFlags.toArray());
    }

}