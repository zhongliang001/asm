package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class RuntimeInvisibleTypeAnnAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(RuntimeInvisibleTypeAnnAttribute.class);
    private int numAnnotations;

    private TypeAnnotation[] typeAnnotations;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public RuntimeInvisibleTypeAnnAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        numAnnotations = ByteUtils.bytesToInt(bc.next(2));
        typeAnnotations = new TypeAnnotation[numAnnotations];
        for (int i = 0; i < typeAnnotations.length; i++) {
            typeAnnotations[i] = new TypeAnnotation(bc, constantPoolNode);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("RuntimeInvisibleTypeAnnAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumAnnotations() {
        return numAnnotations;
    }

    public TypeAnnotation[] getTypeAnnotations() {
        return typeAnnotations;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\tRuntimeInvisibleTypeAnnAttribute\tnumParameters\t%d\n", numAnnotations);
        stringBuilder.append(formatter);
        for (TypeAnnotation typeAnnotation : typeAnnotations) {
            typeAnnotation.getLog(stringBuilder);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numAnnotations: {}", numAnnotations);
        for (TypeAnnotation typeAnnotation : typeAnnotations) {
            typeAnnotation.log(log, true);
        }
    }
}
