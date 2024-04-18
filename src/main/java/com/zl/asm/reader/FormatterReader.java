package com.zl.asm.reader;

import com.zl.asm.node.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FormatterReader implements Reader {

    private final Logger logger = LoggerFactory.getLogger(FormatterReader.class);

    private List<ClassNode> classNodes;

    public FormatterReader() {
        classNodes = new ArrayList<>(16);
    }

    @Override
    public void read(ClassNode node) {
        classNodes.add(node);
    }

    public void log() {
        StringBuilder builder = new StringBuilder();
        classNodes.forEach(node -> node.getLog(builder));
        logger.info("\n{}", builder);
    }
}

