package com.zl.asm;

public class ByteContainer {

    private byte[] bytes;

    private int index;

    public ByteContainer(byte[] bytes) {
        this.bytes = bytes;
        this.index = 0;
    }

    public byte next() {
        return bytes[index++];
    }

    public byte[] next(int num) {
        if (num <= 0) {
            return null;
        }
        byte[] result = new byte[num];
        for (int i = 0; i < num; i++) {
            result[i] = bytes[index++];
        }
        return result;
    }


    public int getIndex() {
        return index;
    }
}
