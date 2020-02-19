package app.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import app.Main;

import java.awt.Color;

/**
 * saves and loads the game.
 */
public class GameSaver {

    private static final String PATH = "./data/";

    public static boolean loadGame(Model model) {

        try {
            File file = new File(PATH);
            file.mkdir();
            
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(PATH + "GameStatus.txt"));
            } catch(FileNotFoundException e) {
                //File doesnt exist.
                return false;
            }
            int[] c = new int[3];
            for(int i = 0; i < c.length; i++) {
                c[i] = reader.read();
                reader.readLine();
            }
            Player[] players = new Player[2];
            players[0] = new Player(1, new Color(c[0], c[1], c[2]));
            for(int i = 0; i < c.length; i++) {
                c[i] = reader.read();
                reader.readLine();
            }
            players[1] = new Player(2, new Color(c[0], c[1], c[2]));

            model.setPlayers(players);

            //decoding the board.

            //hardcoded size, not nice but I dont care at the moment.
            //probably should be stored al well.
            Player[][] gameboard = new Player[Main.WIDTH][Main.HEIGHT];
            String line = reader.readLine();

            //TODO Exceptions (NullPointer)
            for(int i = 0; i < Main.WIDTH; i++) { // what is that 9 in the for loop?
                String[] tokens = line.split(" ");
                for(int j = 0; j < tokens.length; j++) {
                    switch(tokens[j]) {
                        case "1":
                            gameboard[i][j] = players[0];
                            break;
                        case "2":
                            gameboard[i][j] = players[1];
                            break;
                    }
                }
                line = reader.readLine();
            }
            
            if(line == "1") {
                model.setCurrentPlayer(players[0]);
            }else {
                model.setCurrentPlayer(players[1]);
            }

            model.setBoard(gameboard);

            reader.close();

            System.out.println("Game loaded from file sucessfully.");
        } catch (IOException e) {
            // catching IOExceptions
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            System.out.println("The File can not be used, maybe externally edited?!");
            System.out.println("Please start a new game.");
            return false;
        }



        return true;
    }
    /**
     * Saves the current game. No actual documentation about how it is decoded.
     * @param board the playing board.
     * @param players the two players.
     */
    public static void saveGame(Player[][] board, Player[] players, Player currentPlayer) {

        try {
            File file = new File(PATH);
            file.mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "GameStatus.txt", false));
            
            //Storing the palyers.
            for(Player p : players) {
                
                // writer.write(p.getColor().getRGB());
                writer.write(p.getColor().getRed());
                writer.newLine();
                writer.write(p.getColor().getGreen());
                writer.newLine();
                writer.write(p.getColor().getBlue());
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
            writer.write(currentPlayer.getID() + "");
            
            writer.close();

            System.out.println("Game saved sucessfully.");
        } catch (IOException e) {
            // catching IOEXceptions
            e.printStackTrace();
        }

    }
}