import javax.swing.JFrame;

public class Controller implements ActionListener {

    private Model model;
    private JFrame frame;


    public Controller(Model model, JFrame frame) {
        this.model = model;
        this.frame = frame;


    }
}