package tech.kaijie.proxy;

/**
 * 对生产厂家要求的接口
 */
public interface IProducer {
    /**
     * 销售
     * @param money
     */
    public void saleProduct(Float money);

    /**
     * 售后
     * @param money
     */
    public void afterService(Float money);
}
