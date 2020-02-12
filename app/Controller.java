package app;

import acm.program.GraphicsProgram;
import java.awt.event.*;

public class Controller extends GraphicsProgram {

    private Model model;
    private GraphicsProgram frame;


    public Controller(Model model, GraphicsProgram frame) {
        this.model = model;
        this.frame = frame;

        frame.addKeyListener(this);

    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                model.changeColumn(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                model.changeColumn(Direction.RIGHT);
                break;
        }
    }
}