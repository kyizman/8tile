package com.company;

import java.util.ArrayList;
import java.util.List;

public class Node {


    private  int[][]  state = new int [3][3];
    private List<Node> children;
    private Node parent;
    private int depth;
    private int blankrow;
    private int blankcol;
    private Directions direction;
    private String stringState;
    private int cost;
    private int maxCost;

    public Node(int [][] state) {
        this.state = state; // the state
        this.depth = 1; // the depth
        this.children = new ArrayList<Node>(); //the children of the node
        this.parent = null;
        this.cost = 0;
        this.maxCost = 0;
        this.stringState = stringBoard();
        this.direction = null;
        for(int i=0; i<=2; i++) {
            for(int j=0; j<=2; j++) {
                if(state[i][j]==0) {
                    this.blankrow = i;
                    this.blankcol = j;
                    break;
                }
            }
        }

    }

    public String stringBoard() {   //method that returns a String version of the baord
        StringBuilder sb = new StringBuilder();
        for (int i =0; i<state.length; i++) {
            for(int j = 0; j<state[i].length;j++ ) {
                sb.append(state[i][j]);
            }
        }
        return sb.toString();
    }

    public void addChild(Node child) { //adding a Child to the node
        child.setParent(this);
        child.setDepth(this.getDepth()+1);
        child.setMaxCost(child.getCost());
        this.children.add(child);

    }

    public void setParent(Node parent) { //setting the Parent of the node
        this.parent = parent;
    }

    public void setDepth(int depth) {  //setting the Depth of the node
        this.depth = depth;
    }
    public int getDepth() {  //getting the Depth of the node
        return depth;
    }

    public Node getParent() {  //getting the Parent of the node
        return parent;
    }

    public int getRowBlank() {  //getting the Row of the zero tile
        return blankrow;
    }

    public int getColBlank() { //getting the Column of the zero tile
        return blankcol;
    }

    public int [][] getMatrix(){ //getting the state in array form
        return state;
    }

    public int getCost() { //getting the cost of last move
        return this.cost;
    }


    public List<Node> getChildren(){ //getting the children
        return children;
    }
    public void setChildren(List<Node> childrens) { //setting the children
        this.children =  childrens;
    }

    public Node createChild(int a, int b) {      //creating the child or possible states from current node
        int temp[][] = new int[state.length][state.length];
        for(int i=0; i<state.length; i++)
            for(int j=0; j<state[i].length; j++)
                temp[i][j]=state[i][j];
        temp[blankrow][blankcol] = temp[a][b];
        int cost = state[a][b];
        temp[a][b] = 0;
        Node child = new Node(temp);
        child.setCost(cost);							//adding to Child to parent
        addChild(child);
        return child;
    }

    public void setDir(Directions d) {				//setting the Direction moved
        this.direction = d;
    }
    public Directions getDir() {				//getting the direction moved
        return direction;
    }

    public boolean isGaol() {				//checking if node is goal node
        boolean result = false;
        int [][] goal = {{1,2,3},{8,6,4},{7,0,5}};
        Node goalNode = new Node(goal);
        result = this.equals(goalNode);
        return result;
    }

    @Override
    public boolean equals(Object object ) {    //equals for HashMap

        if(!(object instanceof Node)) {
            return false;
        }
        Node check = (Node) object;

        return check.getString().equals(this.getString());
    }

    @Override
    public int hashCode() {			//Hashcode generated from String version of board
        int result = 17;
        result = 37 * result + this.getString().hashCode();
        return result;
    }

    public String getString() {			//getting String version of Board
        return stringState;
    }

    public void setCost(int i) {					//setting cost
        this.cost = i;
    }
    public void setMaxCost(int i) {
        this.maxCost = this.getParent().getMaxCost() + i;			//setting MaxCost
    }

    public int getMaxCost() { //getting the current MaxCode to get to current Node
        return maxCost;
    }

    public int getRow(int value) {				//getting the Row of a value in goalState
        int row = 0;
        int [][] goal = {{1,2,3},{8,0,4},{7,6,5}};
        for(int i=0; i<=2; i++) {
            for(int j=0; j<=2; j++) {
                if(goal[i][j]==value) {
                    row = i;
                }
            }
        }
        return row;
    }

    public int getCol(int value) {			//getting the Column of value in goal state used for Manhattan computation
        int col = 0;
        int [][] goal = {{1,2,3},{8,0,4},{7,6,5}};
        for(int i=0; i<=2; i++) {
            for(int j=0; j<=2; j++) {
                if(goal[i][j]==value) {
                    col = j;
                }
            }
        }
        return col;
    }

    public Node duplicateNode() {  //create duplicate Node
        Node dup = new Node(this.getMatrix());
        dup.setParent(this.getParent());
        dup.setDir(this.getDir());
        dup.setCost(this.getCost());
        dup.setDepth(this.getDepth());
        dup.setMaxCost(this.getCost());
        dup.setChildren(this.getChildren());
        return dup;


    }


}
