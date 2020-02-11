package app;

import java.awt.Color;
import app.Model;

/**
 * Superclass of views.
 * @param <Model>
 */
abstract class View {

    /** Player A color. */
    private Color playerAColor;
    /** Player B color. */
    private Color playerBColor;

    /** Game Grid width. */
    private int gridWidth;
    /** Game Grid height. */
    private int gridHeight;

    /**
     * Updates the view.
     * Called by Model which calls it on changes.
     * 
     * @param model Model of the game.
     */
    public void update(Model model) {
        
    }

}