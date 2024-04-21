package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import com.zl.asm.node.AccessFlagType;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Open {

    private final Logger logger = LoggerFactory.getLogger(Open.class);
    private int opensIndex;

    private AccessFlag opensFlags;

    private int opensToCount;

    private int[] opensToIndex;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public Open(ByteContainer byteContainer, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = byteContainer.getIndex();
        opensIndex = ByteUtils.bytesToInt(byteContainer.next(2));
        opensFlags = new AccessFlag(byteContainer, AccessFlagType.MODULE_ACCESS_FLAG_1);
        opensToCount = ByteUtils.bytesToInt(byteContainer.next(2));
        opensToIndex = new int[opensToCount];
        for (int i = 0; i < opensToIndex.length; i++) {
            opensToIndex[i] = ByteUtils.bytesToInt(byteContainer.next(2));
        }
        endIndex = byteContainer.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Open code:{}", byteContainer.copy(startIndex, endIndex));
        }
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("opensIndex:|%03d|,opensFlags:%s,opensToCount:%d,opensToIndex:", opensIndex, opensFlags.getAccessFlags(), opensToCount);
        for (int toIndex : opensToIndex) {
            formatter.format("|%03d|\t", toIndex);
        }
        log.info("{}", formatter);
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[opensIndex - 1];
        formatter.format("\t\t\topens\t%s\topensFlag\t%s\topensToCount\t%d\n\t\t\t\t", constantNode.getValue(), opensFlags.getAccessStr(), opensToCount);
        for (int toIndex : opensToIndex) {
            ConstantNode indexConstantNode = constantNodes[toIndex - 1];
            formatter.format("%s\t", indexConstantNode.getValue());
        }
        formatter.format("\n");
        stringBuilder.append(formatter);
    }
}
