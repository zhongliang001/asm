package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ExceptionNode {

    private final Logger logger = LoggerFactory.getLogger(ExceptionNode.class);

    private int startPc;

    private int endPc;

    private int handlerPc;

    private int catchType;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public ExceptionNode(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        startPc = ByteUtils.bytesToInt(bc.next(2));
        endPc = ByteUtils.bytesToInt(bc.next(2));
        handlerPc = ByteUtils.bytesToInt(bc.next(2));
        catchType = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ExceptionNode code:{}", bc.copy(startIndex, endIndex));
        }
    }

    // @Override
    public void accept() {
        logger.info("startPc:{} endPc:{}，handlerPc：{}，catchType：{}", startPc, endPc, handlerPc, catchType);
    }

    public void log(Logger log, boolean isParent) {
        logger.info("startPc:{} endPc:{}，handlerPc：{}，catchType：{}", startPc, endPc, handlerPc, catchType);
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        formatter.format("\t\tstartPc\t%d\t endPc\t%d\thandlerPc\t%d\t", startPc, endPc, handlerPc);
        if (catchType > 0) {
            ConstantNode catchNode = constantNodes[catchType - 1];
            formatter.format("catchType\t%s", catchNode.getValue());
        }
        formatter.format("\n");

        stringBuilder.append(formatter);
    }
}