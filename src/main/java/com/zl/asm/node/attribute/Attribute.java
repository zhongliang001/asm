package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Attribute implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Attribute.class);
    public int attributeNameIndex;

    public int attributeLength;

    public Attribute(ByteContainer bc, int attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = ByteUtils.bytesToInt(bc.next(4));
    }

    public abstract void log(Logger log, boolean isParent);

    public void accept(Reader reader) {
        reader.read(this);
    }
}
