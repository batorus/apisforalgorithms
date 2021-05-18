package com.batorus.apisforalgorithms.sensorgrid.service;

import com.batorus.apisforalgorithms.sensorgrid.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


@Service
public class SensorsService {

    // Below arrays detail all four possible movements from a cell,
    // i.e., (top, right, bottom, left)
    private static final int[] row = {-1, 0, 0, 1};
    private static final int[] col = {0, -1, 1, 0};

    private static List<Node> path = new ArrayList<>();

    @Autowired
    Node node;


//    public Sensors(Node node) {
//        this.node = node;
//    }

    // Function to check if it is safe to go to position `(x, y)`
    // from the current position. The function returns false if `(x, y)`
    // is unsafe or already visited.
    private static boolean isSafe(Integer[][] field, boolean[][] visited, int x, int y) {
        return (field[x][y] == 1 && !visited[x][y]);
    }

    // Check if `(x, y)` is valid field coordinates.
    // Note that we cannot go out of the field.
    private static boolean isValid(int x, int y, int M, int N) {
        return (x < M && y < N && x >= 0 && y >= 0);
    }

    // Find the minimum number of steps required to reach the last column
    // from the first column using BFS
    private Node BFS(Integer[][] field) {

        //rows
        int M = field.length;

        //cols
        int N = field[0].length;

        // stores if a cell is visited or not
        boolean[][] visited = new boolean[M][N];

        // create an empty queue
        Queue<Node> q = new ArrayDeque<>();

        // process every cell of the first column
        for (int r = 0; r < M; r++) {
            // if the cell is safe, mark it as visited and
            // enqueue it by assigning it distance as 0
            if (field[r][0] == 1) {
                q.add(new Node(r, 0, 0, null));
                visited[r][0] = true;
            }
        }

        Node node = null;
        // loop till queue is empty
        while (!q.isEmpty()) {

            node = q.poll();
            // dequeue front node and process it
            int i = node.getX();
            int j = node.getY();
            int dist = node.getDist();



            // if the destination is found, return minimum distance
            if (j == N - 1) {
                return node;
            }

            // check for all four possible movements from the current cell
            // and enqueue each valid movement
            for (int k = 0; k < 4; k++) {
                // skip if the location is invalid or visited, or unsafe
                if (isValid(i + row[k], j + col[k], M, N) &&
                        isSafe(field, visited, i + row[k], j + col[k])) {
                    // mark it as visited and enqueue it with +1 distance
                    visited[i + row[k]][j + col[k]] = true;

                    // path.add(new Node(i + row[k], j + col[k], dist + 1, node));
                    q.add(new Node(i + row[k], j + col[k], dist + 1, node));
                }
            }
        }
        return null;
    }

    public void setPath(Node node) {
        if (node == null) {
            return;
        }
        setPath(node.getParent());
        //System.out.println(node);
        path.add(node);
    }

    public List<Node> getPath(){
        return path;
    }

    // Find the shortest path from the first column to the last column in a given field
    public  Node findShortestDistance(Integer[][] field) {

        //rows
        int M = field.length;

        //cols
        int N = field[0].length;

        // `r[]` and `c[]` detail all eight possible movements from a cell
        // (top, right, bottom, left, and four diagonal moves)
        int[] r = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] c = {-1, 0, 1, -1, 1, -1, 0, 1};

        // mark adjacent cells of sensors (value 0) as unsafe with Integer.MAX_VALUE
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 8; k++) {
                    if (field[i][j] == 0 &&
                        isValid(i + r[k], j + c[k], M, N) &&
                        field[i + r[k]][j + c[k]] == 1) {
                            field[i + r[k]][j + c[k]] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        // update the unsafe field to 0
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] == Integer.MAX_VALUE) {
                    field[i][j] = 0;
                }
            }
        }

        // call BFS and return the shortest distance found by it
        return BFS(field);
    }


}
