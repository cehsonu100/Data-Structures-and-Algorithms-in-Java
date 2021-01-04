package com.graph;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord {
    private static int INFINITY = 99999999;

    class NegativeWeightCycleException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
    
    public Map<Vertex<Integer>, Integer> getShortestPath(Graph<Integer> graph, Vertex<Integer> sourceVertex) {
        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();
        Map<Vertex<Integer>, Integer> distance = new HashMap<>();

        //Set all vertex distance and parent to infinity and null respectively
        for(Vertex<Integer> vertex: graph.getAllVertex()) {
            distance.put(vertex, INFINITY);
            parent.put(vertex, null);
        }

        distance.put(sourceVertex, 0);

        int V = graph.getAllVertex().size();

        //relax the edge for V-1 times, if new distance found less than current assigned one
        for(int i = 0; i < V-1; i++) {
            for (Edge<Integer> edge : graph.getAllEdges()) {
                Vertex<Integer> v1 = edge.getVertex1();
                Vertex<Integer> v2 = edge.getVertex2();
                int v1Distance = distance.get(v1);
                int v2Distance = distance.get(v2);
                if(v2Distance > v1Distance + edge.getWeight()) {
                    distance.put(v2, v1Distance + edge.getWeight());
                    parent.put(v2, v1);
                }
            }
        }

        /**
         * relax one more time
         * if still distance value get changed, that means there is negative cycle
         */
        for (Edge<Integer> edge : graph.getAllEdges()) {
            Vertex<Integer> v1 = edge.getVertex1();
            Vertex<Integer> v2 = edge.getVertex2();
            int v1Distance = distance.get(v1);
            int v2Distance = distance.get(v2);
            if(v2Distance > v1Distance + edge.getWeight()) {
                throw new NegativeWeightCycleException();
            }
        }

        return distance;
    }

    public static void main(String args[]){
        
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(0, 3, 8);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 3, 1);

        BellmanFord shortestPath = new BellmanFord();
        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();
        for (Vertex<Integer> sourceVertex : graph.getAllVertex()) {
            Map<Vertex<Integer>,Integer> distance = shortestPath.getShortestPath(graph, sourceVertex);
            System.out.println("SourceVertex = " + sourceVertex.getId());
            System.out.println(distance);
        }
        
    }
}
