package com.graph;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

    private Map<Long, Node> hashMap = new HashMap<>();

    class Node {
        long data;
        int rank;
        Node parent;
    }

    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.rank = 0;
        node.parent = node;
        hashMap.put(data, node);
    }

    public void union(long d1, long d2) {
        Node n1 = hashMap.get(d1);
        Node n2 = hashMap.get(d2);
        Node p1 = findSet(n1);
        Node p2 = findSet(n2);
        if(p1.rank >= p2.rank) {
            p2.parent = p1;
            p1.rank = p1.rank == p2.rank ? p1.rank + 1 : p1.rank;
        }
        else {
            p1.parent = p2;
        }
    }

    public Node findSet(Node node) {
        if(node.parent == node) {
            return node.parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }

    public long findSet(long data) {
        return findSet(hashMap.get(data)).data;
    }
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        System.out.println(ds.findSet(2));
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        System.out.println(ds.findSet(5));
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }
}
