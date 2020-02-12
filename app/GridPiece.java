package app;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

import java.awt.Color;
import java.awt.Graphics;

/** A piece of the game grid. */
class GridPiece extends GObject {

    /** The GridPiece size. We only use circles and quadratic graphics. */
    public static final int SIZE = 100; // it would make sense to declare this static. Anyway there is no reason.

    /** Rect which defines the GridPiece. */
    private GRect rect;

    /**
     * The circle inside which represents the palyers token. Not set = not filled.
     */
    private GArc arc;

    /**
     * Constructor for GridPiece Initializes a GridPiece with non set token.
     */
    public GridPiece() {
        // init rect and arc with their sizes.
        this.rect = new GRect(GridPiece.SIZE, GridPiece.SIZE);
        this.arc = new GArc(GridPiece.SIZE - 2, GridPiece.SIZE - 2, 0, 360);

        // set the rects colors.
        this.rect.setFilled(true);
        this.rect.setFillColor(Color.RED);

        // should be that location by default, just to make it clear.
        this.rect.setLocation(0, 0);
        this.arc.setLocation(0, 0);

        
   }

    public void setToken(Color color) {
        if (color == null)
            this.arc.setFilled(false);
        else {
            this.arc.setFilled(true);
            this.arc.setFillColor(color);
        }
    }

    @Override
    public void paint(Graphics g) {
        this.rect.paint(g);
        this.arc.paint(g);
    }

    @Override
    public GRectangle getBounds() {
        // TODO Auto-generated method stub
        return null;
    }

}