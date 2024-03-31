package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

import java.util.Formatter;

public class LocalVariableTable implements ClassNode {

    private int startPc;
    private int length;
    private int nameIndex;
    private int descriptorIndex;
    private int index;

    public LocalVariableTable(ByteContainer bc) {
        this.startPc = ByteUtils.bytesToInt(bc.next(2));
        this.length = ByteUtils.bytesToInt(bc.next(2));
        this.nameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        this.index = ByteUtils.bytesToInt(bc.next(2));
    }

    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("startPc:|%03d|lineNumber:%d,nameIndex:%d,descriptorIndex:|%03d|, index:%d", startPc, length, nameIndex, descriptorIndex, index);
        logger.info("{}", formatter);
    }


    @Override
    public void accept(Reader reader) {

    }
}
