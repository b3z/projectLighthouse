package app.Views;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.gui.VPanel;

import java.awt.Button; // we use awt Buttons here because GLabels suck :D
import java.awt.Font;

/**
 * This MenuView class displays the Game Menu which is loaded on startup and also 
 * functions as pause menu when the game is paused.
 * 
 * Functionalites are saving, loading and managing the game.
 */
public class MenuView extends GCompound {

    /** The box we put the buttons in. vpanel orders buttons vertically. */
    VPanel vbox;

    /** Starts a new game */
    private GLabel newGame;
    /** Save the currently running game. */
    private GLabel saveGame;
    /** Load the saved game. */
    private GLabel loadGame;

    private GLabel menu;

    /** Window width in pixels. */
    private int width;
    /** Windows heigth in pixels. */
    private int height;

    /**
     * Constructor assembles the Menu.
     * 
     * @param width in tokens .
     */
    public MenuView(int width, int height) {

        // calculate window sizes.
        this.width = width * GridPiece.SIZE;
        this.height = height * GridPiece.SIZE;

        // init new buttons.
        this.newGame = new GLabel("[ N ] New Game");
        this.saveGame = new GLabel("[ S ] Save Game");
        this.loadGame = new GLabel("[ L ] Load Game");
        this.menu = new GLabel("[ ESC ] Toggle Menu");

        //set fonts
        this.newGame.setFont(new Font("Comic Sans", 1, 18));
        this.loadGame.setFont(new Font("Comic Sans", 1, 18));
        this.saveGame.setFont(new Font("Comic Sans", 1, 18));
        this.menu.setFont(new Font("Comic Sans", 1, 18));

        
        // add buttons (order matthers).
        this.add(this.newGame);
        this.add(this.saveGame);
        this.add(this.loadGame);
        this.add(this.menu);



        //temp solution wanna make that dynamic
        int x = 100;
        int y = 100;

        this.newGame.setLocation(10+x, 30+y);
        this.saveGame.setLocation(10+x, 50+y);
        this.loadGame.setLocation(10+x, 70+y);
        this.menu.setLocation(10+x, 90+y);

      

    }
    

    
}