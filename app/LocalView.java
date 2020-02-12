package app;

import java.awt.Color;

import acm.graphics.GCompound;

/**
 * The local view on our computer. Aka. the GUI the users sees on his pc.
 * Displays the game.
 */
class LocalView extends GCompound implements View {

    /** The grid pieces the game grid is built from. 0,0 ist oben links, x,y */
    private GridPiece [][] gridPieces;

    /**
     * Init LocalView.
     * 
     * @param width Höhe in Spielsteinen!
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
                gridPieces[x][y].setLocation(x*GridPiece.SIZE, y*GridPiece.SIZE);
                gridPieces[x][y].setToken(null);
                this.add(gridPieces[x][y]);
            }
        }
    }
    /**
     * Updates the Game UI.
     * 
     * @param model the Model
     */
    @Override
    public void update(Model model) {
        for (int x = 0; x < gridPieces.length; x++) { // iterate through the rows.
            for (int y = 0; y < gridPieces[x].length; y++) { // iterate through the columns.
                gridPieces[x][y].setToken(model.getPlayerColorAt(x, y));
            }
        }
    }
}