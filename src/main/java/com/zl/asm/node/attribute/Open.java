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

public class Open implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Open.class);
    private int opensIndex;

    private AccessFlag opensFlags;

    private int opensToCount;

    private int[] opensToIndex;

    private int startIndex;

    private int endIndex;

    public Open(ByteContainer byteContainer) {
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

    @Override
    public void accept(Reader reader) {

    }
}
