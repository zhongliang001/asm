package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.attribute.ParameterAnnotation;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class RuntimeInvisibleParameterAnnAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(RuntimeInvisibleParameterAnnAttribute.class);

    private int numParameters;

    private ParameterAnnotation[] parameterAnnotations;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public RuntimeInvisibleParameterAnnAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numParameters = ByteUtils.bytesToInt(bc.next(1));
        parameterAnnotations = new ParameterAnnotation[numParameters];
        for (int i = 0; i < parameterAnnotations.length; i++) {
            parameterAnnotations[i] = new ParameterAnnotation(bc, constantPoolNode);
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
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tRuntimeInvisibleParameterAnnAttribute\tnumParameters\t%d\n", numParameters);
        stringBuilder.append(formatter);
        for (ParameterAnnotation parameterAnnotation : parameterAnnotations) {
            parameterAnnotation.getLog(stringBuilder);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numParameters: {}", numParameters);
        for (ParameterAnnotation parameterAnnotation : parameterAnnotations) {
            parameterAnnotation.log(log, true);
        }
    }
}
