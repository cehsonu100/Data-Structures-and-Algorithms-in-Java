package com.graph;

public class Main {
    public static <T> void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(6, 5);
        System.out.println(graph.getAllEdges());
    }
}
