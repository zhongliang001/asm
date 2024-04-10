package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapMethodAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(BootstrapMethodAttribute.class);
    private int numBootstrapMethods;

    private BootstrapMethod[] bootstrapMethods;

    private int endIndex;

    public BootstrapMethodAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numBootstrapMethods = ByteUtils.bytesToInt(bc.next(2));
        bootstrapMethods = new BootstrapMethod[numBootstrapMethods];
        for (int i = 0; i < bootstrapMethods.length; i++) {
            bootstrapMethods[i] = new BootstrapMethod(bc);
        }
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("BootstrapMethodAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumBootstrapMethods() {
        return numBootstrapMethods;
    }

    public BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numBootstrapMethods:{}", numBootstrapMethods);
        for (BootstrapMethod bootstrapMethod : bootstrapMethods) {
            bootstrapMethod.log(log, isParent);
        }
    }
}
