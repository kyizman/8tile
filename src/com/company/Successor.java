package com.company;

import java.util.ArrayList;
import java.util.List;

public class Successor {

    public Successor() {

    }

    public List<Node> successor(Node node) {
        //successor function that takes a state and returns a list of possible states that can be reached

        List<Node> list = new ArrayList<Node>();

        int row = node.getRowBlank();
        int col = node.getColBlank();

        //up

        if((col !=0 || col != 1 || col != 2) && (row !=0) ) {  //uses information about the nature of 2d arrays to dictate the zero-tile's movemnt.
            Node upNode = node.createChild(row-1, col);
            upNode.setDir(Directions.UP);
            list.add(upNode);

        }

        //down

        if((col !=0 || col != 1 || col != 2) && (row !=2) ) {
            Node downNode = node.createChild(row+1, col);
            downNode.setDir(Directions.DOWN);
            list.add(downNode);
        }

        //right

        if((row != 0 || row != 1 || row != 2) && (col !=2)) {
            Node rightNode = node.createChild(row, col+1);
            rightNode.setDir(Directions.RIGHT);
            list.add(rightNode);
        }

        //left
        if((row != 0 || row != 1 || row != 2) && (col !=0)) {
            Node leftNode = node.createChild(row, col-1); // a child is created if the zero tile can move left.
            leftNode.setDir(Directions.LEFT);
            list.add(leftNode);
        }

        return list;  // a list of children is returned.

    }
}
