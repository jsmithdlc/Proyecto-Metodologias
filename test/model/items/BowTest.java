package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.map.Location;
import model.units.Archer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

/**
 * Test set for bows
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class BowTest extends AbstractTestItem {

  private Bow bow_set;
  private Bow bow_receive;
  private Bow wrongBow;
  private Archer archer_set;
  private Archer archer_receive;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItems() {
    expectedName = "Common bow";
    expectedPower = 8;
    expectedMinRange = 2;
    expectedMaxRange = 4;
    bow_set = new Bow(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    bow_receive = new Bow("Bow", 50, 2, 10);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongBow = new Bow("Wrong bow", 10, 1, 1);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnits() {
    archer_set = new Archer(10, 5, new Location(0, 0));
    archer_receive = new Archer(100, 10, new Location(0, 0));
    archer_receive.equipItem(bow_receive);
  }

  /**
   * Checks that the minimum range for a bow is greater than 1
   */
  @Override
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() > 1);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongBow;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return bow_set;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer_set;
  }

  @Test
  public void testReceiveAxeAttack() {
    assertEquals(bow_receive.getOwner().getCurrentHitPoints(), 100);
    bow_receive.receiveAxeAttack(axe);
    assertEquals(bow_receive.getOwner().getCurrentHitPoints(), 100 - axe.getPower());
  }

  @Test
  public void testReceiveSpearAttack() {
    assertEquals(bow_receive.getOwner().getCurrentHitPoints(), 100);
    bow_receive.receiveSpearAttack(spear);
    assertEquals(bow_receive.getOwner().getCurrentHitPoints(), 100 - spear.getPower());
  }

  @Test
  public void testReceiveSwordAttack() {
    assertEquals(bow_receive.getOwner().getCurrentHitPoints(), 100);
    bow_receive.receiveSwordAttack(sword);
    assertEquals(bow_receive.getOwner().getCurrentHitPoints(), 100 - sword.getPower());
  }

  @Test
  public void testBowAttackAndCounter() {
    bow.attackItem(bow_receive);
    assertEquals(bow_receive.getOwner().getCurrentHitPoints(), 100 - bow.getPower());
    assertEquals(bow.getOwner().getCurrentHitPoints(), 100 - bow_receive.getPower());
    bow.getOwner().setCurrentHitPoints(100);
    bow.attackItem(axe);
    assertEquals(axe.getOwner().getCurrentHitPoints(), 100 - bow.getPower());
    assertEquals(bow.getOwner().getCurrentHitPoints(), 100 - axe.getPower());
    bow.getOwner().setCurrentHitPoints(100);
    bow.attackItem(spear);
    assertEquals(spear.getOwner().getCurrentHitPoints(), 100 - bow.getPower());
    assertEquals(bow.getOwner().getCurrentHitPoints(), 100 - spear.getPower());
    bow.getOwner().setCurrentHitPoints(100);
    bow.attackItem(staff);
    assertEquals(staff.getOwner().getCurrentHitPoints(), 100 - bow.getPower());
    assertEquals(bow.getOwner().getCurrentHitPoints(), 100);
    bow.getOwner().setCurrentHitPoints(100);
    bow.attackItem(sword);
    assertEquals(sword.getOwner().getCurrentHitPoints(),100-bow.getPower());
    assertEquals(bow.getOwner().getCurrentHitPoints(),100-sword.getPower());
  }
}

