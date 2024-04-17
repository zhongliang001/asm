package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NestHostAttribute extends Attribute {
    private final Logger logger = LoggerFactory.getLogger(NestHostAttribute.class);
    private int hostClassIndex;

    private int endIndex;

    public NestHostAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        hostClassIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("hostClassIndex code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("NestHostAttribute:{}", hostClassIndex);
    }
}
