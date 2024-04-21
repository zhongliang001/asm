package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ExceptionAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(ExceptionAttribute.class);
    private int numberOfExceptions;

    private int[] exceptionIndexTable;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public ExceptionAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        numberOfExceptions = ByteUtils.bytesToInt(bc.next(2));
        exceptionIndexTable = new int[numberOfExceptions];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            exceptionIndexTable[i] = ByteUtils.bytesToInt(bc.next(2));
        }

        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ExceptionAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tnumberOfExceptions\t%s\n", numberOfExceptions);
        for (int i : exceptionIndexTable) {
            ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
            ConstantNode exceptionIndexTable = constantNodes[i - 1];
            formatter.format("\texceptionIndexTable:%s\n", exceptionIndexTable.getValue());

        }
        stringBuilder.append(formatter);
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
