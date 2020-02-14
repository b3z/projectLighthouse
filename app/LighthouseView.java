package app;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import lighthouse.LighthouseDisplay;

class LighthouseView implements View {

    private static final int DISPLAY_WIDTH = 14;
    private static final int DISPLAY_HEIGHT = 28;

    private byte[] data = new byte[DISPLAY_WIDTH * DISPLAY_HEIGHT * 3];
    private LighthouseDisplay display;
    private int boardWidth;
    private int boardHeight;

    public LighthouseView(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        // Try connecting to the display
        try {
            display = LighthouseDisplay.getDisplay();
            display.setUsername("Felioh");
            display.setToken("API-TOK_N7tj-2yg7-2NOB-CnST-bxCS"); 
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        generateBoard();
    }

    /**
     * Generates the lanes.
     */
    private void generateBoard() {
        for(int x = 0; x < DISPLAY_WIDTH; x += 3) {
            for(int y = 0; y < DISPLAY_HEIGHT; y++) {
                Color color = new Color(255, 255, 255);
                data[getDisplayIndex(x, y)] = (byte) color.getRed();
                data[getDisplayIndex(x, y) + 1] = (byte) color.getRed();
                data[getDisplayIndex(x, y) + 2] = (byte) color.getRed();
            }
        }
        // for(int y = 0; y < DISPLAY_HEIGHT - 1; y += 3) {
        //     Color color = new Color(255, 255, 255);
        //     data[getDisplayIndex(x, y)] = (byte) color.getRed();
        //     data[getDisplayIndex(x, y) + 1] = (byte) color.getRed();
        //     data[getDisplayIndex(x, y) + 2] = (byte) color.getRed();
        // }

        try {
            this.display.sendImage(this.data);
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void update(Model model, ArrayList<Point> changes) {
        // Send data to the display

        for(Point p : changes) {
            Color color = model.getPlayerColorAt(p.getX(), p.getY());
            if(color == null) {
                color = new Color(0, 0, 0);
            }
            for(int i = 0; i < 2; i++) {
                
                data[getDisplayIndex((1 + p.getX() * 3) + i, p.getY() * 2)] = (byte) color.getRed();
                data[getDisplayIndex((1 + p.getX() * 3) + i, p.getY() * 2) + 1] = (byte) color.getGreen();
                data[getDisplayIndex((1 + p.getX() * 3) + i, p.getY() * 2) + 2] = (byte) color.getBlue();

            }


        }

        // for(Point p : changes) {
        //     // System.out.println(scale(p.getX(), 0, boardWidth, 0 , DISPLAY_WIDTH) + 
        //     //     ", " + scale(p.getY(), 0, boardHeight, 0, DISPLAY_HEIGHT));
        //     Point startPixel = new Point(scale(p.getX(), 0, boardWidth, 0, DISPLAY_WIDTH), scale(p.getY(), 0, boardHeight, 0, DISPLAY_HEIGHT));
        //     Point endPixel = new Point(scale(p.getX() + 1, 0, boardWidth, 0, DISPLAY_WIDTH) - 1, scale(p.getY() + 1, 0, boardHeight, 0, DISPLAY_HEIGHT) - 1);
        //     System.out.println("(" + startPixel.getX() + ", " + startPixel.getY() + ")");
        //     System.out.println("(" + endPixel.getX() + ", " + endPixel.getY() + ")");
        //     Color color = model.getPlayerColorAt(p.getX(), p.getY());
        //     if(color == null) {
        //         color = new Color(0, 0, 0);
        //     }

        //     for(int i = startPixel.getX(); i < endPixel.getX(); i++) {
        //         for(int j = startPixel.getY(); j < endPixel.getY(); j++) {
        //             data[3] = (byte) Color.RED.getRed();
        //             data[getDisplayIndex(i, j) + 0] = (byte) color.getRed();
        //             data[getDisplayIndex(i, j) + 1] = (byte) color.getGreen();
        //             data[getDisplayIndex(i, j) + 2] = (byte) color.getBlue();
        //         }
        //     }
        // }

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

    /**
     * converts a point on the Display to the index inside its array.
     * @param x x value.
     * @param y y value.
     * @return the index inside the data array.
     */
    private int getDisplayIndex(int x, int y) {
        return (y * DISPLAY_WIDTH + x) * 3;
    }

    /**
     * sclaes a given value to another range of values. As an integer.
     * @param m the value to scale.
     * @param rmin mimimun of the values range.
     * @param rmax maximum of the values range.
     * @param tmin minimum of the target range.
     * @param tmax maximum of the target range.
     * @return the scaled value.
     */
    private int scale(int m, int rmin, int rmax, int tmin, int tmax) {
        return (int) ((m - rmin) / (double) (rmax - rmin) * (tmax - tmin) + tmin);

    }
}
