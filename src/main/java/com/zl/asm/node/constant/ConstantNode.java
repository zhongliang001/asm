package com.zl.asm.node.constant;

import org.slf4j.Logger;

public abstract class ConstantNode {

    public abstract void log(Logger logger, boolean isParent);

    public abstract String getValue();

    public abstract void getLog(StringBuilder stringBuilder);
}
