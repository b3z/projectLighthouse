package app;

import java.io.IOException;
import java.util.ArrayList;

import lighthouse.LighthouseDisplay;

class LighthouseView implements View {

    private byte[] data = new byte[14 * 28 * 3];
    private LighthouseDisplay display;

    public LighthouseView() {
        // Try connecting to the display
        try {
            display = LighthouseDisplay.getDisplay();
            display.setUsername("b3z");
            display.setToken("API-TOK_z5/O-Zw/d-XG/2-Xn+9-IT56"); 
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Model model, ArrayList<Point> changes) {
        // Send data to the display
        try {

            //TODO Ich habe keinen plan wie man das Bild hier codiert.
            
           // for (Point p : changes)
             //   data[0] = ;

         //    for(int i = 0; i<data.length; i++)
           //     this.data[i] = 1; 

            
            this.display.sendImage(this.data);
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
