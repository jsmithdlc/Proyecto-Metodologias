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
public class DarkBookTest extends AbstractTestItem {

    private DarkBook darkBook_set;
    private DarkBook darkBook_receive;
    private DarkBook wrongDarkBook;
    private Sorcerer sorcerer_set;
    private Sorcerer sorcerer_receive;

    /**
     * Sets which item is going to be tested
     */
    @Override
    public void setTestItems() {
        expectedName = "DarkBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 3;
        darkBook_set = new DarkBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
        darkBook_receive = new DarkBook("LibroOscuro",50,0,10);
    }
    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongDarkBook = new DarkBook("darkBook malo", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnits() {
        sorcerer_set = new Sorcerer(10, 5, new Location(0, 0));
        sorcerer_receive = new Sorcerer(100,10,new Location(0,0),darkBook_receive);
        sorcerer_receive.equipItem(darkBook_receive);
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongDarkBook;
    }

    /**
     * @return the item being tested
     */
    @Override
    public IEquipableItem getTestItem() {
        return darkBook_set;
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
        assertEquals(darkBook_receive.getOwner().getCurrentHitPoints(),100);
        darkBook_receive.receiveLightBookAttack(lightBook);
        assertEquals(darkBook_receive.getOwner().getCurrentHitPoints(),100-(int)Math.round(lightBook.getPower()*1.5));
    }

    @Test
    public void testReceiveDarkBookAttack(){
        assertEquals(darkBook_receive.getOwner().getCurrentHitPoints(),100);
        darkBook_receive.receiveDarkBookAttack(darkBook);
        assertEquals(darkBook_receive.getOwner().getCurrentHitPoints(),100-darkBook.getPower());
    }

    @Test
    public void testReceiveSpiritBookAttack(){
        assertEquals(darkBook_receive.getOwner().getCurrentHitPoints(),100);
        darkBook_receive.receiveSpiritBookAttack(spiritBook);
        assertEquals(darkBook_receive.getOwner().getCurrentHitPoints(),100-(sword.getPower()-20));
    }

    @Test
    public void testDarkBookAttackAndCounter() {
        darkBook.attackItem(bow);
        assertEquals(100 - (int)Math.round(darkBook.getPower()*1.5),bow.getOwner().getCurrentHitPoints());
        assertEquals(100 - (int)Math.round(bow.getPower()*1.5),darkBook.getOwner().getCurrentHitPoints());
        darkBook.getOwner().setCurrentHitPoints(100);
        darkBook.attackItem(darkBook_receive);
        assertEquals(100 - darkBook.getPower(),darkBook_receive.getOwner().getCurrentHitPoints());
        assertEquals(100 - darkBook_receive.getPower(),darkBook.getOwner().getCurrentHitPoints());
        darkBook.getOwner().setCurrentHitPoints(100);
        darkBook.attackItem(lightBook);
        assertEquals(100 - (darkBook.getPower()-20),lightBook.getOwner().getCurrentHitPoints());
        assertEquals(100 - (int)Math.round(lightBook.getPower()*1.5),darkBook.getOwner().getCurrentHitPoints());
        darkBook.getOwner().setCurrentHitPoints(100);
        darkBook.attackItem(spiritBook);
        assertEquals(100 - (int)Math.round(1.5*darkBook.getPower()),spiritBook.getOwner().getCurrentHitPoints());
        assertEquals(100 - (spiritBook.getPower()-20),darkBook.getOwner().getCurrentHitPoints());
    }
}
