package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWeapon {
    @Test
    void testWeaponName() {
        Weapon bazooka = new Weapon("BAZOOKA", 90);

        assertEquals("BAZOOKA", bazooka.getName());

    }

    @Test
    void testWeaponDamage() {
        Weapon bazooka = new Weapon("BAZOOKA", 90);

        assert (bazooka.getDamage() == 90);

    }


}
