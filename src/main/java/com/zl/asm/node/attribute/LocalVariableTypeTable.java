package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class LocalVariableTypeTable implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(LocalVariableTypeTable.class);

    private int startPc;

    private int length;

    private int nameIndex;

    private int signatureIndex;

    private int index;

    private int startIndex;

    private int endIndex;

    public LocalVariableTypeTable(ByteContainer bc) {
        this.startIndex = bc.getIndex();
        this.startPc = ByteUtils.bytesToInt(bc.next(2));
        this.length = ByteUtils.bytesToInt(bc.next(2));
        this.nameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.signatureIndex = ByteUtils.bytesToInt(bc.next(2));
        this.index = ByteUtils.bytesToInt(bc.next(2));
        this.endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("LocalVariableTypeTable code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public void log(Logger log, boolean isParent) {
        if (isParent) {
            Formatter formatter = new Formatter();
            formatter.format("startPc:|%d|length:%d,nameIndex:|%03d|,signatureIndex:|%03d|,index:%d", startPc, length, nameIndex, signatureIndex, index);
            logger.info("{}", formatter);
        }
    }

    @Override
    public void accept(Reader reader) {

    }
}
