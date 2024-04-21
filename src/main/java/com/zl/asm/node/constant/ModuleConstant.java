package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ModuleConstant extends ConstantNode {
    private final Logger logger = LoggerFactory.getLogger(ModuleConstant.class);
    private int tag;

    private int index;
    private int nameIndex;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public ModuleConstant(ByteContainer bc, ConstantPoolNode constantPoolNode, int tag, int index) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        this.tag = tag;
        this.index = index;
        this.nameIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("ModuleConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNameIndex() {
        return nameIndex;
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("|%03d|\t|%s|\t|%03d|", index, ModuleConstant.class.getSimpleName(), nameIndex);
        logger.info("{}", formatter);
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t|%03d|\t|%s|\t#%03d\n", index, ModuleConstant.class.getSimpleName(), nameIndex);
        stringBuilder.append(formatter);
    }

    @Override
    public String getValue() {
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[nameIndex - 1];
        return constantNode.getValue();
    }
}
