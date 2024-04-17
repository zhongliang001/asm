package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import com.zl.asm.node.AccessFlagType;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Parameter {

    private final Logger logger = LoggerFactory.getLogger(Parameter.class);
    private int nameIndex;

    private AccessFlag accessFlag;

    private int startIndex;

    private int endIndex;

    public Parameter(ByteContainer bc) {
        startIndex = bc.getIndex();
        nameIndex = ByteUtils.bytesToInt(bc.next(2));
        accessFlag = new AccessFlag(bc, AccessFlagType.METHOD_ACCESS_FLAG);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Parameter code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("nameIndex: |%03d|", nameIndex);
        log.info("{}", formatter);
        accessFlag.log(log, isParent);
    }
}
