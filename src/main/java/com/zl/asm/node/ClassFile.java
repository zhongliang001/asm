package com.zl.asm.node;

import com.zl.asm.ByteContainer;
import com.zl.asm.node.constant.ConstantPoolNode;
import com.zl.asm.reader.Reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class ClassFile implements ClassNode {

    private ByteContainer bc;
    private Magic magic;
    private MinorVersion minorVersion;
    private ConstantPoolNode constantPoolNode;

    private ClassInfo classInfo;

    private Field filed;

    private Method method;

    private SourceFile sourceFile;

    public ClassFile(String path) {
        URL resource = this.getClass().getClassLoader().getResource(path);
        if (resource != null) {
            try (FileInputStream fileInputStream = new FileInputStream(resource.getPath())) {
                byte[] bytes = fileInputStream.readAllBytes();
                bc = new ByteContainer(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        init();
    }

    public void init() {
        magic = new Magic(bc);
        minorVersion = new MinorVersion(bc);
        constantPoolNode = new ConstantPoolNode(bc);
        classInfo = new ClassInfo(bc);
        filed = new Field(bc, constantPoolNode);
        method = new Method(bc, constantPoolNode);
        sourceFile = new SourceFile(bc, constantPoolNode);
    }

    public ByteContainer getBc() {
        return bc;
    }

    public void setBc(ByteContainer bc) {
        this.bc = bc;
    }

    @Override
    public void accept(Reader reader) {
        magic.accept(reader);
        minorVersion.accept(reader);
        constantPoolNode.accept(reader);
        classInfo.accept(reader);
        filed.accept(reader);
        method.accept(reader);
        sourceFile.accept(reader);
    }
}
