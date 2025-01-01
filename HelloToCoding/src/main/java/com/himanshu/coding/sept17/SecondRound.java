package com.himanshu.coding.sept17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SecondRound {
}


enum Colour {
    BLACK,WHITE
}

enum PieceType {
    PAWN,BISHAP,KNIGHT,ROOK,KING,QUEEN
}

class Position {
    String row;
    String column;
}

//TODO(hibhardw): See if we could have proper interface definition on this
class  Piece {
    Colour colour;
    Position position;
    PieceType pieceType;
}

class Board {
}


/*
1. 8**
2. 1 board
    all pieces
    method to valiate if it is valid setup or not
    if it required change of state of something, then board will do that


3. Pieces
    Position(-1)
    colour
    method-- change position of piece

4. Player
    colour




 */


class Graph {
    List<Integer> adjist [];
    int numberOfNodes;

    public Graph(int numNodes) {
        this.numberOfNodes = numNodes;
        adjist= new List[numNodes];

        for (int i=0;i<numNodes;i++) {
            adjist[i] = new ArrayList<>();
        }
    }
    public void BFS(int root) {
        Queue<Integer> queue = new LinkedList<>();
        boolean [] isVisited = new boolean[numberOfNodes];
        queue.add(root);
        isVisited[root] = true;

        while (!queue.isEmpty()) {
            int currentRoot = queue.poll();
            System.out.println(currentRoot);

            for (int neighbours: adjist[currentRoot]) {
                if (isVisited[neighbours] == false) {
                    queue.add(neighbours);
                    isVisited[neighbours] = true;
                }
            }
        }
    }
}

//Sigeton
//Factor
//abstract
//builder
//utill
//chain
//Adapter

