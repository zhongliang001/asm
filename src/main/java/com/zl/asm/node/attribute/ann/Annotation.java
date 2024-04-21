package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Annotation {

    private final Logger logger = LoggerFactory.getLogger(Annotation.class);
    private int typeIndex;

    private int numElementValuePairs;

    private ElementValuePair[] elementValuePairs;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public Annotation(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        typeIndex = ByteUtils.bytesToInt(bc.next(2));
        numElementValuePairs = ByteUtils.bytesToInt(bc.next(2));
        elementValuePairs = new ElementValuePair[numElementValuePairs];
        for (int i = 0; i < elementValuePairs.length; i++) {
            elementValuePairs[i] = new ElementValuePair(bc, constantPoolNode);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Annotation code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public int getNumElementValuePairs() {
        return numElementValuePairs;
    }

    public ElementValuePair[] getElementValuePairs() {
        return elementValuePairs;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("typeIndex:|%03d|,numElementValuePairs: %d", typeIndex, numElementValuePairs);
        log.info("{}", log);
        for (ElementValuePair elementValuePair : elementValuePairs) {
            elementValuePair.log(log, true);
        }
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[typeIndex - 1];
        formatter.format("\t\ttypeIndex\t%s\tnumElementValuePairs\t%s\n", constantNode.getValue(), numElementValuePairs);
        stringBuilder.append(formatter);
        for (ElementValuePair elementValuePair : elementValuePairs) {
            elementValuePair.getLog(stringBuilder);
        }

    }
}
