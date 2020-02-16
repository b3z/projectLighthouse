package app.Views;

import java.util.ArrayList;
import app.Model;
import app.Player;
import app.Point;

/**
 * Inteface‚ views.
 */
public interface View {

    /**
     * Updates the view.
     * Called by Model which calls it on changes.
     * 
     * @param model Model of the game.
     * @param changes is a arraylist of changed tokens.
     */
    public void update(Model model, ArrayList<Point> changes);


    /**
     * A method to be called when a player has won the game.
     * @param model the model of the game.
     * @param winner the winning player.
     * @param winningPoints the point wich chaused the win.
     */
    public void gameWon(Model model, Player winner, ArrayList<Point> winningPoints);

}