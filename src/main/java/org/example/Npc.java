package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Npc extends GameCharacter implements Serializable {
    Random randomDmg = new Random();

    public Npc(String name, int hitPoints, Weapon equippedWeapon, ArrayList<Weapon> inventory) {
        super(name, hitPoints, equippedWeapon, 0.5, inventory);


    }

    public Npc(String name, int hitPoints, ArrayList<Weapon> inventory) {
        super(name, hitPoints, 0.5, inventory);


    }

    //spawns a new enemy with randomly chosen name, weapon , health and damage
    static GameCharacter spawnNpc() {
        Random randomNum = new Random();
        ArrayList<Weapon> inventory = new ArrayList<>();

        String[] npcNames = {"Kuchisake", "Kikidoushi", "Tsuyuonna", "Chiwarashi", "Utsuro", "Rekkouki", "Shiromuku", "Sojutsuki", "Byotara", "Gouhoushi"};
        int randomName = randomNum.nextInt(0, 9);
        String enemyName = npcNames[randomName];

        String[] npcWeapons = {"Machete", "Katana", "Paperblade", "Hammer", "Soulwand", "Umbrella", "Bushcutters"};
        int randomWeaponName = randomNum.nextInt(0, 6);
        String weaponName = npcWeapons[randomWeaponName];

        Weapon enemyWeapon = new Weapon(weaponName, randomNum.nextInt(10, 40));
        int hitPoints = randomNum.nextInt(50, 120);
        inventory.add(enemyWeapon);

        return new Npc(enemyName, hitPoints, enemyWeapon, inventory);
    }

    @Override
    void mend() {

    }
}
