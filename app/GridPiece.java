package app;

import acm.graphics.GArc;
import acm.graphics.GRect;
import java.awt.Color;

/** A piece of the game grid. */
class GridPiece{

    /** The GridPiece size. We only use circles and quadratic graphics. */
    public static final int SIZE = 20; // it would make sense to declare this static. Anyway there is no reason.


    /** Rect which defines the GridPiece. */
    private GRect rect;

    /** The circle inside which represents the palyers token. Not set = not filled. */
    private GArc arc;
    
    /**
     * Constructor for GridPiece
     * Initializes a GridPiece with non set token.
     */
    public GridPiece() {
       this.rect = new GRect(GridPiece.SIZE, GridPiece.SIZE);
       this.arc = new GArc(GridPiece.SIZE-2, GridPiece.SIZE-2, 0, 360);
    }

    public void setToken(Color color) {
        if(color == null) 
            this.arc.setFilled(false);
        else {
            this.arc.setFilled(true);
            this.arc.setFillColor(color);
        }
    }

}