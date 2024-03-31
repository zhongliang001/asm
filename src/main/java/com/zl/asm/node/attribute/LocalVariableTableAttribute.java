package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

public class LocalVariableTableAttribute extends Attribute {

    private int localVariableTableLength;

    private LocalVariableTable[] localVariableTables;

    public LocalVariableTableAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        localVariableTableLength = ByteUtils.bytesToInt(bc.next(2));
        localVariableTables = new LocalVariableTable[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTables[i] = new LocalVariableTable(bc);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("localVariableTableLength:{}", localVariableTableLength);
        for (LocalVariableTable localVariableTable : localVariableTables) {
            localVariableTable.log(log, isParent);
        }
    }
}
