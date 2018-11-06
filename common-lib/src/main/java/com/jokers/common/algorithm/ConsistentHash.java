package com.jokers.common.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * ConsistentHash
 * <p>一致性hash算法</p>
 * Created by yuTong on 20181106.
 */
public class ConsistentHash<T> {
    private final HashFunction hashFunction;
    private final int numberOfReplicas;// 节点的复制因子,实际节点个数 * numberOfReplicas = 虚拟节点个数
    private final SortedMap<Long, T> circle = new TreeMap<Long, T>();// 存储虚拟节点的hash值到真实节点的映射

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * @param node 对于一个实际机器节点 node, 对应 numberOfReplicas 个虚拟节点
     *             不同的虚拟节点(i不同)有不同的hash值,但都对应同一个实际机器node
     *             虚拟node一般是均衡分布在环上的,数据存储在顺时针方向的虚拟node上
     */
    public void add(T node) {
        try {
            for (int i = 0; i < numberOfReplicas; i++) {
                circle.put(hashFunction.hash(node.toString() + i), node);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
    }


    /**
     * 删除节点
     *
     * @param node T
     */
    public void remove(T node) {
        try {
            for (int i = 0; i < numberOfReplicas; i++) {
                circle.remove(hashFunction.hash(node.toString() + i));
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
    }

    /**
     * 获得一个最近的顺时针节点,根据给定的key 取Hash
     * 然后再取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * 再从实际节点中取得 数据
     *
     * @param key 节点K
     * @return 节点值
     */
    public T get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        try {
            return circle.get(hashFunction.hash(key));
        } catch (NoSuchAlgorithmException ignored) {
        }
        return null;
    }

    public long getSize() {
        return circle.size();
    }

    /**
     * 查看MD5算法生成的hashCode值---表示整个哈希环中各个虚拟节点位置
     */
    private void testBalance() {
        SortedSet<Long> sortedSet = new TreeSet<Long>(circle.keySet());
        for (Long hashCode : sortedSet) {
            System.out.println(hashCode);
        }
        System.out.println("----each location 's distance are follows: ----");
        /*
         * 查看用MD5算法生成的long hashCode 相邻两个hashCode的差值
         */
        Iterator<Long> it = sortedSet.iterator();
        Iterator<Long> it2 = sortedSet.iterator();
        if (it2.hasNext()) it2.next();
        long keyPre, keyAfter;
        while (it.hasNext() && it2.hasNext()) {
            keyPre = it.next();
            keyAfter = it2.next();
            System.out.println(keyAfter - keyPre);
        }
    }

    /**
     * 实现一致性哈希算法中使用的哈希函数,使用MD5算法来保证一致性哈希的平衡性
     */
    public static class HashFunction {
        private MessageDigest md5 = null;

        public long hash(String key) throws NoSuchAlgorithmException {
            if (null == md5) {
                md5 = MessageDigest.getInstance("MD5");
            }
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            long result = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16
                    | ((long) (bKey[1] & 0xFF) << 8)
                    | (long) (bKey[0] & 0xFF));
            return result & 0xffffffffL;
        }
    }

    public static void main(String[] args) {
        Set<String> nodes = new HashSet<String>() {{
            add("A");
            add("B");
            add("C");
        }};
        ConsistentHash<String> consistentHash = new ConsistentHash<String>(new HashFunction(), 2, nodes);
        consistentHash.add("D");
        System.out.println("hash circle size: " + consistentHash.getSize());
        System.out.println("location of each node are follows: ");
        consistentHash.testBalance();
    }
}
