package com.cyw.常规算法题.哈希;

import java.util.HashMap;

/**
 * @author chenyuwei
 * @create 2020-08-15-16:57
 * 设计一个RandomPool结构，在该结构中有如下三个功能：
 * insert(key)：将某个key加入到该结构中，做到不重复加入
 * delete(key)：将某个key从该结构中删除
 * getRandom：等概率随机返回一个key
 */
public class RandomPool {
    public static class Pool<k>{
        private HashMap<k,Integer> map1;
        private HashMap<Integer,k> map2;
        private int size;

        public Pool(){
            this.map1 = new HashMap<>();
            this.map2 = new HashMap<>();
            this.size = 0;
        }

        public void insert(k key){
            if (!this.map1.containsKey(key)){
                this.map1.put(key,this.size);
                this.map2.put(this.size,key);
                this.size++;
            }
        }

        /**
         * 删除某个key
         * @param key
         * 需要注意的是，在map2中不能留下删除之后的“洞”，因此用最后一个元素来填这个洞，
         * 然后删除最后一个元素。
         */
        public void delete(k key){
            if (this.map1.containsKey(key)){
                int deleteIndex = this.map1.get(key);
                int lastIndex = --this.size;
                k lastKey = this.map2.get(lastIndex);
                this.map1.put(lastKey,deleteIndex);
                this.map2.put(deleteIndex,lastKey);
                this.map1.remove(key);
                this.map2.remove(lastIndex);
            }
        }
        public k getRandom(){
            if (this.size==0){
                return null;
            }
            int randomIndex = (int) (Math.random()*this.size);
            return this.map2.get(randomIndex);
        }

        public static void main(String[] args) {
            Pool<String> pool = new Pool<String>();
            pool.insert("zuo");
            pool.insert("cheng");
            pool.insert("yun");
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
        }
    }
}
