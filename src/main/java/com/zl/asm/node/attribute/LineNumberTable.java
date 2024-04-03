package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LineNumberTable implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(LineNumberTable.class);
    private int startPc;

    private int lineNumber;

    private int startIndex;

    private int endIndex;

    public LineNumberTable(ByteContainer bc) {
        startIndex = bc.getIndex();
        startPc = ByteUtils.bytesToInt(bc.next(2));
        lineNumber = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LineNumberTable code:{}", bc.copy(startIndex, endIndex));
        }

    }

    public int getStartPc() {
        return startPc;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("startPc:|%03d|lineNumber:%d", startPc, lineNumber);
        logger.info("{}", formatter);
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}
