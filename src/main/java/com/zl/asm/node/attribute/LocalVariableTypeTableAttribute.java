package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalVariableTypeTableAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(LocalVariableTypeTableAttribute.class);

    private int localVariableTypeTableLength;

    private LocalVariableTypeTable[] localVariableTypeTables;

    private int endIndex;

    public LocalVariableTypeTableAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        localVariableTypeTableLength = ByteUtils.bytesToInt(bc.next(2));
        localVariableTypeTables = new LocalVariableTypeTable[localVariableTypeTableLength];
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            localVariableTypeTables[i] = new LocalVariableTypeTable(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVariableTypeTableAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("localVariableTypeTableLength:{}", localVariableTypeTableLength);
        for (LocalVariableTypeTable localVariableTypeTable : localVariableTypeTables) {
            localVariableTypeTable.log(log, isParent);
        }
    }
}
