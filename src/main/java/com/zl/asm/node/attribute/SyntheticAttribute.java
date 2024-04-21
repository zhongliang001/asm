package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyntheticAttribute extends Attribute {
    private final Logger logger = LoggerFactory.getLogger(SyntheticAttribute.class);

    private int endIndex;

    public SyntheticAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("SyntheticAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        stringBuilder.append("\tSyntheticAttribute\n");
    }


    @Override
    public void log(Logger log, boolean isParent) {

    }
}
