package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Variables and first text to pop up
        Scanner console = new Scanner(System.in);
        System.out.println("The game has begun!!!!");
        String playerName;
        Player player;
        ArrayList<Weapon> inventory = new ArrayList<>();

        //check for save game and let the user choose to use save game or start a new game
        if (!FileUtils.readTextFile("savedplayer.save").equals("")) {
            System.out.println("Savegame found! Would you like to load it (press y for yes or press enter for no)");

            if (console.nextLine().equals("y")) {
                player = FileUtils.getSavedPlayer("savedplayer.save");
            } else {
                System.out.println("Who are you?");
                playerName = console.nextLine();

                Weapon sword = new Weapon("Sword", 20);
                Weapon crossbow = new Weapon("Crossbow", 30);
                inventory.add(sword);
                inventory.add(crossbow);
                player = new Player(playerName, 100, inventory);
            }

        } else {
            //if no save game was found create a new character
            System.out.println("Who are you?");
            playerName = console.nextLine();

            Weapon sword = new Weapon("Sword", 20);
            Weapon crossbow = new Weapon("Crossbow", 30);
            inventory.add(sword);
            inventory.add(crossbow);
            player = new Player(playerName, 100, inventory);
        }

        //for turnbased combat
        String turn = player.getName();
        //loop while the player is "alive"
        while (player.getHitPoints() > 0) {
            //spawn enemy at beginning of loop
            GameCharacter enemy = Npc.spawnNpc();

            System.out.println("You see a " + enemy.getName() + " walk towards you! What will you do...");
            //fight loop, loops as long as both are alive
            while (player.getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                if (turn.equals(player.getName())) {
                    //To give the player the choice to either attack of flee
                    System.out.println("Do you want to attack?(press enter) or run away (press q)");
                    if (console.nextLine().equals("q")) {
                        System.out.println("You ran away...");
                        break;
                    } else {
                        //display weapons available and have the player choose one
                        System.out.println("Choose your weapon for your attack! You have in your inventory the following:");
                        int index = 1;
                        for (Weapon i : player.inventory) {
                            System.out.println("Name: " + i.getName() + " Damage: " + i.getDamage() + " Press " + index);
                            index++;
                        }
                        //check if valid index in inventory
                        int inventoryIndex = Utils.CheckInputNotOutOfBounds(player.inventory);

                        player.setEquippedWeapon(player.inventory.get(inventoryIndex - 1));
                        //cause damage to enemy
                        int causedDamage = player.attack(enemy);
                        System.out.println(player.getName() + " attacked " + enemy.getName() + " With a " + player.getEquippedWeapon().getName() + " for " + causedDamage + " damage! \n-> " + enemy.getName() + " has " + enemy.getHitPoints() + " health left!\n");
                        turn = enemy.getName();
                    }
                    //check if enemies turn and have the enemy cause damage to player
                } else if (turn.equals(enemy.getName())) {
                    int causedDamage = enemy.attack(player);
                    System.out.println(enemy.getName() + " attacked " + player.getName() + " With a " + enemy.getEquippedWeapon().getName() + " for " + causedDamage + " damage! \n-> " + player.getName() + " has " + player.getHitPoints() + " health left!\n");
                    turn = player.getName();
                }
            }
            //check for if the player fleed during the fight
            if (player.getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                //heal the player and ask what the next action is
                int healedFor = player.heal();
                System.out.println("You managed to escape and rest for a bit \nWhile you rested you healed for " + healedFor + " health! You have now " + player.getHitPoints() + " health!");
                System.out.println("The next enemy has spotted you, what do you do? Engage it (press enter) or run for your life?(to quit press q)");
                if (console.nextLine().equals("q")) {
                    System.out.println("Would you like to save your progress?(Press y for yes or enter for no)");
                    if (console.nextLine().equals("y")) {
                        FileUtils.savePlayer(player, "savedplayer.save");
                    }
                    break;
                }
            } else {
                //check if the player won the fight and let the player pick up the weapon of the enemy
                if (player.getHitPoints() > 0) {
                    System.out.println(player.getName() + " Won the fight with " + player.getHitPoints() + " health left! \nDo you want to take the " + enemy.getEquippedWeapon().getName() + " (" + enemy.getEquippedWeapon().getDamage() + " damage) from the " + enemy.getName() + "? (yes-press y / no - press enter)");
                    if (console.nextLine().equals("y")) {
                        player.inventory.add(enemy.getEquippedWeapon());
                    }
                    //let player inspect their inventory and remove items
                    System.out.println("Would you like to inspect your inventory?(press y to inspect, press enter to continue)");
                    if (console.nextLine().equals("y")) {
                        int index = 1;
                        for (Weapon i : player.inventory) {
                            System.out.println("Slot: " + index + " Name: " + i.getName() + " Damage: " + i.getDamage());
                            index++;
                        }
                        System.out.println("If you like to remove an item please press the corresponding slot number or press enter to continue");
                        String deletedItem = console.nextLine();
                        if (!deletedItem.equals("")) {
                            int inventoryIndex = Utils.CheckInputNotOutOfBounds(player.inventory, deletedItem);
                            System.out.println("Are you sure you want to remove " + player.inventory.get(inventoryIndex - 1).getName() + " (Press y for yes or enter for no!)");
                            if (console.nextLine().equals("y")) {
                                player.inventory.remove(inventoryIndex - 1);
                            }
                        }
                    }

                    int healedFor = player.heal();
                    System.out.println("You rest through the night... During the night you healed for " + healedFor + " You have now " + player.getHitPoints() + " health!");

                    turn = player.getName();
                    //check if the enemy won and end the game
                } else if (enemy.getHitPoints() > 0) {
                    System.out.println(enemy.getName() + " Won the fight with " + enemy.getHitPoints() + " health left!");
                    System.out.println("GAME OVER!");
                    break;
                }
                //to let the player quit before spawning next enemy
                System.out.println("You feel the presence of something!? Do you investigate it?(press enter) or run for your life? (to quit press q)");
                if (console.nextLine().equals("q")) {
                    System.out.println("Would you like to save your progress?(Press y for yes or enter for no)");
                    if (console.nextLine().equals("y")) {
                        FileUtils.savePlayer(player, "savedplayer.save");
                    }
                    break;

                }
            }
        }
    }
}