package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ExceptionAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(ExceptionAttribute.class);
    private int numberOfExceptions;

    private int[] exceptionIndexTable;

    private int endIndex;

    public ExceptionAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numberOfExceptions = ByteUtils.bytesToInt(bc.next(2));
        exceptionIndexTable = new int[numberOfExceptions];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            exceptionIndexTable[i] = ByteUtils.bytesToInt(bc.next(2));
        }

        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ConstantAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numberOfExceptions:{}", numberOfExceptions);
        for (int i : exceptionIndexTable) {
            Formatter formatter = new Formatter();
            formatter.format("exceptionIndexTable:|%03d|", i);
            log.info("{}", formatter);
        }
    }
}
