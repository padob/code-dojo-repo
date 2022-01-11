package com.pdb;

import com.pdb.utils.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class FileWritter {
    private final AtomicInteger atomicInteger;

    public FileWritter() {
        this.atomicInteger = new AtomicInteger(0);
    }

    public void write(String content) {
        //writes content
        if (StringUtils.isBlank(content)) {
            return;
        }
        atomicInteger.incrementAndGet();
    }


    public int getCallsNumber() {
        return atomicInteger.get();
    }
}
