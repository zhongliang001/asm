package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Magic implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Magic.class);
    private byte[] bytes;

    private int startIndex;
    private int endIndex;

    private String value;

    public Magic(ByteContainer bc) {
        startIndex = bc.getIndex();
        bytes = bc.next(4);
        endIndex = bc.getIndex();
        value = ByteUtils.toHexString(bytes);
        if (logger.isDebugEnabled()) {
            log(logger);
        }
    }

    @Override
    public void log(Logger log) {
        log.info("magic:{}", value);
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(Reader reader) {
        reader.read(this);
    }
}
