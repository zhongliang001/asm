package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

public class LineNumberTableAttribute extends Attribute {

    private int lineNumberTableLength;

    private LineNumbeTable [] lineNumbeTables;
    public LineNumberTableAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        lineNumberTableLength = ByteUtils.bytesToInt(bc.next(2));
        lineNumbeTables = new LineNumbeTable[lineNumberTableLength];
        for (int i = 0; i < lineNumbeTables.length; i++) {
            lineNumbeTables[i] = new LineNumbeTable(bc);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("lineNumberTableLength:{}",lineNumberTableLength);
        for (LineNumbeTable lineNumbeTable : lineNumbeTables) {
            lineNumbeTable.log(log, isParent);
        }
    }
}
