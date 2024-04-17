package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnclosingMethodAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(EnclosingMethodAttribute.class);

    private int classIndex;
    private int methodIndex;

    private int endIndex;

    public EnclosingMethodAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        classIndex = ByteUtils.bytesToInt(bc.next(2));
        methodIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("EnclosingMethodAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("classIndex:{},methodIndex:{}", classIndex, methodIndex);
    }
}
