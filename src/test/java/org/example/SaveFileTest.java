package org.example;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveFileTest {

    @Test
    void testSavePlayer() {
        ArrayList<Weapon> inventory = new ArrayList<>();
        Weapon katana = new Weapon("katana", 5);
        Weapon bow = new Weapon("Bow", 10);
        inventory.add(katana);
        inventory.add(bow);
        Player player = new Player("Akanji", 100, inventory);

        FileUtils.savePlayer(player, "testSave.save");

        Player player2 = FileUtils.getSavedPlayer("testSave.save");

        assertEquals(player2.inventory.get(0).getDamage(), player.inventory.get(0).getDamage());
        assertEquals(player2.inventory.get(0).getName(), player.inventory.get(0).getName());
        assertEquals(player2.getName(), player.getName());

    }


}
