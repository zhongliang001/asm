package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LocalVariableTable {

    private final Logger logger = LoggerFactory.getLogger(LocalVariableTable.class);

    private int startPc;
    private int length;
    private int nameIndex;
    private int descriptorIndex;
    private int index;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public LocalVariableTable(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        this.startIndex = bc.getIndex();
        this.startPc = ByteUtils.bytesToInt(bc.next(2));
        this.length = ByteUtils.bytesToInt(bc.next(2));
        this.nameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        this.index = ByteUtils.bytesToInt(bc.next(2));
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVariableTable code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("startPc:|%03d|lineNumber:%d,nameIndex:%d,descriptorIndex:|%03d|, index:%d", startPc, length, nameIndex, descriptorIndex, index);
        logger.info("{}", formatter);
    }


    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode nameConstantNode = constantNodes[nameIndex - 1];
        ConstantNode descripConstantNode = constantNodes[descriptorIndex - 1];
        formatter.format("\t\tstartPc\t%d\tlineNumber\t%d\tname\t%s\t\ttype\t%s\tindex\t%d\n", startPc, length, nameConstantNode.getValue(), descripConstantNode.getValue(), index);
        stringBuilder.append(formatter);
    }
}
