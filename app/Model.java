package app;

import java.awt.Color;

public class Model {

    private LocalView localView;
    private LighthouseView lighthouseView;
    private int currentColumn;

    /**
     * first Player.
     */
    private Player player1;

    /**
     * second player.
     */
    private Player player2;

    /**
     * the current Player.
     */
    private Player currentPlayer;

    /**
     * The playing board, in which an empty spot is indicated as null.
     */
    private Player[][] board;

    /**
     * width of playing board.
     */
    private final int BOARD_WIDTH;

    /**
     * height of playing board.
     */
    private final int BOARD_HEIGHT;



    /**
     * The model constructor.
     * @param localView reference to the local view.
     * @param lighthouseView reference to the lighthouse view.
     * @param width the width of the playing board.
     * @param height the height of the playing board.
     */
    public Model(LocalView localView, LighthouseView lighthouseView, int width, int height) {
        this.localView = localView;
        this.lighthouseView = lighthouseView;
        this.BOARD_HEIGHT = height;
        this.BOARD_WIDTH = width;

        //default player configurations.
        this.player1 = new Player(1, new Color(0, 0, 255));
        this.player2 = new Player(2, new Color(255, 0, 0));
        this.currentPlayer = player1;

        this.board = new Player[width][height];

    }

    /**
     * places a token in the selected column.
     */
    public void placeToken() {
        //find out, where to place the token
        int i = BOARD_HEIGHT -1;
        while(board[currentColumn][i] != null) {
            i--;
        }
        board[currentColumn][i] = currentPlayer;    //place token

        updateViews();
        if(hasWon(currentColumn, i, currentPlayer)) {      //check if player has won
            gameOver(currentPlayer);
        }
        
        //TODO weil unschön...
        if(currentPlayer == player1) {
            currentPlayer = player2;
        }else {
            currentPlayer = player1;
        }
    }

    /**
     * changes the selected column.
     * @param dir the cahnged direction.
     */
    public void changeColumn(Direction dir) {
        currentColumn = currentColumn + dir.getInt();
        if(currentColumn < 0) {
            currentColumn = BOARD_WIDTH - 1;
        }
        if(currentColumn >= BOARD_WIDTH) {
            currentColumn = 0;
        }
    }

    /**
     * returns the color of the  player who has a token at that spot. @null if empty.
     * @param x the x postion on the playing board.
     * @param y the y postion on the playing board.
     * @return the Color of that spot.
     */
    public Color getPlayerColorAt(int x, int y) {
        return board[x][y].getColor();
    }

    /**
     * updates both views.
     */
    private void updateViews() {
        localView.update(this);
        // lighthouseView.update(this);
    }

    /**
     * notifies the views of the winner.
     * @param winner the player that won the game.
     */
    private void gameOver(Player winner) {
        // localView.gameWon(winner);
        // lighthouseView.gameWon(winner);
    }

    private boolean hasWon(int x, int y, Player player) {

        //check the horizontal
        int count = 0;
        for(int i = 0; i < board.length; i++) {
            if(board[i][y] == player) {
                count++;
            }else {
                count = 0;
            }
            if(count == 4) {
                return true;
            }
        }

        //check vertikal
        count = 0;
        for(int i = 0; i < board[x].length; i++) {
            if(board[x][i] == player) {
                count++;
            }else {
                count = 0;
            }
            if(count == 4) {
                return true;
            }
        }

        //check diagonal
        
        //TODO weil scheiße...
        


        return false;
    }
}