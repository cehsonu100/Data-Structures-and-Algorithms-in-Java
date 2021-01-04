package com.graph.GraphLibrary;

import java.util.HashMap;
import java.util.*;

public class AdjGraph {
    public Map<Integer, ArrayList<Integer>> adjGraphRepresentation(int[][] edges, int n, boolean isDirected) {
        Map<Integer, ArrayList<Integer>> adjGraph = new HashMap<>();
        for(int i = 0; i < n; i++) {
            adjGraph.put(i, new ArrayList<Integer>());
        }
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            ArrayList<Integer> adjVertices = adjGraph.getOrDefault(u, new ArrayList<Integer>());
            adjVertices.add(v);
            adjGraph.put(u, adjVertices);

            //If graph is undirected then also add edge pointing from v to u
            if(!isDirected) {
                adjVertices = adjGraph.getOrDefault(v, new ArrayList<Integer>());
                adjVertices.add(u);
                adjGraph.put(v, adjVertices);
            }
            
        }
        return(adjGraph);
    }

    public static void main(String[] args) {
        AdjGraph adjGraph = new AdjGraph();
        int[][] edges = {{1, 2}, {3, 4}, {3, 0}};
        Map<Integer, ArrayList<Integer>> xyz= adjGraph.adjGraphRepresentation(edges, 4, false);
    }
}
