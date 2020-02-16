package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;

/**
 * saves and loads the game.
 * TODO NullPointerExceptions, file externally edited, etc.
 */
public class GameSaver {

    private static final String PATH = "./data/";

    public static Player[][] loadGame() {

        //hardcoded size, not nice but I dont care at the moment.
        //probably should be stored al well.
        Player[][] gameboard = new Player[9][7];

        try {
            File file = new File(PATH);
            file.mkdir();
            BufferedReader reader = new BufferedReader(new FileReader(PATH + "GameStatus.txt"));

            Player player1 = new Player(1, new Color(reader.read()));
            Player player2 = new Player(2, new Color(reader.read()));

            reader.readLine();
            reader.readLine();
            String line = reader.readLine();


            for(int i = 0; i < 9; i++) {
                String[] tokens = line.split(" ");
                for(int j = 0; j < tokens.length; j++) {
                    switch(tokens[j]) {
                        case "1":
                            gameboard[i][j] = player1;
                            break;
                        case "2":
                            gameboard[i][j] = player2;
                            break;
                    }
                }
                System.out.println();
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            // catching IOExceptions
            e.printStackTrace();
        }



        return gameboard;
    }
    /**
     * Saves the current game. No actual documentation about how it is decoded.
     * @param board the playing board.
     * @param players the two players.
     */
    public static void saveGame(Player[][] board, Player[] players) {

        try {
            File file = new File(PATH);
            file.mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "GameStatus.txt"));
            // for(Player p : players) {
            //     writer.write(p.getID() + " ");
            // }
            
            
            //Storing the palyers.
            for(Player p : players) {
                
                writer.write(p.getColor().getRGB());
                // writer.write(p.getColor().getRed() + " ");
                // writer.write(p.getColor().getGreen() + " ");
                // writer.write(p.getColor().getBlue() + " ");
                writer.newLine();
            }

            //Stores the gameBoard.
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
            // catching IOEXceptions
            e.printStackTrace();
        }


    }
}