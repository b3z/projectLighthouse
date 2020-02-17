package app.Model;

import java.awt.Color;

import app.Views.GridPiece;
import app.Views.Target;

public class Player {
    private final int id;
    private Color color;
    private int selctedColumn = 0;
    private Target target;


    public Player(int id, Color color) {
        this.id = id;
        this.color = color;
        this.target = new Target(color, GridPiece.SIZE);
        this.target.setVisible(false);
    }

    public int getID() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public int getSelectedColumn() {
        return selctedColumn;
    }

    public void changeSelectedColumn(int column) {
        this.selctedColumn += column;
    }

    public void setSelectedColumn(int column) {
        this.selctedColumn = column;
    }

    public Target getTarget() {
        return target;
    }
}