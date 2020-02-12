package app;

import java.awt.Dimension;

import acm.program.GraphicsProgram;
import apple.laf.JRSUIConstants.Widget;

class Main extends GraphicsProgram {

    // in tokens.
    private final int WIDTH = 6;
    private final int HEIGHT = 7;

    public void init() {
        //create the views.
        LocalView localView = new LocalView(WIDTH, HEIGHT);
        LighthouseView lighthouseView = new LighthouseView();

        
        //add the local view.
        add(localView);
        
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