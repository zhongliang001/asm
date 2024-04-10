package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignatureAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(SignatureAttribute.class);

    private int signatureIndex;
    private int endIndex;

    public SignatureAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        signatureIndex = ByteUtils.bytesToInt(bc.next(2));
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ConstantAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }


    @Override
    public void log(Logger log, boolean isParent) {
        log.info("signatureIndex:{}", signatureIndex);
    }
}
