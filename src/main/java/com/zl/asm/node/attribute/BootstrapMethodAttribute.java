package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class BootstrapMethodAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(BootstrapMethodAttribute.class);
    private int numBootstrapMethods;

    private BootstrapMethod[] bootstrapMethods;

    private int endIndex;

    public BootstrapMethodAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numBootstrapMethods = ByteUtils.bytesToInt(bc.next(2));
        bootstrapMethods = new BootstrapMethod[numBootstrapMethods];
        for (int i = 0; i < bootstrapMethods.length; i++) {
            bootstrapMethods[i] = new BootstrapMethod(bc, constantPoolNode);
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
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tnumBootstrapMethods\t%s\n", numBootstrapMethods);
        stringBuilder.append(formatter);
        for (BootstrapMethod bootstrapMethod : bootstrapMethods) {
            bootstrapMethod.getLog(stringBuilder);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numBootstrapMethods:{}", numBootstrapMethods);
        for (BootstrapMethod bootstrapMethod : bootstrapMethods) {
            bootstrapMethod.log(log, isParent);
        }
    }
}
