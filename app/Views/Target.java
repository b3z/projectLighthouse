package app.Views;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GOval;

import java.awt.Color;

public class Target extends GCompound {

    // private Color color;
    // private int width;

    /**
     * Constructor for the target.
     * @param color the color of the target.
     */
    public Target(Color color, int width) {
        // this.color = color;
        // this.width = width;

        GOval circle = new GOval(width + GridPiece.OFFSET / 2, width + GridPiece.OFFSET / 2);
        circle.setColor(color);
        add(circle);

        GLine line = new GLine(0, width / 2, width, width / 2);
        line.setColor(color);
        add(line);

        GLine line2 = new GLine(width / 2, 0, width / 2, width);
        line2.setColor(color);
        add(line2);

    }

    


}