package app;

import acm.program.GraphicsProgram;
import java.awt.event.*;

public class Controller implements ActionListener {

    private Model model;
    private GraphicsProgram frame;


    public Controller(Model model, GraphicsProgram frame) {
        this.model = model;
        this.frame = frame;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}