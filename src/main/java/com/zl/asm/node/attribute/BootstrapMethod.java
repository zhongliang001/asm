package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class BootstrapMethod implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(BootstrapMethod.class);

    private int bootstrapMethodRef;

    private int numBootstrapArguments;

    private int[] bootstrapArguments;

    private int startIndex;

    private int endIndex;

    public BootstrapMethod(ByteContainer bc) {
        startIndex = bc.getIndex();
        bootstrapMethodRef = ByteUtils.bytesToInt(bc.next(2));
        numBootstrapArguments = ByteUtils.bytesToInt(bc.next(2));
        bootstrapArguments = new int[numBootstrapArguments];
        for (int i = 0; i < bootstrapArguments.length; i++) {
            bootstrapArguments[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("BootstrapMethod code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getBootstrapMethodRef() {
        return bootstrapMethodRef;
    }

    public int getNumBootstrapArguments() {
        return numBootstrapArguments;
    }

    public int[] getBootstrapArguments() {
        return bootstrapArguments;
    }

    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("bootstrapMethodRef:|%03d|numBootstrapArguments:%d", bootstrapMethodRef, numBootstrapArguments);
        logger.info("{}", formatter);
        logger.info("bootstrapArguments:{}", bootstrapArguments);
    }

    @Override
    public void accept(Reader reader) {

    }
}
