package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;

import java.util.Formatter;

public class LineNumbeTable implements ClassNode {

    private int startPc;

    private int lineNumber;

    public LineNumbeTable(ByteContainer bc) {
        startPc = ByteUtils.bytesToInt(bc.next(2));
        lineNumber = ByteUtils.bytesToInt(bc.next(2));
    }

    public void log(Logger logger) {
        Formatter formatter = new Formatter();
        formatter.format("startPc:|%03d|lineNumber:|%03d|", startPc, lineNumber);
        logger.info("{}", formatter);
    }

    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("startPc:|%03d|lineNumber:|%03d|", startPc, lineNumber);
        logger.info("{}", formatter);
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}
