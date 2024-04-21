package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class InterfaceMethodrefConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(InterfaceMethodrefConstant.class);

    private int tag;

    private int index;

    private int classIndex;
    // 指向方法
    private int nameAndTypeIndex;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;

    public InterfaceMethodrefConstant(ByteContainer bc, ConstantPoolNode constantPoolNode, int tag, int index) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        this.index = index;
        this.tag = tag;
        classIndex = ByteUtils.bytesToInt(bc.next(2));
        nameAndTypeIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("InterfaceMethodrefConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%03d|", index, InterfaceMethodrefConstant.class.getSimpleName(), classIndex, nameAndTypeIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, classIndex:{},name_and_type_index:{}", index, tag, classIndex, nameAndTypeIndex);
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t|%03d|\t|%s|\t#%03d.#%03d\n", index, InterfaceMethodrefConstant.class.getSimpleName(), classIndex, nameAndTypeIndex);
        stringBuilder.append(formatter);
    }

    @Override
    public String getValue() {
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode nameConstantNode = constantNodes[classIndex - 1];
        ConstantNode constantNode = constantNodes[nameAndTypeIndex - 1];
        return nameConstantNode.getValue() + "--" + constantNode.getValue();
    }
}
