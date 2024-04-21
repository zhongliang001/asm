package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LocalVariableTypeTableAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(LocalVariableTypeTableAttribute.class);

    private int localVariableTypeTableLength;

    private LocalVariableTypeTable[] localVariableTypeTables;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public LocalVariableTypeTableAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        localVariableTypeTableLength = ByteUtils.bytesToInt(bc.next(2));
        localVariableTypeTables = new LocalVariableTypeTable[localVariableTypeTableLength];
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            localVariableTypeTables[i] = new LocalVariableTypeTable(bc, constantPoolNode);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVariableTypeTableAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\tlocalVariableTypeTableLength\t%s\n", localVariableTypeTableLength);
        stringBuilder.append(formatter);
        for (LocalVariableTypeTable localVariableTypeTable : localVariableTypeTables) {
            localVariableTypeTable.getLog(stringBuilder);
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
