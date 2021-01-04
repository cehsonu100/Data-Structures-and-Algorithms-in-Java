package com.graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Dijkastra {

    public Map<Vertex<Integer>, Integer> dijkastraShortestPath(Graph<Integer> graph, Vertex<Integer> source) {
        PriorityQueueMin<Vertex<Integer>> priorityQueue = new PriorityQueueMin();
        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap();
        Map<Vertex<Integer>, Integer> distance = new HashMap();
        Collection<Vertex<Integer>> graphVertex = graph.getAllVertex();

        distance.put(source, 0);
        parent.put(source, null);

        //initialize all vertex with infinite distance from source vertex
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            priorityQueue.add(Integer.MAX_VALUE, vertex);
        }
        
        while(!priorityQueue.empty()) {

            PriorityQueueMin<Vertex<Integer>>.Node heapNode = priorityQueue.extractMinNode();
            Vertex<Integer> currentVertex = heapNode.key;

            distance.put(currentVertex, heapNode.weight);

            for (Edge<Integer> edge : currentVertex.getEdges()) {

                Vertex<Integer> adjVertex = getVertexForEdge(currentVertex, edge);

                //if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if(!priorityQueue.containsData(adjVertex)){
                    continue;
                }

                int newDistance = distance.get(currentVertex) + edge.getWeight();

                if(newDistance < priorityQueue.getWeight(adjVertex)) {
                    parent.put(adjVertex, currentVertex);
                    //Decrease the distane of vertex in priorityqueue
                    priorityQueue.decrease(adjVertex, newDistance);

                }
            }
        }

        return distance;

    } 

    private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e){
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }

    public static void main(String[] args) 
    {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(1, 4, 9);
        graph.addEdge(1, 5, 3);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 4, 2);
        graph.addEdge(3, 4, 3);

        Dijkastra dsp = new Dijkastra();
        Vertex<Integer> sourceVertex = graph.getVertex(1);
        Map<Vertex<Integer>,Integer> distance = dsp.dijkastraShortestPath(graph, sourceVertex);
        System.out.println(distance);
    }
}

class PriorityComparator implements Comparator<Vertex<Integer>> {

    @Override
    public int compare(Vertex<Integer> o1, Vertex<Integer> o2) {
        
        return (int) (o1.distance - o2.distance);
    }
    
}
