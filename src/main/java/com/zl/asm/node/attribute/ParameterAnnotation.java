package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.ann.Annotation;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterAnnotation {

    private final Logger logger = LoggerFactory.getLogger(ParameterAnnotation.class);
    private int startIndex;
    private int numAnnotations;

    private Annotation[] annotations;

    private int endIndex;


    public ParameterAnnotation(ByteContainer bc) {
        startIndex = bc.getIndex();
        numAnnotations = ByteUtils.bytesToInt(bc.next(2));
        annotations = new Annotation[numAnnotations];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = new Annotation(bc);
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
}
