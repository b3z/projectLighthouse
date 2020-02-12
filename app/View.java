package app;

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
     */
    public void update(Model model);

}