package com.zl.asm.node.constant;

import com.zl.asm.ByteContainer;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class NameAndTypeConstant extends ConstantNode {

    private final Logger logger = LoggerFactory.getLogger(NameAndTypeConstant.class);

    private int tag;
    private int index;

    private int nameIndex;
    private int descriptorIndex;

    private int startIndex;

    private int endIndex;

    private ConstantPoolNode constantPoolNode;


    public NameAndTypeConstant(ByteContainer bc, ConstantPoolNode constantPoolNode, int tag, int index) {
        this.constantPoolNode = constantPoolNode;
        startIndex = bc.getIndex();
        this.tag = tag;
        this.index = index;
        this.nameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.debug("NameAndTypeConstant code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void accept() {
        logger.info("index:{},tag:{},nameIndex:{},descriptorIndex:{}", index, tag, nameIndex, descriptorIndex);
    }

    @Override
    public void log(Logger logger, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("|%03d|\t|%s|\t|%03d|\t|%03d|", index, NameAndTypeConstant.class.getSimpleName(), nameIndex, descriptorIndex);
            logger.info("{}", formatter);
        } else {
            logger.info("index:{}, tag:{}, classIndex:{},name_and_type_index:{}", index, tag, nameIndex, descriptorIndex);
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("\t|%03d|\t|%s|\t#%03d.#%03d\n", index, NameAndTypeConstant.class.getSimpleName(), nameIndex, descriptorIndex);
        stringBuilder.append(formatter);
    }

    @Override
    public String getValue() {
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode nameConstantNode = constantNodes[nameIndex - 1];
        ConstantNode constantNode = constantNodes[descriptorIndex - 1];
        return nameConstantNode.getValue() + "--" + constantNode.getValue();
    }
}
