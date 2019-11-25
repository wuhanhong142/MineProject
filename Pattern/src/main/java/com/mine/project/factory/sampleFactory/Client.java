package com.mine.project.factory.sampleFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new CarFactory();
        Auto auto = factory.createAuto();
        auto.run();

        factory = new BusFactory();
        auto = factory.createAuto();
        auto.run();

    }
}
