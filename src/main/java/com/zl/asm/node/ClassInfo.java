package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ClassInfo implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(ClassInfo.class);

    private AccessFlagsVisitor accessFlagsVisitor;
    private int thisClass;

    private int superClass;

    private int interfaceCount;

    private int[] interFaces;

    public ClassInfo(ByteContainer bc) {
        accessFlagsVisitor = new AccessFlagsVisitor(bc, AccessFlagType.CLASS_ACCESS_FLAG);
        thisClass = ByteUtils.bytesToInt(bc.next(2));
        superClass = ByteUtils.bytesToInt(bc.next(2));
        interfaceCount = ByteUtils.bytesToInt(bc.next(2));
        interFaces = new int[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            interFaces[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        if (logger.isDebugEnabled()) {
            log(logger);
        }
    }

    public void accept() {
        accessFlagsVisitor.accept();
        logger.info("thisClass:{}", thisClass);
        logger.info("superClass:{}", superClass);
        logger.info("interfaceCount:{}", interfaceCount);
        logger.info("interFaces:{}", interFaces);

    }

    @Override
    public void log(Logger logger) {
        Formatter formatter = new Formatter();
        formatter.format("thisClass:%03d,superClass:%03d,interfaceCount:%03d", thisClass, superClass, interfaceCount);
        logger.info("{},interFaces:{}", formatter, interFaces);
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}