package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.ann.Annotation;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ParameterAnnotation {

    private final Logger logger = LoggerFactory.getLogger(ParameterAnnotation.class);
    private int startIndex;
    private int numAnnotations;

    private Annotation[] annotations;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public ParameterAnnotation(ByteContainer bc, ConstantPoolNode constantPoolNode) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        numAnnotations = ByteUtils.bytesToInt(bc.next(2));
        annotations = new Annotation[numAnnotations];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = new Annotation(bc, constantPoolNode);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ParameterAnnotation code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNumAnnotations() {
        return numAnnotations;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public void log(Logger log, boolean isPanrent) {
        log.info("numAnnotations: {}", numAnnotations);
        for (Annotation annotation : annotations) {
            annotation.log(log, true);
        }
    }

    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\t\tParameterAnnotation\tnumAnnotations\t%d\n", numAnnotations);
        stringBuilder.append(formatter);
        for (Annotation annotation : annotations) {
            annotation.getLog(stringBuilder);
        }

    }
}
