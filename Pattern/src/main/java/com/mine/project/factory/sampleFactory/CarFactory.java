package com.mine.project.factory.sampleFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public class CarFactory extends Factory {
    @Override
    public Auto createAuto() {
        return new Car();
    }
}
