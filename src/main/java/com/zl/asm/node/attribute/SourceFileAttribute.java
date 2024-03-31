package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

import java.util.Formatter;

public class SourceFileAttribute extends Attribute {

    private int sourcefileIndex;

    public SourceFileAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        sourcefileIndex = ByteUtils.bytesToInt(bc.next(2));
    }

    @Override
    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("sourcefileIndex:|%03d|", sourcefileIndex);
        log.info("{}", formatter);
    }
}
