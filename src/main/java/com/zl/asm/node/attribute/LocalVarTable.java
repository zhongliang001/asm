package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LocalVarTable {

    private final Logger logger = LoggerFactory.getLogger(LocalVarTable.class);

    private int startPc;

    private int length;

    private int index;

    private int startIndex;

    private int endIndex;

    public LocalVarTable(ByteContainer bc) {
        startIndex = bc.getIndex();
        startPc = ByteUtils.bytesToInt(bc.next(2));
        length = ByteUtils.bytesToInt(bc.next(2));
        index = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVarTable code:{}", bc.copy(startIndex, endIndex));
        }

    }

    public int getStartPc() {
        return startPc;
    }

    public int getLength() {
        return length;
    }

    public int getIndex() {
        return index;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("startPc: |%03d|, length: %d, index: |%03d|", startPc, length, index);
        log.info("{}", formatter);

    }
}
