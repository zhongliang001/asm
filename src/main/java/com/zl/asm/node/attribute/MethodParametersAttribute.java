package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class MethodParametersAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(MethodParametersAttribute.class);

    private int parametersCount;

    private Parameter[] parameters;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public MethodParametersAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        parametersCount = ByteUtils.byteToUnsignedInt(bc.next());
        parameters = new Parameter[parametersCount];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = new Parameter(bc, constantPoolNode);
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
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tMethodParametersAttribute\tparametersCount\t%d\n", parametersCount);
        stringBuilder.append(formatter);
        for (Parameter parameter : parameters) {
            parameter.getLog(stringBuilder);
        }
    }


    @Override
    public void log(Logger log, boolean isParent) {
        log.info("parametersCount: {}", parametersCount);
        for (Parameter parameter : parameters) {
            parameter.log(log, isParent);
        }
    }
}
