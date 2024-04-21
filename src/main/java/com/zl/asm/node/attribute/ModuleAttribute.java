package com.zl.asm.node.attribute;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.AccessFlag;
import com.zl.asm.node.AccessFlagType;
import com.zl.asm.node.constant.ConstantNode;
import com.zl.asm.node.constant.ConstantPoolNode;
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

    private ConstantPoolNode constantPoolNode;

    public ModuleAttribute(ByteContainer bc, ConstantPoolNode constantPoolNode, int attributeNameIndex) {
        super(bc, attributeNameIndex);
        this.constantPoolNode = constantPoolNode;
        moduleNameIndex = ByteUtils.bytesToInt(bc.next(2));
        moduleFlags = new AccessFlag(bc, AccessFlagType.MODULE_ACCESS_FLAG);
        moduleVersionIndex = ByteUtils.bytesToInt(bc.next(2));
        requiresCount = ByteUtils.bytesToInt(bc.next(2));
        requires = new Require[requiresCount];
        for (int i = 0; i < requires.length; i++) {
            requires[i] = new Require(bc, constantPoolNode);
        }
        exportsCount = ByteUtils.bytesToInt(bc.next(2));
        exports = new Export[exportsCount];
        for (int i = 0; i < exports.length; i++) {
            exports[i] = new Export(bc, constantPoolNode);
        }
        opensCount = ByteUtils.bytesToInt(bc.next(2));
        opens = new Open[opensCount];
        for (int i = 0; i < opens.length; i++) {
            opens[i] = new Open(bc, constantPoolNode);
        }
        usesCount = ByteUtils.bytesToInt(bc.next(2));
        usesIndex = new int[usesCount];
        for (int i = 0; i < usesIndex.length; i++) {
            usesIndex[i] = ByteUtils.bytesToInt(bc.next(2));
        }
        providesCount = ByteUtils.bytesToInt(bc.next(2));
        provides = new Provide[providesCount];
        for (int i = 0; i < provides.length; i++) {
            provides[i] = new Provide(bc, constantPoolNode);
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
    public void getLog(StringBuilder stringBuilder) {
        ConstantNode[] constantNodes = constantPoolNode.getConstantNodes();
        ConstantNode constantNode = constantNodes[moduleNameIndex - 1];
        ConstantNode versionConstantNode = constantNodes[moduleVersionIndex - 1];
        Formatter formatter = new Formatter();
        formatter.format("\tModuleAttribute\tmoduleName\t%s\tmoduleFlags\t%s\tversionConstantNode\t%s\t\n\t\trequiresCount\t%d\n",
                constantNode.getValue(), moduleFlags.getAccessStr(), versionConstantNode.getValue(), requiresCount);
        stringBuilder.append(formatter);
        for (Require require : requires) {
            require.getLog(stringBuilder);
        }
        Formatter exportFormatter = new Formatter();
        exportFormatter.format("\t\texportsCount\t%d\n", exportsCount);
        stringBuilder.append(exportFormatter);
        for (Export export : exports) {
            export.getLog(stringBuilder);
        }
        Formatter openFormatter = new Formatter();
        openFormatter.format("\t\topensCount\t%d\n", opensCount);
        stringBuilder.append(openFormatter);
        for (Open open : opens) {
            open.getLog(stringBuilder);
        }
        Formatter useFormatter = new Formatter();
        useFormatter.format("\t\tusesCount\t%d\n", usesCount);
        for (int index : usesIndex) {
            ConstantNode indexConstantNode = constantNodes[index - 1];
            useFormatter.format("\t\t\t%s\n", indexConstantNode.getValue());
        }
        stringBuilder.append(useFormatter);
        Formatter proFormatter = new Formatter();
        proFormatter.format("\t\tprovidesCount\t%d\n", providesCount);
        for (Provide provide : provides) {
            provide.getLog(stringBuilder);
        }
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
