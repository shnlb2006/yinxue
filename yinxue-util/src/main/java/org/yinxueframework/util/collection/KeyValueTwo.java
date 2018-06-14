package org.yinxueframework.util.collection;

/**
 * 一种可以通过key来取得第二位或者第三位内容的数据结构，并且key不能重复<br>
 *     采用TreeMap方式来实现 <br>
 *
 * @author zengjian
 * @create 2018-03-15 14:29
 * @since 1.0.0
 */
public class KeyValueTwo<K, V1, V2> implements TwoValueMap<K,V1,V2>{

    private Entry<K, V1, V2>[] entrys;
    private int size = 0;

    public static class Entry<K, V1, V2> {
        private K key;
        private V1 value1;
        private V2 value2;
        private int index;

        public Entry(K key, V1 value1, V2 value2, int index) {
            this.key = key;
            this.value1 = value1;
            this.value2 = value2;
            this.index = index;
        }

        public V1 getValue1() {
            return value1;
        }

        public V2 getValue2() {
            return value2;
        }
    }

    public void put(K key, V1 value1, V2 value2) {
        if (size == 0) {
            entrys = new Entry[32];
            entrys[0] = new Entry<>(key, value1, value2, 0);
            size++;
        } else {
            for (int i = 0; i < entrys.length; i++) {
                // 不为空，并且key相等，则更新v1 v2字段
                if (entrys[i] != null && entrys[i].key.equals(key)) {
                    // 存在key size不变
                    entrys[i].value1 = value1;
                    entrys[i].value2 = value2;
                    break;
                } else if (entrys[i] != null && !entrys[i].key.equals(key)) {
                    if (i == entrys.length - 1) {
                        // 2倍扩容
                        Entry[] newEntrys = new Entry[(int) (2 * (entrys.length))];
                        System.arraycopy(entrys, 0, newEntrys, 0, entrys.length);
                        newEntrys[entrys.length] = new Entry<>(key, value1, value2, entrys.length);
                        entrys = newEntrys;
                        size++;
                        break;
                    }
                    continue;
                } else {
                    entrys[i] = new Entry<>(key, value1, value2, i);
                    size++;
                    break;
                }
            }
        }
    }

    @Override
    public void putV1(K key, V1 value1) {

    }

    @Override
    public void putV2(K key, V2 value2) {

    }

    public V1 getV1(K key) {
        for (int i = 0; i < size; i++) {
            if (entrys[i].key.equals(key)) {
                return entrys[i].value1;
            }
        }
        return null;
    }

    public V2 getV2(K key) {
        for (int i = 0; i < size; i++) {
            if (entrys[i].key.equals(key)) {
                return entrys[i].value2;
            }
        }
        return null;
    }

    @Override
    public void removeV1(K key) {

    }

    @Override
    public void removeV2(K key) {

    }

    public int size() {
        return size;
    }

    public Entry get(int index) {
        return entrys[index];
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < size(); i++) {
            if (key.equals(entrys[i].key)) {
                return true;
            }
        }
        return false;
    }

    public void remove(K key) {
        for (int i = 0; i < size(); i++) {
            if (key.equals(entrys[i])) {
                // 将数组往前拷贝一位，并将最后一位元素置null
                Entry oldEntry = entrys[i];
                System.arraycopy(entrys, i + 1, entrys, i, entrys.length - 1 - i);
                entrys[entrys.length - 1] = null;
                size--;
                // return oldEntry;
            }
        }
        // return null;
    }
}
