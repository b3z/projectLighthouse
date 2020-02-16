package app;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCompound;
import acm.graphics.GLabel;


/**
 * The window that displays the winner!
 */
class WinnerScreen extends GCompound {

    /**
     * Casual Constructor.
     * 
     * @param winner the player who won.
     */
    public WinnerScreen(Player winner) {
        GLabel yayyLabel = new GLabel("Yayy!");
        GLabel winnerLabel = new GLabel("Player "+ winner.getID()+" wins!");
        
        yayyLabel.setColor(Color.BLACK);
        winnerLabel.setColor(Color.BLACK);
        
        yayyLabel.setFont(new Font("Comic Sans", 1, 28));
        winnerLabel.setFont(new Font("Comic Sans", 1, 28));
        
        yayyLabel.setLocation(Main.WIDTH * GridPiece.SIZE / 2 - yayyLabel.getWidth()/2 , Main.HEIGHT * GridPiece.SIZE / 2 - yayyLabel.getHeight());
        winnerLabel.setLocation(Main.WIDTH * GridPiece.SIZE / 2 - winnerLabel.getWidth()/2, Main.HEIGHT * GridPiece.SIZE / 2);
        
        this.add(yayyLabel);
        this.add(winnerLabel);
    }

}