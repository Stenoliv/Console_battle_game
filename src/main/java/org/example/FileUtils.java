package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileUtils {

    //used to check if savegame file is empty or not
    public static String readTextFile(String fileName) {
        StringBuilder readText = new StringBuilder();

        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readText.append(line);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("No savegame found!");

            return "";
        } catch (IOException e) {
            System.out.println("No savegame found!");

        }
        return readText.toString();
    }

    //saves the players Player in a file
    public static void savePlayer(Player player, String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);

            objOutputStream.writeObject(player);
            objOutputStream.flush();
            objOutputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error has occured while saving the game!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("An error has occured while saving the game!");
            throw new RuntimeException(e);

        }
    }

    //reads the saved Player
    public static Player getSavedPlayer(String fileName) {

        Player player;
        try {
            FileInputStream data = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(data);

            player = (Player) objectInputStream.readObject();


            objectInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return player;
    }
}
