package com.company;

import java.util.Comparator;
import java.util.List;

public class Best {

    private Node initialNode;

    public Best(Node node) {
        this.initialNode = node;
    }

    private class hComparator implements Comparator<Node> {



        public int compare(Node a, Node b) {
            return numCorPos(a) - numCorPos(b);

        }

    }

    public int numCorPos(Node node) {
        int [][] goal = {{1,2,3},{8,0,4},{7,6,5}};
        int result = 0;
        int [][] state = node.getMatrix();
        for(int i=0; i<state.length; i++) {
            for(int j=0; j<state.length; j++) {
                if(goal[i][j]!=state[i][j]) {
                    result += 1;
                }
            }
        }
        return result;
    }
    public boolean search() {

        //BestFirst search which creates a priority queue which sorts according to h(n)
        NodeLocation info = new NodeLocation();
        info.makePQueue(new hComparator()); //making a priority queue with hComparator
        Node node = initialNode;
        info.pQueue.add(node);

        while(!(info.pQueue.isEmpty())) {
            node = info.pQueue.poll();
            info.incTime();
            info.visited.put(node.hashCode(), node);
            if(node.isGaol()) {
                PathActions p = new PathActions(initialNode,node); // class that creates a path from goal to start Node if goal is reached.
                p.printPath(); // the path is then printed
                return true;
            }

            Successor s = new Successor(); // Successor class created to provide next possible moves from current node
            List<Node> list = s.successor(node); // list of potential children

            for(Node temp: list) {
                boolean ans = info.visited.containsKey(temp.hashCode()); //Uses temporary node's hashCode to check if it has been expanded or not.
                if(ans==false) { //if it hasn't been expanded then we can now check if there is a node in the Priority Queue with a higher Cost
                    if(!(info.pQueue.contains(temp))){
                        info.pQueue.add(temp);
                        info.pQueueSize();
                    }
                }
            }
        }
        return false;
    }



}

