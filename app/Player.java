package app;

import java.awt.Color;

public class Player {
    private final int id;
    private Color color;


    public Player(int id, Color color) {
        this.id = id;
        this.color = color;
    }

    public int getID() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}