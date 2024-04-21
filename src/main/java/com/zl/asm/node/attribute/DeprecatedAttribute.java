package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeprecatedAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(DeprecatedAttribute.class);

    private int endIndex;

    public DeprecatedAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("DeprecatedAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        stringBuilder.append("DeprecatedAttribute\n");
    }

    @Override
    public void log(Logger log, boolean isParent) {

    }
}
