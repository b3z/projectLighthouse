package app;
import javax.swing.*;

import acm.program.GraphicsProgram;
import app.GridPiece;
import app.Model;

/**
 * The local view on our computer. Aka. the GUI the users sees on his pc.
 * Displays the game.
 */
class LocalView extends View {

    /** The frame we operate on. Aka window. */
    private GraphicsProgram frame;
    /** The grid pieces the game grid is built from. */
    private GridPiece [][] gridPieces;

    public LocalView(int width, int height) {
        this.frame = new GraphicsProgram() {};
        this.frame.setContentSize(width*GridPiece.SIZE, height*GridPiece.SIZE);‚
    }

    /**
     * Updates the Game UI.
     * 
     * @param model the Model
     */
    @Override
    public void update(Model model) {
        //TODO implement
    }
}