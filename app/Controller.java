package app;

import acm.program.GraphicsProgram;
import app.Views.MenuView;
import app.Model.Direction;
import app.Model.Model;

import java.awt.event.*;

public class Controller implements KeyListener {

    private Model model;
    private Main frame;


    public Controller(Model model, Main frame) {
        this.model = model;
        this.frame = frame;

        frame.addKeyListeners(this);
        //frame.getMenuView().addKeyListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT: model.changeColumn(Direction.LEFT);
                break;

            case KeyEvent.VK_RIGHT: model.changeColumn(Direction.RIGHT);
                break;

            case KeyEvent.VK_ENTER: 
            case KeyEvent.VK_SPACE: model.placeToken();
                break;

            case KeyEvent.VK_S: model.SaveGame();
                break;

            case KeyEvent.VK_ESCAPE: this.frame.toggleMenu(); // toggeli tiggeli dooo
                break;

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