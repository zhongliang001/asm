package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Provide {

    private final Logger logger = LoggerFactory.getLogger(Provide.class);
    private int providesIndex;
    private int providesWithCount;

    private int[] providesWithIndex;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public Provide(ByteContainer byteContainer, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = byteContainer.getIndex();
        providesIndex = ByteUtils.bytesToInt(byteContainer.next(2));
        providesWithCount = ByteUtils.bytesToInt(byteContainer.next(2));
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithIndex.length; i++) {
            providesWithIndex[i] = ByteUtils.bytesToInt(byteContainer.next(2));
        }
        endIndex = byteContainer.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Open code:{}", byteContainer.copy(startIndex, endIndex));
        }
    }

    public int getProvidesIndex() {
        return providesIndex;
    }

    public int getProvidesWithCount() {
        return providesWithCount;
    }

    public int[] getProvidesWithIndex() {
        return providesWithIndex;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("providesIndex:|%03d|,providesWithCount:%d,providesWithIndex:", providesIndex, providesWithCount);
        for (int toIndex : providesWithIndex) {
            formatter.format("|%03d|\t", toIndex);
        }
        log.info("{}", formatter);
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[providesIndex - 1];
        formatter.format("\t\t\tprovides\t%s\tprovidesCount\t%d\n\t\t\t\t", constantNode.getValue(), providesWithCount);
        for (int toIndex : providesWithIndex) {
            ConstantNode indexConstantNode = constantNodes[toIndex - 1];
            formatter.format("%s\t", indexConstantNode.getValue());
        }
        formatter.format("\n");
        stringBuilder.append(formatter);
    }
}
