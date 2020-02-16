package app;

import java.util.ArrayList;

public class GameBoard {

    private Player[][] board;

    public GameBoard(int width, int height) {
        this.board = new Player[width][height];

    }

    /**
     * returns the highest Streak, that one placement has created.
     * @param x the x postion of that palcement.
     * @param y the y position of that placement.
     * @return the heightest streak found.
     */
    public int getStreakAt(int x, int y) {
        //TODO verschönern, weil code duplikate und so

        Player player = board[x][y];

        int hightestSteak = 0;

        ArrayList<Point> connectedPoints = new ArrayList<Point>();

        //check the horizontal
        for(int i = 0; i < board.length; i++) {
            if(board[i][y] == player) {
                connectedPoints.add(new Point(i, y));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() == 4) {
                return connectedPoints.size();    //four connected tokens found.
            }
        }

        //check vertikal
        hightestSteak = Math.max(hightestSteak, connectedPoints.size());
        connectedPoints.clear();
        for(int i = 0; i < board[x].length; i++) {
            if(board[x][i] == player) {
                connectedPoints.add(new Point(x, i));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() == 4) {
                return connectedPoints.size();    //four connected tokens found.
            }
        }

        //check diagonal
        
        //first diagonal
        hightestSteak = Math.max(hightestSteak, connectedPoints.size());
        connectedPoints.clear();
        int tempx = x;
        int tempy = y;

        while(tempx != 0 && tempy != 0) {   //find a good start point
            tempx--;
            tempy--;
        }
        while(tempx < board.length && tempy < board[tempx].length) {
            if(board[tempx][tempy] == player) {
                connectedPoints.add(new Point(tempx, tempy));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() == 4) {
                return connectedPoints.size();    //four connected tokens found.
            }
            tempx++;
            tempy++;
        }

        //second diagonal
        hightestSteak = Math.max(hightestSteak, connectedPoints.size());
        connectedPoints.clear();
        tempx = x;
        tempy = y;

        while(tempx != board.length - 1 && tempy != 0) {    //find a good start point
            tempx++;
            tempy--;
        }
        while(tempx >= 0 && tempy < board[tempx].length) {
            if(board[tempx][tempy] == player) {
                connectedPoints.add(new Point(tempx, tempy));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() == 4) {
                return connectedPoints.size();    //four connected tokens found.
            }
            tempx--;
            tempy++;
        }

        return hightestSteak;
    }

    public int getHighestStreakFor(Player p) {

        //TODO for AIPlayer?
        return 0;
    }
}