package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.attribute.ParameterAnnotation;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimeInvisibleParameterAnnAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(RuntimeInvisibleParameterAnnAttribute.class);

    private int numParameters;

    private ParameterAnnotation[] parameterAnnotations;

    private int endIndex;

    public RuntimeInvisibleParameterAnnAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numParameters = ByteUtils.bytesToInt(bc.next(1));
        parameterAnnotations = new ParameterAnnotation[numParameters];
        for (int i = 0; i < parameterAnnotations.length; i++) {
            parameterAnnotations[i] = new ParameterAnnotation(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("RuntimeInvisibleParameterAnnAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumParameters() {
        return numParameters;
    }

    public ParameterAnnotation[] getParameterAnnotations() {
        return parameterAnnotations;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numParameters: {}", numParameters);
        for (ParameterAnnotation parameterAnnotation : parameterAnnotations) {
            parameterAnnotation.log(log, true);
        }
    }
}
