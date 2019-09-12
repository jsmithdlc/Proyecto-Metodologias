package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SorcererTest extends AbstractTestUnit {
    private Sorcerer sorcerer;

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    @Test
    @Override
    public void equipSorcererLightTest() {
        sorcerer.addItem(getLightBook());
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(getLightBook());
        assertEquals(getLightBook(), sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void equipSorcererDarkTest() {
        sorcerer.addItem(getDarkBook());
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(getDarkBook());
        assertEquals(getDarkBook(), sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void equipSorcererSpiritTest() {
        sorcerer.addItem(getSpiritBook());
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(getSpiritBook());
        assertEquals(getSpiritBook(), sorcerer.getEquippedItem());
    }

    @Test
    public void equipSwordMasterWrongTest() {
        assertNull(sorcerer.getEquippedItem());
        for (int i = 0; i < weapons_list.size(); i++) {
            sorcerer.addItem(weapons_list.get(i));
            weapons_list.get(i).equipSorcerer(sorcerer);
            assertNull(sorcerer.getEquippedItem());
            sorcerer.removeItem(weapons_list.get(i));
        }
    }
}

