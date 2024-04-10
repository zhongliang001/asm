package com.zl.asm.node.constant;

import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import org.slf4j.Logger;

public abstract class ConstantNode implements ClassNode {

    public abstract void log(Logger logger, boolean isParent);

    public void accept(Reader reader) {
        reader.read(this);
    }

    public abstract String getValue();
}
