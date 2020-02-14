package app;

import acm.program.GraphicsProgram;
import java.awt.event.*;

public class Controller implements KeyListener {

    private Model model;
    private GraphicsProgram frame;


    public Controller(Model model, GraphicsProgram frame) {
        this.model = model;
        this.frame = frame;

        frame.addKeyListeners(this);

    }

    public void keyPressed(KeyEvent e) {
        // System.out.println(e);
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
                model.placeToken();
                // System.out.println("Token placed");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}