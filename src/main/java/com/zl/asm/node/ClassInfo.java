package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ClassInfo implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(ClassInfo.class);

    private AccessFlag accessFlag;
    private int thisClass;

    private int superClass;

    private int interfaceCount;

    private int[] interFaces;

    private int startIndex;

    private int endIndex;

    public ClassInfo(ByteContainer bc) {
        startIndex = bc.getIndex();
        accessFlag = new AccessFlag(bc, AccessFlagType.CLASS_ACCESS_FLAG);
        thisClass = ByteUtils.bytesToInt(bc.next(2));
        superClass = ByteUtils.bytesToInt(bc.next(2));
        interfaceCount = ByteUtils.bytesToInt(bc.next(2));
        interFaces = new int[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            interFaces[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.debug("ClassInfo code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public int getThisClass() {
        return thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public int getInterfaceCount() {
        return interfaceCount;
    }

    public int[] getInterFaces() {
        return interFaces;
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("classInfo:\n\tthisClass\t#%03d\n\tsuperClass\t#%03d\n\tinterfaceCount\t%d\n\tinterFaces", thisClass, superClass, interfaceCount);
        for (int i = 0; i < interFaces.length; i++) {
            formatter.format("\t#%03d", interFaces[i]);
        }
        stringBuilder.append(formatter).append("\n");
    }

    @Override
    public void log(Logger logger) {
        accessFlag.log(logger, true);
        Formatter formatter = new Formatter();
        formatter.format("thisClass:%03d,superClass:%03d,interfaceCount:%03d", thisClass, superClass, interfaceCount);
        logger.info("{},interFaces:{}", formatter, interFaces);
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}