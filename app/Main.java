package app;

import acm.program.GraphicsProgram;

class Main extends GraphicsProgram {

    // in tokens.
    private final int WIDTH = 14;
    private final int HEIGHT = 28;

    public void init() {
        //create the views.
        LocalView localView = new LocalView(WIDTH, HEIGHT);
        LighthouseView lighthouseView = new LighthouseView();

        //add the local view.
        add(localView);

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