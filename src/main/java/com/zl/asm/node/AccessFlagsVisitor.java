package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccessFlagsVisitor implements ClassNode {

    private Logger logger = LoggerFactory.getLogger(AccessFlagsVisitor.class);

    private byte[] accessBytes;

    private String accessString;
    private List<String> accessFlags;

    public AccessFlagsVisitor(ByteContainer bc, int type) {
        accessBytes = bc.next(2);
        int i = ByteUtils.bytesToInt(accessBytes);
        accessString = "Ox" + ByteUtils.toHexString(accessBytes);
        accessFlags = AccessFlagsFormatter.getAccessFlag(ByteUtils.bytesToInt(accessBytes), type);
    }

    public void accept() {
        logger.info("accessString:{},accessFlags:{}", accessString, accessFlags.toArray());
    }

    @Override
    public void accept(Reader reader) {

    }
}
