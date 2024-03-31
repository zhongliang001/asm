package com.zl.asm.reader;

import com.zl.asm.node.AccessFlagType;
import com.zl.asm.node.AccessFlagsFormatter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AccessFlagsFormatterTest {

    @Test
    void getAccessFlag() {
        List<String> accessFlag = AccessFlagsFormatter.getAccessFlag(0x21, AccessFlagType.CLASS_ACCESS_FLAG);
        List<String> ex = new ArrayList<>();
        ex.add("ACC_PUBLIC");
        ex.add("ACC_SUPER");
        assertArrayEquals(ex.toArray(), accessFlag.toArray());
    }
}