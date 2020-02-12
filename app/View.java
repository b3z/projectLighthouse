package app;

import java.util.ArrayList;
import app.Model;

/**
 * Inteface‚ views.
 */
interface View {

    /**
     * Updates the view.
     * Called by Model which calls it on changes.
     * 
     * @param model Model of the game.
     * @param changes is a arraylist of changed tokens.
     */
    public void update(Model model, ArrayList<Point> changes);

}