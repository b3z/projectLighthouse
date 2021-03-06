package app.Model;

import java.awt.Color;
import java.util.ArrayList;

import app.Views.Audio;
import app.Views.LighthouseView;
import app.Views.LocalView;
import app.Views.View;

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
    private GameBoard board;

    /**
     * indicates, whether the game is over or not.
     */
    private boolean gameOver = false;

    /**
     * width of playing board.
     */
    private final int boardWidth;

    /**
     * height of playing board.
     */
    private final int boardHeight;

    /**
     * A list of every changed point.
     */
    private ArrayList<Point> changedPoints = new ArrayList<Point>();

    /**
     * The model constructor.
     * 
     * @param localView      reference to the local view.
     * @param lighthouseView reference to the lighthouse view.
     * @param width          the width of the playing board.
     * @param height         the height of the playing board.
     */
    public Model(LocalView localView, LighthouseView lighthouseView, int width, int height, boolean loadGame) {

        views.add(localView);
        views.add(lighthouseView);

        this.boardHeight = height;
        this.boardWidth = width;

        // load previous game from file.
        if (!loadGame) {

            // default player configurations.
            this.players[0] = new Player(1, new Color(0, 0, 255));
            this.players[1] = new Player(2, new Color(255, 0, 0));
            this.currentPlayer = players[0];
            this.board = new GameBoard(width, height);
        } else {
            GameSaver.loadGame(this);
        }
        this.currentPlayer.getTarget().setVisible(true);

        localView.addTargets(players);

        updateViews();

    }

    /**
     * places a token in the selected column.
     */
    public void placeToken() {


        Audio.playPlacement(); // is this ok for MVC?
        //dont to anything if the game is over.
        if(gameOver) {
            return;
        }
        //find out, where to place the token
        int i = boardHeight -1;
        while(board.getPlayerAt(currentPlayer.getSelectedColumn(), i) != null) {
            if(i == 0) {
                return;
            }
            i--;
        }
        board.setTokenAt(currentPlayer.getSelectedColumn(), i, currentPlayer);    //place token

        changedPoints.add(new Point(currentPlayer.getSelectedColumn(), i));
        changedPoints.add(new Point(currentPlayer.getSelectedColumn(), 0));
        
        //GameOver?
        ArrayList<Point> connected = board.getStreakAt(currentPlayer.getSelectedColumn(), i);
        if(connected != null) {      //check if player has won
            Audio.playWinning();    //again is this ok in MVC?
            gameOver = true;
            gameOver(currentPlayer, connected);
            return;     //game over dont do anything else
        }
        
        //switches to the next player
        currentPlayer.getTarget().setVisible(false);
        currentPlayer = players[0] == currentPlayer ? players[1] : players[0];
        currentPlayer.getTarget().setVisible(true);
        
        //checks, whether the player had selected a filled column.
        if(board.getPlayerAt(currentPlayer.getSelectedColumn(), 0) != null) {
            changeColumn(Direction.LEFT);
        }
        updateViews();
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
        currentPlayer.changeSelectedColumn(dir.getInt());   //change that palyers selcted column.
        if(currentPlayer.getSelectedColumn() < 0) {
            currentPlayer.setSelectedColumn(boardWidth - 1);
        }
        if(currentPlayer.getSelectedColumn() >= boardWidth) {
            currentPlayer.setSelectedColumn(0);
        }

        //if next column is full
        if(board.getPlayerAt(currentPlayer.getSelectedColumn(), 0) != null) {
            changeColumn(dir);
            //TODO Exceptions.
            //alle reihen voll, etc.
        }
        changedPoints.add(new Point(currentPlayer.getSelectedColumn(), 0));

        updateViews();
    }

    /**
     * returns the color of the  player who has a token at that spot. @null if empty.
     * @param x the x postion on the playing board. Has to be between 0 and @boardWidth.
     * @param y the y postion on the playing board. Has to be between 0 and @boardHeight.
     * @return the Color of that spot.
     */
    public Color getPlayerColorAt(int x, int y) {
        Player p = board.getPlayerAt(x, y);
        if(p == null) {
            return null;
        }
        return p.getColor();
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

    public Player[] getPlayers() {
        return players;
    }

    protected void setPlayers(Player[] players) {
        this.players = players;
    }

    protected void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    protected void setBoard(Player[][] board) {
        this.board = new GameBoard(board);
    }

    /**
     * saves the game.
     */
    public void saveGame() {
        GameSaver.saveGame(board.getBoardArray(), players, currentPlayer);
    }

    /**
     * loads game from file.
     */
    public void loadGame() {
        GameSaver.loadGame(this);
        updateViews();
    }
}