package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import com.zl.asm.node.AccessFlagType;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ClassTable implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(ClassTable.class);

    private int innerClassInfoIndex;

    private int outerClassInfoIndex;
    private int innerNameIndex;
    private AccessFlag innerClassAccessFlags;

    private int startIndex;

    private int endIndex;

    public ClassTable(ByteContainer bc) {
        startIndex = bc.getIndex();
        this.innerClassInfoIndex = ByteUtils.bytesToInt(bc.next(2));
        this.outerClassInfoIndex = ByteUtils.bytesToInt(bc.next(2));
        this.innerNameIndex = ByteUtils.bytesToInt(bc.next(2));
        this.innerClassAccessFlags = new AccessFlag(bc, AccessFlagType.CLASS_ACCESS_FLAG);
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ClassTable code: {}", bc.copy(startIndex, endIndex));
        }
    }

    public int getInnerClassInfoIndex() {
        return innerClassInfoIndex;
    }

    public int getOuterClassInfoIndex() {
        return outerClassInfoIndex;
    }

    public int getInnerNameIndex() {
        return innerNameIndex;
    }

    public AccessFlag getInnerClassAccessFlags() {
        return innerClassAccessFlags;
    }

    public void log(Logger logger, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("innerClassInfoIndex:|%03d|，outerClassInfoIndex:|%03d|, innerNameIndex:|%03d|, innerClassAccessFlags:%s", innerClassInfoIndex, outerClassInfoIndex, innerNameIndex, innerClassAccessFlags.getAccessFlags());
        logger.info("{}", formatter);
    }

    @Override
    public void accept(Reader reader) {

    }
}
