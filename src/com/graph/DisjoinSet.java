package com.graph;

import java.util.HashMap;
import java.util.Map;


public class DisjoinSet {

    class Node {
        long data;
        int rank;
        Node parent;
    }

    private Map<Long, Node> map = new HashMap<>();  

    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    /**
     * Union operation - by Rank
     */
    public void union(long X, long Y) {
        Node nodeX = map.get(X);
        Node nodeY = map.get(Y);

        Node parentX = findSet(nodeX);
        Node parentY = findSet(nodeY);

        if(parentX.data == parentY.data) {
            return;
        }
        
        if(parentX.rank >= parentY.rank) {
            parentX.rank = parentX.rank == parentY.rank ? parentX.rank + 1 : parentX.rank;
            parentY.parent = parentX;
        }
        else {
            parentX.parent = parentY;
        }
            
    }

    public Node findSet(Node node) {
        Node parent = node.parent;
        if(node == parent) {
            return parent;
        }
        node.parent = findSet(parent);
        return node.parent;
    }

    public long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    public static void main(String arg[]) {
        DisjoinSet dSet = new DisjoinSet();
        dSet.makeSet(1);
        dSet.makeSet(2);
        dSet.makeSet(3);
        dSet.makeSet(4);
        dSet.makeSet(5);
        dSet.makeSet(6);
        dSet.makeSet(7);

        dSet.union(1, 2);
        dSet.union(2, 3);
        dSet.union(3, 5);
        dSet.union(6, 7);
        dSet.union(5, 6);
        dSet.union(3, 7);

        System.out.println(dSet.findSet(1));
        System.out.println(dSet.findSet(2));
        System.out.println(dSet.findSet(3));
        System.out.println(dSet.findSet(4));
        System.out.println(dSet.findSet(5));
        System.out.println(dSet.findSet(6));
        System.out.println(dSet.findSet(7));
    }

}
