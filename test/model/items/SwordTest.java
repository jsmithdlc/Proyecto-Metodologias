package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.Test;

/**
 * Test set for swords
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordTest extends AbstractTestItem {

  private Sword sword_set;
  private Sword sword_receive;
  private Sword wrongSword;
  private SwordMaster swordMaster_set;
  private SwordMaster swordMaster_receive;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItems() {
    expectedName = "Common sword";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    sword_set = new Sword(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    sword_receive = new Sword("Sword", 50, 0,10);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSword = new Sword("Wrong sword", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnits() {
    swordMaster_set = new SwordMaster(10, 5, new Location(0, 0));
    swordMaster_receive = new SwordMaster(100,10,new Location(0,0),sword_receive);
    swordMaster_receive.equipItem(sword_receive);
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSword;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return sword_set;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster_set;
  }

  @Test
  public void testReceiveAxeAttack() {
    assertEquals(sword_receive.getOwner().getCurrentHitPoints(), 100);
    sword_receive.receiveAxeAttack(axe);
    assertEquals(sword_receive.getOwner().getCurrentHitPoints(), 100 - (axe.getPower()-20));
  }

  @Test
  public void testReceiveSpearAttack() {
    assertEquals(sword_receive.getOwner().getCurrentHitPoints(), 100);
    sword_receive.receiveSpearAttack(spear);
    assertEquals(sword_receive.getOwner().getCurrentHitPoints(), 100 - (int)Math.round(spear.getPower()*1.5));
  }

  @Test
  public void testReceiveSwordAttack() {
    assertEquals(sword_receive.getOwner().getCurrentHitPoints(), 100);
    sword_receive.receiveSwordAttack(sword);
    assertEquals(sword_receive.getOwner().getCurrentHitPoints(), 100 - sword.getPower());
  }

  @Test
  public void testSwordAttackAndCounter() {
    sword.attackItem(darkBook);
    assertEquals(100 - (int)Math.round(darkBook.getPower()*1.5),sword.getOwner().getCurrentHitPoints());
    assertEquals(100 - (int)Math.round(sword.getPower()*1.5),darkBook.getOwner().getCurrentHitPoints());
    sword.getOwner().setCurrentHitPoints(100);
    sword.attackItem(bow);
    assertEquals(bow.getOwner().getCurrentHitPoints(), 100 -sword.getPower());
    assertEquals(sword.getOwner().getCurrentHitPoints(), 100 - bow.getPower());
    sword.getOwner().setCurrentHitPoints(100);
    sword.attackItem(axe);
    assertEquals(axe.getOwner().getCurrentHitPoints(), 100 - (int)Math.round(1.5*sword.getPower()));
    assertEquals(sword.getOwner().getCurrentHitPoints(), 100 -(axe.getPower()-20));
    sword.getOwner().setCurrentHitPoints(100);
    sword.attackItem(spear);
    assertEquals(spear.getOwner().getCurrentHitPoints(), 100 -(sword.getPower()-20));
    assertEquals(sword.getOwner().getCurrentHitPoints(), 100 - (int)Math.round(1.5*spear.getPower()));
    sword.getOwner().setCurrentHitPoints(100);
    sword.attackItem(staff);
    assertEquals(staff.getOwner().getCurrentHitPoints(), 100 -sword.getPower());
    assertEquals(sword.getOwner().getCurrentHitPoints(), 100);
    sword.getOwner().setCurrentHitPoints(100);
    sword.attackItem(sword_receive);
    assertEquals(sword_receive.getOwner().getCurrentHitPoints(),100-sword.getPower());
    assertEquals(sword.getOwner().getCurrentHitPoints(),100-sword_receive.getPower());
  }
}
