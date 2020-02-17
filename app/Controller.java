package app;

import acm.program.GraphicsProgram;
import app.Model.Direction;
import app.Model.Model;

import java.awt.event.*;

public class Controller implements KeyListener {

    private Model model;
    private GraphicsProgram frame;


    public Controller(Model model, GraphicsProgram frame) {
        this.model = model;
        this.frame = frame;

        frame.addKeyListeners(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                model.changeColumn(Direction.LEFT);
                // System.out.println("Left");
                break;
            case KeyEvent.VK_RIGHT:
                model.changeColumn(Direction.RIGHT);
                // System.out.println("Right");
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                model.placeToken();
                // System.out.println("Token placed");
                break;
            case KeyEvent.VK_S:
                model.SaveGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // nothing to do here
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing to do here
        
    }
}