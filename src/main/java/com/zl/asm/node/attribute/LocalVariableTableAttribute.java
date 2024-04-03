package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalVariableTableAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(LocalVariableTableAttribute.class);
    private int localVariableTableLength;

    private LocalVariableTable[] localVariableTables;
    private int endIndex;

    public LocalVariableTableAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        localVariableTableLength = ByteUtils.bytesToInt(bc.next(2));
        localVariableTables = new LocalVariableTable[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTables[i] = new LocalVariableTable(bc);
        }
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVariableTableAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getLocalVariableTableLength() {
        return localVariableTableLength;
    }

    public LocalVariableTable[] getLocalVariableTables() {
        return localVariableTables;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("localVariableTableLength:{}", localVariableTableLength);
        for (LocalVariableTable localVariableTable : localVariableTables) {
            localVariableTable.log(log, isParent);
        }
    }
}
