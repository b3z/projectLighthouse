package app;

import acm.program.GraphicsProgram;
import app.Model.CheckOS;
import app.Model.Model;
import app.Views.Audio;
import app.Views.GridPiece;
import app.Views.LighthouseView;
import app.Views.LocalView;
import app.Views.MenuView;
import java.awt.event.*;

public class Main extends GraphicsProgram {

    // Note: window sizes are debendend on the game boards dimension.
    /** Game board width in tokens. (aka columns). */
    public static final int WIDTH = 9;
    /** Game board height in tokens. (aka rows). */
    public static final int HEIGHT = 7;
    /**
     * shows whether the menu is open or not
     */
    public boolean menu = true;

    /** The gui on our pc. */
    LocalView localView;
    /** The view which displays the game on the lighthouse. */
    LighthouseView lighthouseView;
    /** The menu view. */
    MenuView menuView;

    Model model;

    Controller controller;
    /**
     * Initially called on startup.
     */
    public void init() {

        this.setTitle("4 Gewinnt!!");

        //create the views.
        this.localView = new LocalView(WIDTH, HEIGHT);
        this.lighthouseView = new LighthouseView(WIDTH, HEIGHT);
        this.menuView = new MenuView(WIDTH, HEIGHT);

        //add the local view.
        this.add(this.localView);
        this.add(menuView);

        //Sets the frame size to the board size (also calculating the height of the frame titlebar.)
        if(CheckOS.isWin()) {
            this.setSize(WIDTH * GridPiece.SIZE + 15, HEIGHT * GridPiece.SIZE + 60);
        }else {
            this.setSize(WIDTH * GridPiece.SIZE, HEIGHT * GridPiece.SIZE + this.getInsets().top);
        }

        //create the model.
        model = new Model(localView, lighthouseView, WIDTH, HEIGHT, false);

        //create Controller
        controller = new Controller(model, this);

        //Window close listener ;)
        Runtime.getRuntime().addShutdownHook(new Thread() 
        { 
            public void run() 
            { 
                System.out.println("Window closing"); 
                System.out.println("Autosaving...");
                model.saveGame();
                lighthouseView.closeConnection();
            } 
        }); 

        //toggle menu so on start menu is open
        this.toggleMenu();

        Audio.playBackgroundMusic();

    }


    /**
     * Getter Menu View.
     * Called by Controller.
     * 
     * @return menuView‚
     */
    public MenuView getMenuView() {
        return this.menuView;
    }

    /**
     * Toggles the menu.
     * 
     * We do this here and not in the controller 
     * because I am not comfortable with manipulating
     * the views from within the controller.
     * 
     * That would violate human rights and also discriminate true loving feminists with little white curly haired puppies.
     */
    public void toggleMenu() {
        // System.out.println(this.menuView.getParent() != null);
        if(!menu) {
            this.menuView.setVisible(false);
            this.localView.setVisible(true);
        }
        else {
            this.menuView.setVisible(true);
            this.localView.setVisible(false);
        }
        menu = !menu;
    }

    /**
     * Loads the game from file.
     */
    public void loadGame() {
        if(menu) {     //dont do anything if the menu isnt open.
            return;
        }
        this.removeAll();
        //create the views.
        this.localView = new LocalView(WIDTH, HEIGHT);
        this.lighthouseView = new LighthouseView(WIDTH, HEIGHT);
        this.menuView = new MenuView(WIDTH, HEIGHT);

        //add the local view.
        this.add(this.localView);
        this.add(menuView);
         //create the model.
         model = new Model(localView, lighthouseView, WIDTH, HEIGHT, true);

         //create Controller
         controller.changeModel(model);
 
         //toggle menu so on start menu is open
         this.toggleMenu();
    }

    /**
     * clears the board and starts a new game.
     */
    public void newGame() {
        if(menu) {     //dont do anything if the menu isnt open.
            return;
        }
        removeAll();

        this.localView = new LocalView(WIDTH, HEIGHT);
        this.lighthouseView = new LighthouseView(WIDTH, HEIGHT);
        // this.menuView = new MenuView(WIDTH, HEIGHT);

        //add the local view.
        this.add(this.localView);
        this.add(menuView);

        //Sets the frame size to the board size (also calculating the height of the frame titlebar.)
        if(CheckOS.isWin()) {
            this.setSize(WIDTH * GridPiece.SIZE + 15, HEIGHT * GridPiece.SIZE + 60);
        }else {
            this.setSize(WIDTH * GridPiece.SIZE, HEIGHT * GridPiece.SIZE + this.getInsets().top);
        }

        //create the model.
        model = new Model(localView, lighthouseView, WIDTH, HEIGHT, false);

        controller.changeModel(model);

        //toggle menu so on start menu is open
        this.toggleMenu();
    }

    /**
     * saves the current game in a file.
     */
    public void saveGame() {
        if(menu) {     //dont do anything if the menu isnt open.
            return;
        }
        toggleMenu();
        model.saveGame();
    }

    public static void main(String[] args) {
        //this is the main.
       // System.out.println("Hallo Welt! Hallo Felix!");
        new Main().start();

        //for(int)
    }
}