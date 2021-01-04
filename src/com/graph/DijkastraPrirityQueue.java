package com.graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkastraPrirityQueue {

    public Map<Vertex<Integer>, Integer> dijkastraShortestPath(Graph<Integer> graph, Vertex<Integer> source) {
        PriorityQueue<Vertex<Integer>> priorityQueue = new PriorityQueue(new PriorityComparatorNew());
        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap();
        Map<Vertex<Integer>, Integer> distance = new HashMap();
        Collection<Vertex<Integer>> graphVertex = graph.getAllVertex();

        distance.put(source, 0);
        parent.put(source, null);

        
        //initialize all vertex with infinite distance from source vertex
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(vertex.equals(source)) {
                vertex.distance = 0;
            }
            else {
                vertex.distance = Integer.MAX_VALUE;
            }
            
            priorityQueue.add(vertex);
        }
        
        while(!priorityQueue.isEmpty()) {

            Vertex<Integer> currentVertex = priorityQueue.poll();
            // Vertex<Integer> currentVertex = heapNode.key;

            distance.put(currentVertex, currentVertex.distance);

            for (Edge<Integer> edge : currentVertex.getEdges()) {

                Vertex<Integer> adjVertex = getVertexForEdge(currentVertex, edge);

                //if heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex
                if(!priorityQueue.contains(adjVertex)) {
                    continue;
                }

                int newDistance = currentVertex.distance + edge.getWeight();

                if(newDistance < adjVertex.distance) {
                    parent.put(adjVertex, currentVertex);
                    //Decrease the distane of vertex in priorityqueue
                    adjVertex.distance = newDistance;
                    priorityQueue.offer(adjVertex);

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
        graph.addEdge(1, 5, 2);
        graph.addEdge(5, 6, 3);
        graph.addEdge(6, 4, 2);
        graph.addEdge(3, 4, 3);

        DijkastraPrirityQueue dsp = new DijkastraPrirityQueue();
        Vertex<Integer> sourceVertex = graph.getVertex(1);
        Map<Vertex<Integer>,Integer> distance = dsp.dijkastraShortestPath(graph, sourceVertex);
        System.out.println(distance);
    }
}

class PriorityComparatorNew implements Comparator<Vertex<Integer>> {

    @Override
    public int compare(Vertex<Integer> o1, Vertex<Integer> o2) {
        
        return (int) (o1.distance - o2.distance);
    }
    
}

