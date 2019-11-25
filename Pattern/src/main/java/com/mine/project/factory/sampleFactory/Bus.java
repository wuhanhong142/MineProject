package com.mine.project.factory.sampleFactory;

/**
 * @author wuhanhong
 * @date 2019-11-25
 * @descp
 */
public class Bus extends Auto {

    public Bus(){
        setName("Bus");
    }

    @Override
    public void run() {
        System.out.println("创建AUTO:" +getName());
    }
}
