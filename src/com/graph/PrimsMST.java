package com.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PrimsMST {
    public List<Edge<Integer>> getPrimsMST(Graph<Integer> graph) {
        //binary heap + map data structure
        PriorityQueueMin<Vertex<Integer>> priorityQueue = new PriorityQueueMin();
        List<Edge<Integer>> mstEdges = new ArrayList();
        Map<Vertex<Integer>, Edge<Integer>> vertexToEdge = new HashMap();

        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();

        for(Vertex<Integer> vertex : graph.getAllVertex()) {
            priorityQueue.add(Integer.MAX_VALUE,vertex);
        }

        while(!priorityQueue.empty()) {
            Vertex<Integer> currentVertex = priorityQueue.extractMin();

            //add edge to mst if *******
            Edge<Integer> spanningTreeEdge = vertexToEdge.get(currentVertex);
            if(spanningTreeEdge != null) {
                mstEdges.add(spanningTreeEdge);
            }

            for(Edge<Integer> adjEdge : currentVertex.getEdges()) {
                Vertex<Integer> adjVertex = getVertexForEdge(currentVertex, adjEdge);
                if(priorityQueue.containsData(adjVertex) && priorityQueue.getWeight(adjVertex) > adjEdge.getWeight()) {
                    priorityQueue.decrease(adjVertex, adjEdge.getWeight());
                    vertexToEdge.put(adjVertex, adjEdge);
                }
            }
        }

        return mstEdges;

    }

    private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e){
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }

    public static void main(String args[]){
        Graph<Integer> graph = new Graph<>(false);
     /* graph.addEdge(0, 1, 4);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(2, 5, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(0, 7, 8);
        graph.addEdge(2, 8, 2);
        graph.addEdge(3, 5, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 7);*/

        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);

        PrimsMST prims = new PrimsMST();
        Collection<Edge<Integer>> edges = prims.getPrimsMST(graph);
        for(Edge<Integer> edge : edges){
            System.out.println(edge.getVertex1().id + " - " +  edge.getVertex2().id);
        }
    }
}
