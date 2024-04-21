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

public class Export {

    private final Logger logger = LoggerFactory.getLogger(Export.class);
    private int exportsIndex;

    private AccessFlag exportsFlags;

    private int exportsToCount;

    private int[] exportsToIndex;
    private int startIndex;

    private int endIndex;
    private ConstantPoolNode constantPoolNode;

    public Export(ByteContainer byteContainer, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = byteContainer.getIndex();
        exportsIndex = ByteUtils.bytesToInt(byteContainer.next(2));
        exportsFlags = new AccessFlag(byteContainer, AccessFlagType.MODULE_ACCESS_FLAG_1);
        exportsToCount = ByteUtils.bytesToInt(byteContainer.next(2));
        exportsToIndex = new int[exportsToCount];
        for (int i = 0; i < exportsToIndex.length; i++) {
            exportsToIndex[i] = ByteUtils.bytesToInt(byteContainer.next(2));
        }
        endIndex = byteContainer.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Export code:{}", byteContainer.copy(startIndex, endIndex));
        }
    }


    public int getExportsIndex() {
        return exportsIndex;
    }

    public AccessFlag getExportsFlags() {
        return exportsFlags;
    }

    public int getExportsToCount() {
        return exportsToCount;
    }

    public int[] getExportsToIndex() {
        return exportsToIndex;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("exportsIndex:|%03d|,exportsFlags:%s,exportsToCount:%d,exportsToIndex:", exportsIndex, exportsFlags.getAccessString(), exportsToCount);
        for (int toIndex : exportsToIndex) {
            formatter.format("|%03d|\t", toIndex);
        }
        log.info("{}", formatter);
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[exportsIndex - 1];
        formatter.format("\t\t\texports\t%s\texportsFlags\t%s\texportsToCount\t%d\n\t\t\t\t", constantNode.getValue(), exportsFlags.getAccessStr(), exportsToCount);
        for (int toIndex : exportsToIndex) {
            ConstantNode indexConstantNode = constantNodes[toIndex - 1];
            formatter.format("%s\t", indexConstantNode.getValue());
        }
        formatter.format("\n");
        stringBuilder.append(formatter);
    }
}
