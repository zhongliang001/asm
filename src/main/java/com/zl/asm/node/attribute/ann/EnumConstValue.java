package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
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

    private ConstantPoolNode constantPoolNode;

    public EnumConstValue(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
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

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode typeConstantNode = constantNodes[typeNameIndex - 1];
        ConstantNode consConstantNode = constantNodes[consNameIndex - 1];
        formatter.format("\t\t\ttypeName\t%s\tconsName\t%s\n", typeConstantNode.getValue(), consConstantNode.getValue());
        stringBuilder.append(formatter);
    }
}
