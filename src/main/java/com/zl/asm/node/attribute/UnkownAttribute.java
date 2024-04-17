package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnkownAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(UnkownAttribute.class);

    private byte[] bytes;

    private int endIndex;

    public UnkownAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        bytes = bc.next(this.attributeLength);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("UnkownAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("bytes: {}", bytes);
    }
}
