package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public LineNumberTable[] getLineNumbeTables() {
        return lineNumberTables;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("lineNumberTableLength:{}", lineNumberTableLength);
        for (LineNumberTable lineNumberTable : lineNumberTables) {
            lineNumberTable.log(log, isParent);
        }
    }

    public void accept(Reader reader) {
        reader.read(this);
        for (LineNumberTable lineNumberTable : lineNumberTables) {
            lineNumberTable.accept(reader);
        }
    }

}
