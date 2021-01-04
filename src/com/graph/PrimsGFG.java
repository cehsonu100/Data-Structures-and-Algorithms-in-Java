package com.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrimsGFG {
    public static void main(String args[]) throws IOException {

        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            HashMap<Integer, ArrayList<Integer>> graph = new HashMap();
            str = read.readLine().trim().split(" ");
            int k = 0;
            int i=0;
            while (i++<E) {
                int u = Integer.parseInt(str[k++]);
                int v = Integer.parseInt(str[k++]);
                int w = Integer.parseInt(str[k++]);
                ArrayList<Integer> adjVertexListForU = new ArrayList<Integer>();
                ArrayList<Integer> adjVertexListForV = new ArrayList<Integer>();
                if(graph.containsKey(u)) {
                    adjVertexListForU = graph.get(u);
                }
                if(graph.containsKey(v)) {
                    adjVertexListForV = graph.get(v);
                }
                adjVertexListForU.add(v);adjVertexListForU.add(w);
                adjVertexListForV.add(u);adjVertexListForV.add(w);
                graph.put(u, adjVertexListForU);
                graph.put(v, adjVertexListForV);
            }

            System.out.println(spanningTree(V, E, graph));
            System.out.println(graph);
            
        }
    }

    private static int spanningTree(int V, int E, HashMap<Integer, ArrayList<Integer>> graph) {
        PriorityQueue<VertexForSpanning> pq = new PriorityQueue<>(new PrimsGFGComparator());
        HashMap<Integer, Integer> distance = new HashMap<>();
        int firstKey = graph.keySet().iterator().next(); 
        distance.put(firstKey, 0);
        pq.add(new VertexForSpanning(firstKey, 0));
        for (Map.Entry<Integer, ArrayList<Integer>> hashMap : graph.entrySet()) {
            if(hashMap.getKey() == firstKey) 
                continue;
            distance.put(hashMap.getKey(), Integer.MAX_VALUE);
            pq.add(new VertexForSpanning(hashMap.getKey(), Integer.MAX_VALUE));
        }

        int totalMinWeight = 0;
        while(!pq.isEmpty()) {
            VertexForSpanning currentVertex = pq.poll();
            totalMinWeight = totalMinWeight + currentVertex.distance;
            ArrayList<Integer> adjEdge = graph.get(currentVertex.vertex);
            for(int i = 0; i < adjEdge.size(); i = i+2) {
                int adjVertex = adjEdge.get(i);
                int adjEdgeWeight = adjEdge.get(i+1);
                if(distance.get(adjVertex) > adjEdgeWeight) {
                    distance.put(adjVertex, adjEdgeWeight);
                    pq.offer(new VertexForSpanning(adjVertex, adjEdgeWeight));
                }
            }
        }
        return totalMinWeight;
    }

    private static VertexForSpanning getMin(HashMap<Integer, Integer> vertexWithDistance) {
        VertexForSpanning min = new VertexForSpanning();
        Map.Entry<Integer,Integer> entry = vertexWithDistance.entrySet().iterator().next();
        min.vertex = entry.getKey();
        min.distance = entry.getValue();
        
        for (Map.Entry<Integer, Integer> hashMap : vertexWithDistance.entrySet()) {
            if(min.distance > hashMap.getValue()) {
                min.vertex = hashMap.getKey();
                min.distance = hashMap.getValue();
            }
        }
        return min;
    }

     
}

class PrimsGFGComparator implements Comparator<VertexForSpanning> {

    @Override
    public int compare(VertexForSpanning o1, VertexForSpanning o2) {
        
        return o1.distance - o2.distance;
    }
    
}

class VertexForSpanning {
    int vertex;
    int distance;
    public VertexForSpanning(int v, int d) {
        this.vertex = v;
        this.distance = d;
    }
    public VertexForSpanning() {

    }
}



