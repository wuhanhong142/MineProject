package com.mine.project.factory.abstractFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public class MacosComponentFactory extends ComponentFactory {

    @Override
    Button createButton() {
        return new MacosButton();
    }

    @Override
    Scroll createScroll() {
        return new MacosScroll();
    }
}
