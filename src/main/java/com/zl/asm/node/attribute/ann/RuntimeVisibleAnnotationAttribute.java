package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class RuntimeVisibleAnnotationAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(RuntimeVisibleAnnotationAttribute.class);
    private int numAnnotations;

    private Annotation[] annotations;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public RuntimeVisibleAnnotationAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        numAnnotations = ByteUtils.bytesToInt(bc.next(2));
        annotations = new Annotation[numAnnotations];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = new Annotation(bc, constantPoolNode);
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
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t\tnumAnnotations\t%d\n", numAnnotations);
        stringBuilder.append(formatter);
        for (Annotation annotation : annotations) {
            annotation.getLog(stringBuilder);
        }
    }

    @Override
    public void log(Logger log, boolean isParent) {
        log.info("numAnnotations:{}", numAnnotations);
        for (int i = 0; i < annotations.length; i++) {
            annotations[i].log(log, true);
        }
    }
}
