package app;

import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GCompound;

/**
 * The local view on our computer. Aka. the GUI the users sees on his pc.
 * Displays the game.
 */
class LocalView extends GCompound implements View {

    /**
     * The general board color. TODO Might be changeable in the settings!?
     */
    public static final Color BOARD_COLOR = new Color(214, 186, 0);

    /** The grid pieces the game grid is built from. 0,0 ist oben links, x,y */
    private GridPiece[][] gridPieces;

    /**
     * Init LocalView.
     * 
     * @param width  Höhe in Spielsteinen!
     * @param height Breite in Spielsteinen!
     */
    public LocalView(int width, int height) {
        this.gridPieces = new GridPiece[width][height]; // x, y
        this.drawBoard();

    }

    /**
     * Draws an emtpy board with set colors.
     */
    private void drawBoard() {
        for (int x = 0; x < gridPieces.length; x++) { // iterate through the rows.
            for (int y = 0; y < gridPieces[x].length; y++) { // iterate through the columns.
                gridPieces[x][y] = new GridPiece();
                gridPieces[x][y].setLocation(x * GridPiece.SIZE, y * GridPiece.SIZE);
                gridPieces[x][y].setToken(null);
                this.add(gridPieces[x][y]);
            }
        }

    }
    
    /**
     * Updates the Game UI.
     * 
     * @param model the Model
     * @param changes is a arraylist of changed tokens.
     */
    @Override
    public void update(Model model, ArrayList <Point> changes) {
        //update changed Tokens.
        for(Point p: changes)
        gridPieces[p.getX()][p.getY()].setToken(model.getPlayerColorAt(p.getX(), p.getY()));
        
        // update selected column.
        Player player = model.getCurrentPlayer();
        double x = GridPiece.SIZE * player.getSelectedColumn();
        player.getTarget().move(x - player.getTarget().getX(), 0);
        this.repaint();
    }
    
    /**
     * Called when a player wins. Displays the winner.
     * @param model is our model.
     * @param winner is the player who won.
     * @param winningPoints are the points which are connected as 4 in a row.
     */
    @Override
    public void gameWon(Model model, Player winner, ArrayList<Point> winningPoints) {
        
        // make 4 tokens in a row green.
        for(Point p : winningPoints)
            gridPieces[p.getX()][p.getY()].setToken(new Color(0, 255, 0));  

        this.repaint(); // repaint window so changes also apply visually.

        //display winner screen
        WinnerScreen winnerScreen = new WinnerScreen(winner);
        this.add(winnerScreen);
        // winnerScreen.sendForward(); // make sure it is in front.
    }

    /**
     * initially adding targets to canvas.
     * @param players
     */
    public void addTargets(Player[] players) {
        for(Player p : players) {
            add(p.getTarget());
        }
    }

}