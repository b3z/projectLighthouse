package app;

import acm.program.GraphicsProgram;
import app.Model.Model;
import app.Views.GridPiece;
import app.Views.LighthouseView;
import app.Views.LocalView;
import app.Views.MenuView;

public class Main extends GraphicsProgram {

    // Note: window sizes are debendend on the game boards dimension.
    /** Game board width in tokens. (aka columns). */
    public static final int WIDTH = 9;
    /** Game board height in tokens. (aka rows). */
    public static final int HEIGHT = 7;

    /** The gui on our pc. */
    LocalView localView;
    /** The view which displays the game on the lighthouse. */
    LighthouseView lighthouseView;
    /** The menu view. */
    MenuView menuView;
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
        this.add(this.menuView);
        this.add(this.localView);

        //Sets the frame size to the board size (also calculating the height of the frame titlebar.)
        this.setSize(WIDTH * GridPiece.SIZE, HEIGHT * GridPiece.SIZE + this.getInsets().top);

        //create the model.
        Model model = new Model(localView, lighthouseView, WIDTH, HEIGHT);

        //create COntroller
        Controller controller = new Controller(model, this);

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
        System.out.println(this.menuView.getParent() != null);
        if(this.menuView.getParent() != null) {
            this.remove(this.menuView);
            this.localView.setVisible(true);
        }
        else {
            this.add(this.menuView);
            this.localView.setVisible(false);
        }
    }

    public static void main(String[] args) {
        //this is the main.
        System.out.println("Hallo Welt! Hallo Felix!");
        new Main().start();
    }
}