package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LocalVariableTable implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(LocalVariableTable.class);

    private int startPc;
    private int length;
    private int nameIndex;
    private int descriptorIndex;
    private int index;

    private int startIndex;

    private int endIndex;

    public LocalVariableTable(ByteContainer bc) {
        this.startIndex = bc.getIndex();
        this.startPc = ByteUtils.bytesToInt(bc.next(2));
        this.length = ByteUtils.bytesToInt(bc.next(2));
        this.nameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.descriptorIndex = ByteUtils.bytesToInt(bc.next(2));
        this.index = ByteUtils.bytesToInt(bc.next(2));
        this.endIndex = bc.getIndex()-1;
        if(logger.isDebugEnabled()){
            log(logger, false);
            logger.info("LocalVariableTable code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
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
