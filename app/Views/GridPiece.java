package app.Views;

import java.awt.Color;
import java.awt.Graphics;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

/** A piece of the game grid. */
public class GridPiece extends GObject {

    /** The GridPiece size. We only use circles and quadratic graphics. */
    public static final int SIZE = 50; 
    
    /** Rect which defines the GridPiece. */
    private GRect rect;

    /**
     * The circle inside which represents the palyers token. Not set = not filled.
     */
    private GOval arc;

    /**
     * Offset of circle.
     */
    public static final double OFFSET = 2.5;

    /**
     * Constructor for GridPiece Initializes a GridPiece with non set token.
     */
    public GridPiece() {
        // init rect and arc with their sizes.
        this.rect = new GRect(GridPiece.SIZE, GridPiece.SIZE);
        this.arc = new GOval(GridPiece.SIZE - GridPiece.OFFSET * 2, GridPiece.SIZE - GridPiece.OFFSET * 2);

        // set the rects colors.
        this.rect.setFilled(true);
        this.rect.setColor(LocalView.BOARD_COLOR);

        this.arc.setFilled(true);


    }

    /**
     * Set a token, aka the color of the circle.
     * @param color of thr circle/token.
     */
    public void setToken(Color color) {
        if (color == null)
            this.arc.setColor(Color.WHITE);
        else 
            this.arc.setColor(color);
    }

    /**
     * Set the GridPiece location, including the rect and arc.
     */
    @Override
    public void setLocation(double x, double y) {
        assert x < 0 | y < 0 : "Expected positiv cordinates."; // validating cordinates.
        this.rect.setLocation(x, y);
        this.arc.setLocation(x+GridPiece.OFFSET, y+GridPiece.OFFSET);
    }

    /**
     * Paint the GridPiece with rect and arc.
     */
    @Override
    public void paint(Graphics g) {
        this.rect.paint(g);
        this.arc.paint(g);
    }

    @Override
    public GRectangle getBounds() {
        return null;
    }

}