package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Magic implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Magic.class);
    private byte[] bytes;

    private int startIndex;
    private int endIndex;

    private String value;

    private byte[] allBytes;

    public Magic(ByteContainer bc) {
        startIndex = bc.getIndex();
        bytes = bc.next(4);
        endIndex = bc.getIndex() - 1;
        value = ByteUtils.toHexString(bytes);
        if (logger.isDebugEnabled()) {
            log(logger);
            logger.debug("MagicCode:{}", bc.copy(startIndex, endIndex));
        }
    }

    @Override
    public void getLog(StringBuilder stringBuilder) {
        Formatter formatter = new Formatter();
        formatter.format("magic:\t\t%s\n", value);
        stringBuilder.append(formatter);
    }


    @Override
    public void log(Logger log) {
        StringBuilder builder = new StringBuilder();
        getLog(builder);
        log.info("{}", builder);
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
