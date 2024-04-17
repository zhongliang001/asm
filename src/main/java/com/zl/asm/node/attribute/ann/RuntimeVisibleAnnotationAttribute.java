package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimeVisibleAnnotationAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(RuntimeVisibleAnnotationAttribute.class);
    private int numAnnotations;

    private Annotation[] annotations;

    private int endIndex;

    public RuntimeVisibleAnnotationAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        numAnnotations = ByteUtils.bytesToInt(bc.next(2));
        annotations = new Annotation[numAnnotations];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = new Annotation(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("RuntimeVisibleAnnotationAttribute code: {}", bc.copy(startIndex, endIndex));
        }

    }

    public int getNumAnnotations() {
        return numAnnotations;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numAnnotations:{}", numAnnotations);
        for (int i = 0; i < annotations.length; i++) {
            annotations[i].log(log, true);
        }
    }
}
