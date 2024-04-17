package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodParametersAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(MethodParametersAttribute.class);

    private int parametersCount;

    private Parameter[] parameters;

    private int endIndex;

    public MethodParametersAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        parametersCount = ByteUtils.byteToUnsignedInt(bc.next());
        parameters = new Parameter[parametersCount];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = new Parameter(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("MethodParametersAttribute code: {}", bc.copy(startIndex, endIndex));
        }

    }

    public int getParametersCount() {
        return parametersCount;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("parametersCount: {}", parametersCount);
        for (Parameter parameter : parameters) {
            parameter.log(log, isParent);
        }
    }
}
