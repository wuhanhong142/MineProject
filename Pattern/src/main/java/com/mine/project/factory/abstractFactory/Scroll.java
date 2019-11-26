package com.mine.project.factory.abstractFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public abstract class Scroll {
    private String type = "Scroll";
    private String lookAndFeel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLookAndFeel() {
        return lookAndFeel;
    }

    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }

    public void show() {
        System.out.println("显示"+ lookAndFeel + "风格的" + type);
    }
}
