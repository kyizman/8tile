package com.company;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import  com.company.UniformCost;


public class Main {


    public static void main(String[] args) {
        // write your code here

        int[][] easy = {{1, 3, 4}, {8, 6, 2}, {7, 0, 5}};
        UniformCost u = new UniformCost();

solve(easy);







    }

    private static boolean solve(int[][] x) {


            HashMap<Integer, Node> vist = new HashMap<>();
            Node startnode = new Node(x);

            Node nodetoworkwith = startnode;
            int[][] goal = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
            PriorityQueue<Node> prio = new PriorityQueue<Node>(new maxcostComparator());

            prio.add(nodetoworkwith);

            while (!prio.isEmpty()) {
                nodetoworkwith = prio.poll();
                vist.put(nodetoworkwith.hashCode(), nodetoworkwith);

                if (nodetoworkwith.isGaol()) {
                    PathActions p = new PathActions(startnode, nodetoworkwith); // class that creates a path from goal to start Node if goal is reached.
                    p.printPath(); // the path is then printed

                }
                Successor s = new Successor(); // Successor class created to provide next possible moves from current node
                List<Node > list =  s.successor(nodetoworkwith); // list of potential children

                for(Node  temp: list) {
                    boolean ans = vist.containsKey(temp.hashCode()); //Uses temporary node's hashCode to check if it has been expanded or not.
                    if(ans==false) { //if it hasn't been expanded then we can now check if there is a node in the Priority Queue with a higher Cost
                        if(!(prio.contains(temp))){
                            prio.add(temp);

                        }
                    }
                }
            }
            return true;
        }


    }

    class maxcostComparator implements Comparator<Node> {

        public int compare(Node a, Node b) {
            return a.getMaxCost() - b.getMaxCost();
        }




}



