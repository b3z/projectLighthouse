package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver {

    private static final String PATH = "./games";

    public static Player[][] loadGame() {
        return null;
    }

    public static void saveGame(Player[][] board, Player[] players) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "GameStatus.txt"));

            for(Player p : players) {
                writer.write(p.getID() + " ");
            }

            for(Player[] row : board) {
                for(Player p : row) {
                    if(p != null) {
                        writer.write(p.getID() + " ");
                    }else {
                        writer.write("0 ");
                    }
                }
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}