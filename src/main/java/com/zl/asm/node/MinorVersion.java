package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinorVersion implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(MinorVersion.class);
    private byte[] minorVersion;
    private byte[] majorVersion;
    private int startIndex;
    private int endIndex;
    private byte[] bytes;

    private String value;

    public MinorVersion(ByteContainer bc) {
        startIndex = bc.getIndex();
        minorVersion = bc.next(2);
        majorVersion = bc.next(2);
        endIndex = bc.getIndex() - 1;
        bytes = new byte[minorVersion.length + majorVersion.length];
        System.arraycopy(majorVersion, 0, bytes, 0, majorVersion.length);
        System.arraycopy(minorVersion, 0, bytes, majorVersion.length, majorVersion.length);
        value = ByteUtils.bytesToInt(majorVersion) + "." + ByteUtils.bytesToInt(minorVersion);
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.debug("MinorCode:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void log(Logger logger) {
        logger.info("version:{}", value);
        logger.info("bytes:{}", bytes);
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
