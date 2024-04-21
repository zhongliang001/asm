package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccessFlag implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(AccessFlag.class);

    private byte[] accessBytes;

    private String accessString;
    private List<String> accessFlags;

    private int startIndex;

    private int endIndex;

    public AccessFlag(ByteContainer bc, int type) {
        startIndex = bc.getIndex();
        accessBytes = bc.next(2);
        accessString = "Ox" + ByteUtils.toHexString(accessBytes);
        accessFlags = AccessFlagsFormatter.getAccessFlag(ByteUtils.bytesToInt(accessBytes), type);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("AccessFlag code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public void log(Logger log, boolean isParent) {
        log.info("accessFlags:{}", accessFlags);
        if (!isParent) {
            log.info("accessString:{}", accessString);
        }

    }

    public String getAccessString() {
        return accessString;
    }

    public List<String> getAccessFlags() {
        return accessFlags;
    }

    public String getAccessStr() {
        return String.join(",", accessFlags);
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}
