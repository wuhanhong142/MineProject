package com.mine.project.factory.abstractFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public class Client {
    public static void main(String[] args) {
        ComponentFactory factory = new WindowsComponentFactory();
        Button button = factory.createButton();
        Scroll scroll = factory.createScroll();

        button.show();
        scroll.show();


        factory = new MacosComponentFactory();
        button = factory.createButton();
        scroll = factory.createScroll();

        button.show();
        scroll.show();
    }
}
