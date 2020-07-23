package com.cyw.常规算法题.LRU算法;

import java.util.HashMap;

/**
 * @author chenyuwei
 * @create 2020-07-22-15:20
 */
public class LRUCache {
    class Node {
        public Node pre;
        public Node next;
        public String key;
        public String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node end;
    private int limit; // 缓存上限
    private HashMap<String, Node> hashMap;

    LRUCache(int limit) {
        this.limit = limit;
        this.hashMap = new HashMap<>();
    }

    /**
     * 获取hashMap节点的值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    /**
     * 向缓存中添加或更新节点信息
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            //如果节点不存在，则插入该节点
            if (hashMap.size() > limit) {
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            //如果节点存在，则刷新节点
            node.value = value;
            refreshNode(node);
        }
    }

    /**
     * 删除哈希链表中的节点
     *
     * @param key
     */
    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(node);
    }

    /**
     * 刷新节点
     *
     * @param node 待刷新的节点
     */
    private void refreshNode(Node node) {
        //如果节点已经处于最后，则不需要操作
        if (node == end) {
            return;
        }
        //从哈希链中删除该节点
        removeNode(node);
        //将该节点插入到链尾
        addNode(node);
    }

    /**
     * 在尾部插入节点
     *
     * @param node 要插入的节点
     */
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null)
            head = node;
    }

    /**
     * 删除哈希链表中的指定节点
     *
     * @param node 需要删除的节点
     * @return 返回删除节点的key
     */
    private String removeNode(Node node) {
        if (node == head && node == end) {
            head = null;
            end = null;
        } else if (node == head) {
            head = head.next;
            head.pre = null;
        } else if (node == end) {
            end = end.pre;
            end.next = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001", " 用户1信息");
        lruCache.put("002", " 用户1信息");
        lruCache.put("003", " 用户1信息");
        lruCache.put("004", " 用户1信息");
        lruCache.put("005", " 用户1信息");
        lruCache.get("002");
        lruCache.put("004", " 用户2信息更新");
        lruCache.put("006", " 用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
