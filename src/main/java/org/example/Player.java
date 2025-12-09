package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Player extends GameCharacter implements Serializable {
    public Player(String name, int hitPoints, Weapon equippedWeapon, ArrayList<Weapon> inventory) {
        super(name, hitPoints, equippedWeapon, 0.8, inventory);
    }

    public Player(String name, int hitPoints, ArrayList<Weapon> inventory) {
        super(name, hitPoints, 0.8, inventory);
    }

    //heals the player with a random chance for a random amount
    public int heal() {
        Random randomNum = new Random();

        if (randomNum.nextInt(0, 3) != 0) {
            int healingAmount = randomNum.nextInt(10, 80);
            if (this.getHitPoints() + healingAmount > 100) {
                this.setHitPoints(100);
                return healingAmount;
            } else {
                this.setHitPoints(this.getHitPoints() + healingAmount);
                return healingAmount;
            }
        } else {
            return 0;
        }

    }

    @Override
    void mend() {

    }
}
