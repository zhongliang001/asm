package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class EnumConstValue {

    private final Logger logger = LoggerFactory.getLogger(EnumConstValue.class);
    private int typeNameIndex;

    private int consNameIndex;

    private int startIndex;

    private int endIndex;

    public EnumConstValue(ByteContainer bc) {
        startIndex = bc.getIndex();
        typeNameIndex = ByteUtils.bytesToInt(bc.next(2));
        consNameIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("EnumConstValue code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getTypeNameIndex() {
        return typeNameIndex;
    }

    public int getConsNameIndex() {
        return consNameIndex;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("typeNameIndex:|%03d|, consNameIndex: |%03d|", typeNameIndex, consNameIndex);
        log.info("{}", formatter);
    }
}
