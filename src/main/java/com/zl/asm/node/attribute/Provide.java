package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.ClassNode;
import com.zl.asm.reader.Reader;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Provide implements ClassNode {

    private final Logger logger = LoggerFactory.getLogger(Provide.class);
    private int providesIndex;
    private int providesWithCount;

    private int[] providesWithIndex;

    private int startIndex;

    private int endIndex;

    public Provide(ByteContainer byteContainer) {
        startIndex = byteContainer.getIndex();
        providesIndex = ByteUtils.bytesToInt(byteContainer.next(2));
        providesWithCount = ByteUtils.bytesToInt(byteContainer.next(2));
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithIndex.length; i++) {
            providesWithIndex[i] = ByteUtils.bytesToInt(byteContainer.next(2));
        }
        endIndex = byteContainer.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("Open code:{}", byteContainer.copy(startIndex, endIndex));
        }
    }

    public int getProvidesIndex() {
        return providesIndex;
    }

    public int getProvidesWithCount() {
        return providesWithCount;
    }

    public int[] getProvidesWithIndex() {
        return providesWithIndex;
    }

    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("providesIndex:|%03d|,providesWithCount:%d,providesWithIndex:", providesIndex, providesWithCount);
        for (int toIndex : providesWithIndex) {
            formatter.format("|%03d|\t", toIndex);
        }
        log.info("{}", formatter);
    }

    @Override
    public void accept(Reader reader) {

    }
}
