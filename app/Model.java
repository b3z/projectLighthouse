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
     * The playing board, in which an empty space in indicated as 0, player 1 as 1 and player two as 2.
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
        int i = BOARD_HEIGHT -1;
        while(board[currentColumn][i] != null) {
            i--;
        }
        board[currentColumn][i] = currentPlayer;
        //TODO weil unschön...
        if(currentPlayer == player1) {
            currentPlayer = player2;
        }else {
            currentPlayer = player1;
        }
        updateViews();
        checkStatus();
        
    }

    /**
     * changes the selected column.
     * @param dir the cahnged direction.
     */
    public void changeColumn(Direction dir) {
        currentColumn = currentColumn + dir.getInt();
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

    /**
     * checks Status of game.
     */
    private void checkStatus() {
        checkHorizontals();
        checkVertikals();
        checkDiagonals();

    }

    private void checkHorizontals() {
        for(int i = 0; i < board.length; i++) {
            int consecuitive = 0;
            Player lastToken = null;
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == lastToken && lastToken != null) {
                    consecuitive++;
                }
                if(consecuitive == 2) {
                    gameOver(lastToken);
                    break;
                }
                lastToken = board[i][j];
            }

        }

    }

    private void checkVertikals() {
        for(int j = 0; j < board[j].length; j++) {
            int consecuitive = 0;
            Player lastToken = null;
            for(int i = 0; i < board.length; i++) {
                if(board[i][j] == lastToken && lastToken != null) {
                    consecuitive++;
                }
                if(consecuitive == 2) {
                    gameOver(lastToken);
                    break;
                }
                lastToken = board[i][j];
            }

        }
    }

    private void checkDiagonals() {

    }
}