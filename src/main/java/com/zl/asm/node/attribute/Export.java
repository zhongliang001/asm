package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import com.zl.asm.node.AccessFlagType;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Export implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Export.class);
    private int exportsIndex;

    private AccessFlag exportsFlags;

    private int exportsToCount;

    private int[] exportsToIndex;
    private int startIndex;

    private int endIndex;

    public Export(ByteContainer byteContainer) {
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

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("exportsIndex:|%03d|,exportsFlags:%s,exportsToCount:%d,exportsToIndex:", exportsIndex, exportsFlags.getAccessString(), exportsToCount);
        for (int toIndex : exportsToIndex) {
            formatter.format("|%03d|\t", toIndex);
        }
        log.info("{}", formatter);
    }

    @Override
    public void accept(Reader reader) {

    }
}
