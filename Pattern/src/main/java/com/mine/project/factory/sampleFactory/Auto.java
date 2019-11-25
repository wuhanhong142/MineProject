package com.mine.project.factory.sampleFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public abstract class Auto {
    private String name;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public void run();
}
