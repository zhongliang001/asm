package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LineNumberTableAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(LineNumberTableAttribute.class);
    private int lineNumberTableLength;

    private LineNumberTable[] lineNumberTables;
    private int endIndex;

    public LineNumberTableAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        lineNumberTableLength = ByteUtils.bytesToInt(bc.next(2));
        lineNumberTables = new LineNumberTable[lineNumberTableLength];
        for (int i = 0; i < lineNumberTables.length; i++) {
            lineNumberTables[i] = new LineNumberTable(bc);
        }
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LineNumberTableAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getLineNumberTableLength() {
        return lineNumberTableLength;
    }

    public LineNumberTable[] getLineNumberTables() {
        return lineNumberTables;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tlineNumberTableLength\t%d\n", lineNumberTableLength);
        stringBuilder.append(formatter);
        for (LineNumberTable lineNumberTable : lineNumberTables) {
            lineNumberTable.getLog(stringBuilder);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("lineNumberTableLength:{}", lineNumberTableLength);
        for (LineNumberTable lineNumberTable : lineNumberTables) {
            lineNumberTable.log(log, isParent);
        }
    }

}
