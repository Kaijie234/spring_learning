package tech.kaijie.cglib;

import tech.kaijie.proxy.IProducer;

/**
 * 一个生产者
 */
public class Producer {
    /**
     * 销售
     * @param money
     */
    public void saleProduct(Float money)
    {
        System.out.println("销售产品，并拿到钱：" +money);
    }

    /**
     * 售后
     * @param money
     */
    public void afterService(Float money)
    {
        System.out.println("提供售后服务，并拿到钱：" + money);
    }
}
