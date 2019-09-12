package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;
import model.units.Sorcerer;
import org.junit.jupiter.api.Test;

/**
 * Test set for darkBook
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SpiritBookTest extends AbstractTestItem {

    private SpiritBook spiritBook_set;
    private SpiritBook spiritBook_receive;
    private SpiritBook wrongSpiritBook;
    private Sorcerer sorcerer_set;
    private Sorcerer sorcerer_receive;

    /**
     * Sets which item is going to be tested
     */
    @Override
    public void setTestItems() {
        expectedName = "SpiritBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 3;
        spiritBook_set = new SpiritBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
        spiritBook_receive = new SpiritBook("SpiritBook",50,0,10);
    }
    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongSpiritBook = new SpiritBook("SpiritBook malo", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnits() {
        sorcerer_set = new Sorcerer(10, 5, new Location(0, 0));
        sorcerer_receive = new Sorcerer(100,10,new Location(0,0),spiritBook_receive);
        sorcerer_receive.equipItem(spiritBook_receive);
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongSpiritBook;
    }

    /**
     * @return the item being tested
     */
    @Override
    public IEquipableItem getTestItem() {
        return spiritBook_set;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer_set;
    }

    @Test
    public void testReceiveLightBookAttack(){
        assertEquals(spiritBook_receive.getOwner().getCurrentHitPoints(),100);
        spiritBook_receive.receiveLightBookAttack(lightBook);
        assertEquals(100 - (lightBook.getPower()-20),spiritBook_receive.getOwner().getCurrentHitPoints());
    }

    @Test
    public void testReceiveDarkBookAttack(){
        assertEquals(spiritBook_receive.getOwner().getCurrentHitPoints(),100);
        spiritBook_receive.receiveDarkBookAttack(darkBook);
        assertEquals(100 - (int)Math.round(darkBook.getPower()*1.5),spiritBook_receive.getOwner().getCurrentHitPoints());
    }

    @Test
    public void testReceiveSpiritBookAttack(){
        assertEquals(spiritBook_receive.getOwner().getCurrentHitPoints(),100);
        spiritBook_receive.receiveSpiritBookAttack(spiritBook);
        assertEquals(spiritBook_receive.getOwner().getCurrentHitPoints(),100-spiritBook.getPower());
    }

    @Test
    public void testDarkBookAttackAndCounter() {
        spiritBook.attackItem(bow);
        assertEquals(100 - (int)Math.round(spiritBook.getPower()*1.5),bow.getOwner().getCurrentHitPoints());
        assertEquals(100 - (int)Math.round(bow.getPower()*1.5),spiritBook.getOwner().getCurrentHitPoints());
        spiritBook.getOwner().setCurrentHitPoints(100);
        spiritBook.attackItem(spiritBook_receive);
        assertEquals(100 - spiritBook.getPower(),spiritBook_receive.getOwner().getCurrentHitPoints());
        assertEquals(100 - spiritBook_receive.getPower(),spiritBook.getOwner().getCurrentHitPoints());
        spiritBook.getOwner().setCurrentHitPoints(100);
        spiritBook.attackItem(darkBook);
        assertEquals(100 - (spiritBook.getPower()-20),darkBook.getOwner().getCurrentHitPoints());
        assertEquals(100 - (int)Math.round(darkBook.getPower()*1.5),spiritBook.getOwner().getCurrentHitPoints());
        spiritBook.getOwner().setCurrentHitPoints(100);
        spiritBook.attackItem(lightBook);
        assertEquals(100 - (int)Math.round(1.5*spiritBook.getPower()),lightBook.getOwner().getCurrentHitPoints());
        assertEquals(100 - (lightBook.getPower()-20),spiritBook.getOwner().getCurrentHitPoints());
    }
}