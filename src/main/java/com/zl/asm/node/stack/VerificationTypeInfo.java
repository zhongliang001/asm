package com.zl.asm.node.stack;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;

public class VerificationTypeInfo {
    private int tag;

    private int cpoolIndex;

    private int offset;

    public VerificationTypeInfo(ByteContainer bc) {
        tag = ByteUtils.byteToUnsignedInt(bc.next());
        if (tag == 7) {
            cpoolIndex = ByteUtils.bytesToInt(bc.next(2));
        }
        if (tag == 8) {
            offset = ByteUtils.bytesToInt(bc.next(2));
        }
    }
}
