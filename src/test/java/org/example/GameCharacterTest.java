package org.example;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCharacterTest implements Serializable {
    @Test
    void testPlayer() {
        ArrayList<Weapon> inventory = new ArrayList<>();
        Weapon katana = new Weapon("Katana", 20);
        inventory.add(katana);

        Player you = new Player("jack", 120, inventory);

        assertEquals("jack", you.getName());
        assertEquals(120, you.getHitPoints());
        assertEquals(katana, you.inventory.get(0));
    }

    @Test
    void testEnemy() {
        GameCharacter enemy = Npc.spawnNpc();

        assert (!Objects.equals(enemy.getName(), ""));
        assert (enemy.getHitPoints() > 40);

    }

    @Test
    void testFight() {
        ArrayList<Weapon> inventory = new ArrayList<>();
        Weapon sword = new Weapon("Sword", 10);
        inventory.add(sword);

        Npc defender = new Npc("def", 100, inventory);
        Player attacker = new Player("atck", 100, inventory);

        attacker.attack(defender);
        assert (defender.getHitPoints() < 100);
        assert (attacker.getHitPoints() == 100);
    }

    @Test
    void testFightdef() {
        ArrayList<Weapon> inventory = new ArrayList<>();
        Weapon sword = new Weapon("Sword", 10);

        Player defender = new Player("def", 100, inventory);
        Npc attacker = new Npc("atck", 100, inventory);

        attacker.attack(defender);
        assert (defender.getHitPoints() < 100);
        assert (attacker.getHitPoints() == 100);
    }


}
