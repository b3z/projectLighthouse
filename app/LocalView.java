package app;

import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GCompound;

/**
 * The local view on our computer. Aka. the GUI the users sees on his pc.
 * Displays the game.
 */
class LocalView extends GCompound implements View {

    /** The grid pieces the game grid is built from. 0,0 ist oben links, x,y */
    private GridPiece[][] gridPieces;

    /** saves the last selected column so we can toggle it again. */
    private int lastSelectedColumn = -1;

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
        this.viewSelected(model.getSelectedColumn());
        this.repaint();
    }
    
    @Override
    public void gameWon(Model model, Player winner, ArrayList<Point> winningPoints) {
        
        //TODO @Luca mach das bitte schöner, aber ich wollte einfach erstmal was haben ;)
        for(Point p : winningPoints) {
            gridPieces[p.getX()][p.getY()].setToken(new Color(0, 255, 0));
        }
        this.repaint();

    }
    /**
     * Shows selected row.
     * 
     * @param column which is selected.
     */
    private void viewSelected(int column) {
        if (this.lastSelectedColumn != -1)
            this.gridPieces[this.lastSelectedColumn][0].toggleSelected();
        this.gridPieces[column][0].toggleSelected();
        this.lastSelectedColumn = column;
    }

}