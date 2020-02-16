package app;

import java.awt.Color;
import java.util.ArrayList;

/**
 * the model of this program. This is where the magic happens.
 */
public class Model {

    /**
     * a lsit of every view.
     */
    private ArrayList<View> views = new ArrayList<View>();

    /**
     * an array of both players.
     */
    private Player[] players = new Player[2];

    /**
     * the current Player.
     */
    private Player currentPlayer;

    /**
     * The playing board, in which an empty spot is indicated as null.
     */
    private Player[][] board;

    /**
     * indicates, whether the game is over or not.
     */
    private boolean gameOver = false;

    /**
     * width of playing board.
     */
    private final int BOARD_WIDTH;

    /**
     * height of playing board.
     */
    private final int BOARD_HEIGHT;

    /**
     * A list of every changed point.
     */
    private ArrayList<Point> changedPoints = new ArrayList<Point>();



    /**
     * The model constructor.
     * @param localView reference to the local view.
     * @param lighthouseView reference to the lighthouse view.
     * @param width the width of the playing board.
     * @param height the height of the playing board.
     */
    public Model(LocalView localView, LighthouseView lighthouseView, int width, int height) {

        views.add(localView);
        views.add(lighthouseView);

        this.BOARD_HEIGHT = height;
        this.BOARD_WIDTH = width;

        //default player configurations.
        this.players[0] = new Player(1, new Color(0, 0, 255));
        this.players[1] = new Player(2, new Color(255, 0, 0));
        this.currentPlayer = players[0];
        this.currentPlayer.getTarget().setVisible(true);

        localView.addTargets(players);;

        this.board = new Player[width][height];

    }

    /**
     * places a token in the selected column.
     */
    public void placeToken() {

        //dont to anything if the game is over.
        if(gameOver) {
            return;
        }
        //find out, where to place the token
        int i = BOARD_HEIGHT -1;
        while(board[currentPlayer.getSelectedColumn()][i] != null) {
            if(i == 0) {
                return;
            }
            i--;
        }
        board[currentPlayer.getSelectedColumn()][i] = currentPlayer;    //place token

        changedPoints.add(new Point(currentPlayer.getSelectedColumn(), i));
        updateViews();

        //GameOver?
        ArrayList<Point> connected = hasWon(currentPlayer.getSelectedColumn(), i, currentPlayer);
        if(connected != null) {      //check if player has won
            gameOver = true;
            gameOver(currentPlayer, connected);
        }
        
        //switches to the next player
        currentPlayer.getTarget().setVisible(false);
        currentPlayer = players[0] == currentPlayer ? players[1] : players[0];
        currentPlayer.getTarget().setVisible(true);

        //checks, whether the player had selected a filled column.
        if(board[currentPlayer.getSelectedColumn()][0] != null) {
            changeColumn(Direction.LEFT);
        }
    }

    /**
     * changes the selected column.
     * @param dir the cahnged direction.
     */
    public void changeColumn(Direction dir) {

        //dont do anything if the game is over.
        if(gameOver) {
            return;
        }

        changedPoints.add(new Point(currentPlayer.getSelectedColumn(), 0));
        currentPlayer.changeSelectedColumn(dir.getInt());
        if(currentPlayer.getSelectedColumn() < 0) {
            currentPlayer.setSelectedColumn(BOARD_WIDTH - 1);
        }
        if(currentPlayer.getSelectedColumn() >= BOARD_WIDTH) {
            currentPlayer.setSelectedColumn(0);
        }

        //if next column is full
        if(board[currentPlayer.getSelectedColumn()][0] != null) {
            changeColumn(dir);
            //TODO Exceptions.
            //alle reihen voll, etc.
        }
        changedPoints.add(new Point(currentPlayer.getSelectedColumn(), 0));

        updateViews();
    }

    /**
     * returns the color of the  player who has a token at that spot. @null if empty.
     * @param x the x postion on the playing board. Has to be between 0 and @BOARD_WIDTH.
     * @param y the y postion on the playing board. Has to be between 0 and @BOARD_HEIGHT.
     * @return the Color of that spot.
     */
    public Color getPlayerColorAt(int x, int y) {
        if(x > board.length || y > board[x].length || x < 0 || y < 0) {
            throw new IllegalArgumentException("x, y müssen innerhalb des Spielbrettts liegen: x= " + x + " y= " + y);
        }
        if(board[x][y] == null) {
            return null;
        }
        return board[x][y].getColor();
    }

    /**
     * returns the selected column.
     * @return the column that is selected.
     */
    public int getSelectedColumn() {
        return currentPlayer.getSelectedColumn();
    }

    /**
     * Gets the active Player.
     * @return the @Player, whos turn it is.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * updates both views.
     */
    private void updateViews() {
        for(View view : views) {
            view.update(this, changedPoints);
        }
        changedPoints.clear();
    }

    /**
     * notifies the views of the winner.
     * @param winner the player that won the game.
     * @param points the winning points.
     */
    private void gameOver(Player winner, ArrayList<Point> points) {
        // System.out.println("Player " + winner.getID() + " has won. @Luca implement gameWon method!!");
        for(View view : views) {
            view.gameWon(this, winner, points);
        }
    }

    /**
     * checks the game status.
     * @param x the changed x position.
     * @param y the chenged y position.
     * @param player the player who changed the postition.
     * @return 
     */
    private ArrayList<Point> hasWon(int x, int y, Player player) {

        //TODO verschönern, weil code duplikate und so

        ArrayList<Point> connectedPoints = new ArrayList<Point>();

        //check the horizontal
        for(int i = 0; i < board.length; i++) {
            if(board[i][y] == player) {
                connectedPoints.add(new Point(i, y));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() == 4) {
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
            if(connectedPoints.size() == 4) {
                return connectedPoints;    //four connected tokens found.
            }
        }

        //check diagonal
        
        //first diagonal
        int tempx = x;
        int tempy = y;

        while(tempx != 0 && tempy != 0) {   //find a good start point
            tempx--;
            tempy--;
        }
        connectedPoints.clear();
        while(tempx < board.length && tempy < board[tempx].length) {
            if(board[tempx][tempy] == player) {
                connectedPoints.add(new Point(tempx, tempy));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() == 4) {
                return connectedPoints;    //four connected tokens found.
            }
            tempx++;
            tempy++;
        }

        //second diagonal
        tempx = x;
        tempy = y;

        while(tempx != board.length - 1 && tempy != 0) {    //find a good start point
            tempx++;
            tempy--;
        }
        connectedPoints.clear();
        while(tempx >= 0 && tempy < board[tempx].length) {
            if(board[tempx][tempy] == player) {
                connectedPoints.add(new Point(tempx, tempy));
            }else {
                connectedPoints.clear();
            }
            if(connectedPoints.size() == 4) {
                return connectedPoints;    //four connected tokens found.
            }
            tempx--;
            tempy++;
        }

        return null;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void SaveGame() {
        GameSaver.saveGame(board, players);
    }
}