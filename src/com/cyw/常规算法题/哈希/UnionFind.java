package com.cyw.常规算法题.哈希;

import java.util.HashMap;
import java.util.List;

/**
 * @author chenyuwei
 * @create 2020-08-17-13:57
 * 并查集
 */
public class UnionFind {
    //定义节点的结构
    public static class Node{
        //whatever you want
    }
    //定义并查集的结构和功能
    public static class UnionFindSet{
        public HashMap<Node,Node> fatherMap;
        public HashMap<Node,Integer> sizeMap;

        public UnionFindSet(List<Node> nodes){
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            makeSet(nodes);
        }
        //初始时，每个节点的父节点是其本身，且该节点所在的集合的节点数量也只有一个，就是其本身
        private void makeSet(List<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node:nodes){
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }
        //找到一个链表集合的代表节点，即父节点是其本身的节点
        private  Node findHead(Node node){
            Node father = fatherMap.get(node);
            if (father!=node){
                father = findHead(father);
            }
            fatherMap.put(node,father);
            return father;
        }
        //两个节点所在的集合是否是同一个集合
        public boolean isSameSet(Node a,Node b){
            return findHead(a)==findHead(b);
        }
        //将两个集合进行合并
        public void union(Node a,Node b){
            if (a==null || b==null){
                return;
            }
            Node aSetHead = findHead(a);
            Node bSetHead = findHead(b);
            if (aSetHead!=bSetHead){
                int aSetSize = sizeMap.get(aSetHead);
                int bSetSize = sizeMap.get(bSetHead);
                if (aSetSize<=bSetSize){
                    fatherMap.put(aSetHead,bSetHead);
                    sizeMap.put(bSetHead,aSetSize+bSetSize);
                }else{
                    fatherMap.put(bSetHead,aSetHead);
                    sizeMap.put(aSetHead,aSetSize+bSetSize);
                }
            }
        }
    }
}
