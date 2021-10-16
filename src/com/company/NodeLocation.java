package com.company;

import java.util.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class NodeLocation {
    public Queue<Node> queue; // this class has datastructures such as queue, stack, and priority Queue in order to keep track of time, space and which nodes are in the queue
    public Stack<Node> stack;
    public PriorityQueue<Node> pQueue;
    public int time;
    private int maxQueueSize;
    public HashMap<Integer,Node> visited;


    public NodeLocation() {
        queue = new LinkedList<Node>();
        stack = new Stack<Node>();
        pQueue = new PriorityQueue<Node>();
        time = 0;
        maxQueueSize = 0;
        visited = new HashMap<Integer,Node>();



    }

    public void  makePQueue(Comparator c) {   //creates a prioirty queue with a comparator as an argument to decidee the order in which the queue will organize elements
        pQueue = new PriorityQueue<Node>(c);
    }

    public void incTime() {  //timer method that begins timer
        time += 1;
    }

    public void queueSize() {   //queue size that constantly checks for maximum size, this will tell us the space
        if(queue.size()>maxQueueSize) {
            maxQueueSize = queue.size();
        }
    }

    public void stackSize() {   //behaves similar to queueSize() but for stack
        if(stack.size()>maxQueueSize) {
            maxQueueSize = stack.size();
        }
    }

    public void pQueueSize() {  //behaves similar to queueSize() but for priority queue
        if(pQueue.size()>maxQueueSize) {
            maxQueueSize = pQueue.size();
        }
    }

    public int getTime() { //time is returned
        return time;
    }

    public int getSpace() {  //space is returned
        return maxQueueSize;
    }

}



