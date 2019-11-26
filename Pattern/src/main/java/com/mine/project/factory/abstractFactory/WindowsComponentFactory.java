package com.mine.project.factory.abstractFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public class WindowsComponentFactory extends ComponentFactory {

    @Override
    Button createButton() {
        return new WindowsButton();
    }

    @Override
    Scroll createScroll() {
        return new WindowsScroll();
    }
}
