package com.batorus.apisforalgorithms.sensorgrid.model;

import org.springframework.stereotype.Component;

@Component
public class Node {
    // `(x, y)` represents a position inside the field
    // `dist` represents its minimum distance from the source
   private int x, y, dist;

   private Node parent;

    public Node() {
    }

    public Node(int x, int y, int dist, Node parent) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", dist=" + dist +
                '}';
    }
}
