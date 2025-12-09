package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

abstract class GameCharacter implements Serializable {

    private String name;
    private int hitPoints;
    double dexterity;
    Weapon defaultWeapon = new Weapon("Glass bottle", 8);
    Weapon equippedWeapon;
    ArrayList<Weapon> inventory;

    //gamecharacter constructor
    public GameCharacter(String name, int hitPoints, Weapon weapon, double dexterity, ArrayList<Weapon> inventory) {
        this.hitPoints = hitPoints;
        this.name = name;
        this.equippedWeapon = weapon;
        this.dexterity = dexterity;
        this.inventory = inventory;
    }


    public GameCharacter(String name, int hitPoints, double dexterity, ArrayList<Weapon> inventory) {
        this.hitPoints = hitPoints;
        this.name = name;
        this.equippedWeapon = defaultWeapon;
        this.dexterity = dexterity;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }


    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    int attack(GameCharacter defender) {
        double minDamage = this.dexterity * this.getEquippedWeapon().getDamage();
        double maxDamage = this.getEquippedWeapon().getDamage();
        Random random = new Random();

        int causedDamage = (int) random.nextDouble(minDamage, maxDamage);
        defender.setHitPoints(defender.getHitPoints() - causedDamage);
        return (causedDamage);
    }

    abstract void mend();
}
