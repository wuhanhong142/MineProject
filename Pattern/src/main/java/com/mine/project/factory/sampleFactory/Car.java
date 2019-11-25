package com.mine.project.factory.sampleFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public class Car extends Auto {

    public Car(){
        setName("Car");
    }

    @Override
    public void run() {
        System.out.println("创建AUTO:" +getName());
    }
}
