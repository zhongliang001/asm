package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExportTest {

    byte[] bytes = new byte[]{0, 18, 0, 0, 0, 0};

    @Test
    public void test() {
        ByteContainer byteContainer = new ByteContainer(bytes);

        //TODO constantPoolNode
        Export export = new Export(byteContainer, null);
        AccessFlag exportsFlags = export.getExportsFlags();
        assertArrayEquals(new String[]{}, exportsFlags.getAccessFlags().toArray());
        int exportsIndex = export.getExportsIndex();
        assertEquals(18, exportsIndex);
    }

}