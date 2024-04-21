package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

public abstract class Attribute {

    protected int attributeNameIndex;
    protected int attributeLength;

    protected int startIndex;

    public Attribute(ByteContainer bc, int attributeNameIndex) {
        this.startIndex = bc.getIndex();
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = ByteUtils.bytesToInt(bc.next(4));
    }

    public abstract void getLog(StringBuilder stringBuilder);

    public abstract void log(Logger log, boolean isParent);

    public void log(Logger logger) {
    }

}
