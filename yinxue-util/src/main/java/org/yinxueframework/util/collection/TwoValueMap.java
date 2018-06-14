package org.yinxueframework.util.collection;

/**
 * 双value map接口
 * 不成功的操作，以抛异常作为返回。
 *
 * @author zengjian
 * @create 2018-04-22 15:14
 * @since 1.0.0
 */
public interface TwoValueMap<K, V1, V2> {


    /**
     * 存储value1 value2到指定key下
     * @param key
     * @param value1
     * @param value2
     */
    void put(K key, V1 value1, V2 value2);

    /**
     * 存储value1到指定的key下
     *
     * @param key
     * @param value1
     */
    void putV1(K key, V1 value1);

    /**
     * 存储vaulue2到指定的key下
     *
     * @param key
     * @param value2
     */
    void putV2(K key, V2 value2);

    /**
     * 获得指定key的value1值
     *
     * @param key
     * @return
     */
    V1 getV1(K key);

    /**
     * 获得指定key的value2值
     *
     * @param key
     * @return
     */
    V2 getV2(K key);

    /**
     * 移除指定key的value1值
     *
     * @param key
     */
    void removeV1(K key);

    /**
     * 移除指定key的value2值
     *
     * @param key
     */
    void removeV2(K key);

    /**
     * 移除指定的key对象
     *
     * @param key
     */
    void remove(K key);

}
