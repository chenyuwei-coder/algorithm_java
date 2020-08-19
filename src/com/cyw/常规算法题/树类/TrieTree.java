package com.cyw.常规算法题.树类;

/**
 * @author chenyuwei
 * @create 2020-08-18-17:05
 * 前缀树
 */
public class TrieTree {
    /**
     * 前缀树的节点结构
     */
    public static class TrieNode {
        public int path;//记录经过节点的次数
        public int end;//记录有多少字符串以该节点结尾
        public TrieNode nexts[];

        public TrieNode() {
            this.path = 0;
            this.end = 0;
            this.nexts = new TrieNode[26];
        }
    }

    /**
     * 前缀树的结构
     */
    public static class trie {
        private TrieNode root;

        public trie() {
            root = new TrieNode();
        }

        /**
         * 向前缀树中插入字符串
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null)
                return;
            char[] chars = word.toCharArray();
            int index = 0;
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.path++;
            }
            node.end++;
        }

        /**
         * 查询一个字符串出现了几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null)
                return 0;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        /**
         * 删除一个字符串
         *
         * @param word
         */
        public void delete(String word) {
            if (search(word) == 0)
                return;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (--node.nexts[index].path == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }

        /**
         * 查找以给定字符串作为前缀的字符串有多少
         *
         * @param word
         * @return
         */
        public int prefixNum(String word) {
            if (word == null)
                return 0;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }
    }
}
