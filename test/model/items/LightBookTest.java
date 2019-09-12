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
public class LightBookTest extends AbstractTestItem {

    private LightBook lightBook_set;
    private LightBook lightBook_receive;
    private LightBook wrongLightBook;
    private Sorcerer sorcerer_set;
    private Sorcerer sorcerer_receive;

    /**
     * Sets which item is going to be tested
     */
    @Override
    public void setTestItems() {
        expectedName = "lightBook";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 3;
        lightBook_set = new LightBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
        lightBook_receive = new LightBook("lightBook",50,0,10);
    }
    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongLightBook = new LightBook("lightBook malo", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnits() {
        sorcerer_set = new Sorcerer(10, 5, new Location(0, 0));
        sorcerer_receive = new Sorcerer(100,10,new Location(0,0),lightBook_receive);
        sorcerer_receive.equipItem(lightBook_receive);
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongLightBook;
    }

    /**
     * @return the item being tested
     */
    @Override
    public IEquipableItem getTestItem() {
        return lightBook_set;
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
        assertEquals(lightBook_receive.getOwner().getCurrentHitPoints(),100);
        lightBook_receive.receiveLightBookAttack(lightBook);
        assertEquals(lightBook_receive.getOwner().getCurrentHitPoints(),100-lightBook.getPower());
    }

    @Test
    public void testReceiveDarkBookAttack(){
        assertEquals(lightBook_receive.getOwner().getCurrentHitPoints(),100);
        lightBook_receive.receiveDarkBookAttack(darkBook);
        assertEquals(lightBook_receive.getOwner().getCurrentHitPoints(),100-(darkBook.getPower()-20));
    }

    @Test
    public void testReceiveSpiritBookAttack(){
        assertEquals(lightBook_receive.getOwner().getCurrentHitPoints(),100);
        lightBook_receive.receiveSpiritBookAttack(spiritBook);
        assertEquals(lightBook_receive.getOwner().getCurrentHitPoints(),100-(int)Math.round(spiritBook.getPower()*1.5));
    }

    @Test
    public void testLightBookAttackAndCounter() {
        lightBook.attackItem(axe);
        assertEquals(100 - (int)Math.round(lightBook.getPower()*1.5),axe.getOwner().getCurrentHitPoints());
        assertEquals(100 - (int)Math.round(axe.getPower()*1.5),lightBook.getOwner().getCurrentHitPoints());
        lightBook.getOwner().setCurrentHitPoints(100);
        lightBook.attackItem(lightBook_receive);
        assertEquals(100 - lightBook.getPower(),lightBook_receive.getOwner().getCurrentHitPoints());
        assertEquals(100 - lightBook_receive.getPower(),lightBook.getOwner().getCurrentHitPoints());
        lightBook.getOwner().setCurrentHitPoints(100);
        lightBook.attackItem(spiritBook);
        assertEquals(100 - (lightBook.getPower()-20),spiritBook.getOwner().getCurrentHitPoints());
        assertEquals(100 - (int)Math.round(spiritBook.getPower()*1.5),lightBook.getOwner().getCurrentHitPoints());
        lightBook.getOwner().setCurrentHitPoints(100);
        lightBook.attackItem(darkBook);
        assertEquals(100 - (int)Math.round(1.5*lightBook.getPower()),darkBook.getOwner().getCurrentHitPoints());
        assertEquals(100 - (darkBook.getPower()-20),lightBook.getOwner().getCurrentHitPoints());
    }
}
