package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import com.zl.asm.node.AccessFlagType;
import com.zl.asm.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class ModuleAttribute extends Attribute {

    private final Logger logger = LoggerFactory.getLogger(ModuleAttribute.class);

    private int moduleNameIndex;

    private AccessFlag moduleFlags;

    private int moduleVersionIndex;

    private int requiresCount;

    private Require[] requires;

    private int exportsCount;

    private Export[] exports;

    private int opensCount;

    private Open[] opens;

    private int usesCount;

    private int[] usesIndex;

    private int providesCount;

    private Provide[] provides;

    private int endIndex;

    public ModuleAttribute(ByteContainer bc, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        moduleNameIndex = ByteUtils.bytesToInt(bc.next(2));
        moduleFlags = new AccessFlag(bc, AccessFlagType.MODULE_ACCESS_FLAG);
        moduleVersionIndex = ByteUtils.bytesToInt(bc.next(2));
        requiresCount = ByteUtils.bytesToInt(bc.next(2));
        requires = new Require[requiresCount];
        for (int i = 0; i < requires.length; i++) {
            requires[i] = new Require(bc);
        }
        exportsCount = ByteUtils.bytesToInt(bc.next(2));
        exports = new Export[exportsCount];
        for (int i = 0; i < exports.length; i++) {
            exports[i] = new Export(bc);
        }
        opensCount = ByteUtils.bytesToInt(bc.next(2));
        opens = new Open[opensCount];
        for (int i = 0; i < opens.length; i++) {
            opens[i] = new Open(bc);
        }
        usesCount = ByteUtils.bytesToInt(bc.next(2));
        usesIndex = new int[usesCount];
        for (int i = 0; i < usesIndex.length; i++) {
            usesIndex[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        providesCount = ByteUtils.bytesToInt(bc.next(2));
        provides = new Provide[providesCount];
        for (int i = 0; i < provides.length; i++) {
            provides[i] = new Provide(bc);
        }
        endIndex = bc.getIndex() - 1;
        if (logger.isDebugEnabled()) {
            log(logger, false);
            logger.info("ModuleAttribute code:{}", bc.copy(startIndex, endIndex));
        }
    }

    public int getModuleNameIndex() {
        return moduleNameIndex;
    }

    public AccessFlag getModuleFlags() {
        return moduleFlags;
    }

    public int getModuleVersionIndex() {
        return moduleVersionIndex;
    }

    public int getRequiresCount() {
        return requiresCount;
    }

    public Require[] getRequires() {
        return requires;
    }

    public int getExportsCount() {
        return exportsCount;
    }

    public Export[] getExports() {
        return exports;
    }

    public int getOpensCount() {
        return opensCount;
    }

    public Open[] getOpens() {
        return opens;
    }

    public int getUsesCount() {
        return usesCount;
    }

    public int[] getUsesIndex() {
        return usesIndex;
    }

    public int getProvidesCount() {
        return providesCount;
    }

    public Provide[] getProvides() {
        return provides;
    }

    @Override
    public void log(Logger log, boolean isParent) {
        Formatter formatter = new Formatter();
        formatter.format("moduleNameIndex:|%03d|,moduleVersionIndex:|%03d|", moduleNameIndex, moduleVersionIndex);
        log.info("{},moduleFlags:{}", formatter, moduleFlags.getAccessFlags());
        log.info("requiresCount:{}", requiresCount);
        for (Require require : requires) {
            require.log(log, true);
        }
        log.info("exportsCount:{}", exportsCount);
        for (Export export : exports) {
            export.log(log, true);
        }
        log.info("opensCount:{}", opensCount);
        for (Open open : opens) {
            open.log(log, true);
        }
        log.info("usesCount:{}, usesIndex：{}", usesCount, usesIndex);
        log.info("providesCount:{}", providesCount);
        for (Provide provide : provides) {
            provide.log(log, true);
        }

    }
}
