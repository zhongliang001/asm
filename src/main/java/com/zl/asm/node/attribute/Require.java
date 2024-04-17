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

public class Require implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Require.class);

    private int requiresIndex;

    private AccessFlag requiresFlags;

    private int requiresVersionIndex;

    private int startIndex;

    private int endIndex;

    public Require(ByteContainer byteContainer) {
        startIndex = byteContainer.getIndex();
        requiresIndex = ByteUtils.bytesToInt(byteContainer.next(2));
        requiresFlags = new AccessFlag(byteContainer, AccessFlagType.MODULE_ACCESS_FLAG_1);
        requiresVersionIndex = ByteUtils.bytesToInt(byteContainer.next(2));
        ;
        endIndex = byteContainer.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Require code:{}", byteContainer.copy(startIndex, endIndex));
        }
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("requiresIndex:|%03d|,requiresFlags:%s,requiresVersionIndex:|%03d|", requiresIndex, requiresFlags.getAccessFlags(), requiresVersionIndex);
        log.info("{}", formatter);
    }

    @Override
    public void accept(Reader reader) {

    }
}
