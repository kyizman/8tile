package com.company;

import java.util.Comparator;
import java.util.List;

public class AStar {



    public int manhattan(Node node) {   //second heuristic which uses a goal state to help determined how far argument node tiles are from desired position
        int [][]goal = {{1,2,3},{8,0,4},{7,6,5}};
        int result = 0;
        int [][]state = node.getMatrix();
        for(int i=0; i<state.length; i++) {
            for(int j=0; j<state.length; j++) {
                int value = state[i][j];
                result += Math.abs(i - node.getRow(value)) + Math.abs(j - node.getCol(value));
            }
        }
        return result;
    }


    public int comparieson(Node x, Node y)
    {
        return (x.getMaxCost() + manhattan(x)) - (y.getMaxCost()+manhattan(x));
    }


    //Astar class that creates the Astar search
    private Node initialNode;
    private int i;

    public AStar(Node node, int i) {
        this.initialNode = node;
        this.i = i; // this int value helps determine which heuristic will be used
    }




    private class f2Comparator implements Comparator<Node>{			//comparator for manhattan heuristic and totalCost



        public int compare(Node a, Node b) {
            return (a.getMaxCost() +manhattan(a)) - (b.getMaxCost()+manhattan(b));
        }
    }

    public boolean search() {

        //Astar search which creates a priority queue which sorts according to h(n)
        NodeLocation info = new NodeLocation();

            info.makePQueue(new f2Comparator());

        //making a priority queue with one of the heuristics determine the Comparator
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
