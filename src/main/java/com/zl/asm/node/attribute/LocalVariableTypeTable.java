package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LocalVariableTypeTable {

    private final Logger logger = LoggerFactory.getLogger(LocalVariableTypeTable.class);

    private int startPc;

    private int length;

    private int nameIndex;

    private int signatureIndex;

    private int index;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public LocalVariableTypeTable(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        this.startIndex = bc.getIndex();
        this.startPc = ByteUtils.bytesToInt(bc.next(2));
        this.length = ByteUtils.bytesToInt(bc.next(2));
        this.nameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.signatureIndex = ByteUtils.bytesToInt(bc.next(2));
        this.index = ByteUtils.bytesToInt(bc.next(2));
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVariableTypeTable code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public void log(Logger log, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("startPc:|%d|length:%d,nameIndex:|%03d|,signatureIndex:|%03d|,index:%d", startPc, length, nameIndex, signatureIndex, index);
            logger.info("{}", formatter);
        }
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[nameIndex - 1];
        ConstantNode signConstantNode = constantNodes[signatureIndex - 1];
        formatter.format("\t\tstartPc\t%s\tname\t%s\tsingatura\t%s\n", startPc, constantNode.getValue(), signConstantNode.getValue());
        stringBuilder.append(formatter);
    }
}
