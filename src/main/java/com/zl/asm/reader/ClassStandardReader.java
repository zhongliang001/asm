package com.zl.asm.reader;

import com.zl.asm.node.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassStandardReader implements Reader {

    private final Logger logger = LoggerFactory.getLogger(ClassStandardReader.class);

    private List<ClassNode> classNodes;

    public ClassStandardReader() {
        this.classNodes = new ArrayList<>(16);
    }

    @Override
    public void read(ClassNode node) {
        classNodes.add(node);
    }

    public void log() {
        classNodes.forEach(node -> node.log(logger));
    }

}
