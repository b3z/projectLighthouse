package app;

import acm.program.GraphicsProgram;
import app.Views.GridPiece;
import app.Views.LighthouseView;
import app.Views.LocalView;

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

    /**
     * Initially called on startup.
     */
    public void init() {

        this.setTitle("4 Gewinnt!!");

        //create the views.
        this.localView = new LocalView(WIDTH, HEIGHT);
        this.lighthouseView = new LighthouseView(WIDTH, HEIGHT);

        //add the local view.
        this.add(localView);
        
        //Sets the frame size to the board size (also calculating the height of the frame titlebar.)
        this.setSize(WIDTH * GridPiece.SIZE, HEIGHT * GridPiece.SIZE + this.getInsets().top);

        //create the model.
        Model model = new Model(localView, lighthouseView, WIDTH, HEIGHT);

        //create COntroller
        Controller controller = new Controller(model, this);

    }

    public static void main(String[] args) {
        //this is the main.
        System.out.println("Hallo Welt! Hallo Felix!");
        new Main().start();
    }
}