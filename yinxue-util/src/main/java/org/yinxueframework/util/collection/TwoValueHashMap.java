package org.yinxueframework.util.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * K V1 V2 数据结构 <br>
 * @author zengjian
 * @create 2018-04-22 15:23
 * @since 1.0.0
 */
public class TwoValueHashMap<K, V1, V2> implements TwoValueMap<K, V1, V2> {

    private final Map<K, Object[]> decorator = new HashMap<>();

    @Override
    public void put(K key, V1 value1, V2 value2) {
        checkNullAndInitArray(key);
        decorator.get(key)[0] = value1;
        decorator.get(key)[1] = value2;
    }

    @Override
    public void putV1(K key, V1 value1) {
        checkNullAndInitArray(key);
        decorator.get(key)[0] = value1;
    }

    @Override
    public void putV2(K key, V2 value2) {
        checkNullAndInitArray(key);
        decorator.get(key)[1] = value2;
    }

    private void checkNullAndInitArray(K key) {
        if (key == null) {
            throw new NullPointerException("Key not allowed null");
        }
        if (decorator.get(key) == null) {
            Object[] values = new Object[2];
            decorator.put(key, values);
        }
    }

    @Override
    public V1 getV1(K key) {
        if (decorator.get(key) == null) {
            return null;
        }
        return (V1) decorator.get(key)[0];
    }

    @Override
    public V2 getV2(K key) {
        if (decorator.get(key) == null) {
            return null;
        }
        return (V2) decorator.get(key)[1];
    }

    @Override
    public void removeV1(K key) {
        decorator.get(key)[0] = null;
    }

    @Override
    public void removeV2(K key) {
        decorator.get(key)[1] = null;
    }

    @Override
    public void remove(K key) {
        decorator.remove(key);
    }
}
