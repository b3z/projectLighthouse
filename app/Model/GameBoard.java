package app.Model;

import java.util.ArrayList;

/**
 * a class to represent the playing board. and basically do all the thinking that comes with it.
 */
public class GameBoard {

    private Player[][] board;

    public GameBoard(int width, int height) {
        this.board = new Player[width][height];

    }

    public GameBoard(Player[][] board) {
        this.board = board;
    }

    /**
     * returns the player at a specific point.
     * null if no token placed.
     * @param x
     * @param y
     * @return
     */
    public Player getPlayerAt(int x, int y) {
        if(x > board.length || y > board[x].length || x < 0 || y < 0) {
            throw new IllegalArgumentException("x, y müssen innerhalb des Spielbrettts liegen: x= " + x + " y= " + y);
        }
        return board[x][y];
    }

    /**
     * sets a token at a specific position.
     * @param x
     * @param y
     * @param p
     */
    public void setTokenAt(int x, int y, Player p) {
        if(x < 0 || y < 0 || x > board.length || y > board[x].length) {
            throw new IllegalArgumentException();
        }
        board[x][y] = p;
    }


    /**
     * returns the highest Streak, that one placement has created.
     * @param x the x postion of that palcement.
     * @param y the y position of that placement.
     * @return the heightest streak found.
     */
    public ArrayList<Point> getStreakAt(int x, int y) {
        //TODO verschönern, weil code duplikate und so

        Player player = board[x][y];


        ArrayList<Point> connectedPoints = new ArrayList<Point>();

        //check the horizontal
        for(int i = 0; i < board.length; i++) {
            if(board[i][y] == player) {
                connectedPoints.add(new Point(i, y));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() >= 4) {
                return connectedPoints;    //four connected tokens found.
            }
        }
        
        //check vertikal
        connectedPoints.clear();
        for(int i = 0; i < board[x].length; i++) {
            if(board[x][i] == player) {
                connectedPoints.add(new Point(x, i));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() >= 4) {
                return connectedPoints;    //four connected tokens found.
            }
        }
        
        //check diagonal
        
        //first diagonal
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
            tempx++;
            tempy++;
        }
        if(connectedPoints.size() >= 4) {
            return connectedPoints;    //four connected tokens found.
        }
        
        //second diagonal
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
            tempx--;
            tempy++;
        }
        if(connectedPoints.size() >= 4) {
            return connectedPoints;    //four connected tokens found.
        }
        
        return null;
    }

    public int getHighestStreakFor(Player p) {

        //TODO for AIPlayer?
        return 0;
    }

    public Player[][] getBoardArray() {
        return board;
    }
}