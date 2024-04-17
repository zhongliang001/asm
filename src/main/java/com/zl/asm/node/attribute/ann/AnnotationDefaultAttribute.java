package com.zl.asm.node.attribute.ann;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.attribute.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationDefaultAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(AnnotationDefaultAttribute.class);
    private ElementValue elementValue;

    private int endIndex;

    public AnnotationDefaultAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        elementValue = new ElementValue(bc);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("AnnotationDefaultAttribute code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public ElementValue getElementValue() {
        return elementValue;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        elementValue.log(log, true);
    }
}
