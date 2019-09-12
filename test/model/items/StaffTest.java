package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.map.Location;
import model.units.Cleric;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

/**
 * Test set for staffs
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class StaffTest extends AbstractTestItem {

  private Staff staff_set;
  private Staff staff_receive;
  private Staff wrongStaff;
  private Cleric cleric_set;
  private Cleric cleric_receive;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItems() {
    expectedName = "Common staff";
    expectedPower = 5;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    staff_set = new Staff(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    staff_receive = new Staff("Staff", 50, 0, 10);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongStaff = new Staff("Wrong staff", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnits() {
    cleric_set = new Cleric(10, 5, new Location(0, 0));
    cleric_receive = new Cleric(100, 10, new Location(0, 0),staff_receive);
    cleric_receive.equipItem(staff_receive);
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongStaff;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return staff_set;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric_set;
  }

  @Test
  public void testReceiveAxeAttack() {
    assertEquals(staff_receive.getOwner().getCurrentHitPoints(), 100);
    staff_receive.receiveAxeAttack(axe);
    assertEquals(staff_receive.getOwner().getCurrentHitPoints(), 100 - axe.getPower());
  }

  @Test
  public void testReceiveSpearAttack() {
    assertEquals(staff_receive.getOwner().getCurrentHitPoints(), 100);
    staff_receive.receiveSpearAttack(spear);
    assertEquals(staff_receive.getOwner().getCurrentHitPoints(), 100 - spear.getPower());
  }

  @Test
  public void testReceiveSwordAttack() {
    assertEquals(staff_receive.getOwner().getCurrentHitPoints(), 100);
    staff_receive.receiveSwordAttack(sword);
    assertEquals(staff_receive.getOwner().getCurrentHitPoints(), 100 - sword.getPower());
  }

  @Test
  public void testStaffAttackAndCounter() {
    staff.attackItem(darkBook);
    assertEquals(100,staff.getOwner().getCurrentHitPoints());
    assertEquals(100,darkBook.getOwner().getCurrentHitPoints());
    staff.attackItem(bow);
    assertEquals(bow.getOwner().getCurrentHitPoints(), 100);
    assertEquals(staff.getOwner().getCurrentHitPoints(), 100);
    staff.getOwner().setCurrentHitPoints(100);
    staff.attackItem(axe);
    assertEquals(axe.getOwner().getCurrentHitPoints(), 100);
    assertEquals(staff.getOwner().getCurrentHitPoints(), 100);
    staff.getOwner().setCurrentHitPoints(100);
    staff.attackItem(spear);
    assertEquals(spear.getOwner().getCurrentHitPoints(), 100);
    assertEquals(staff.getOwner().getCurrentHitPoints(), 100);
    staff.getOwner().setCurrentHitPoints(100);
    staff.attackItem(staff_receive);
    assertEquals(staff_receive.getOwner().getCurrentHitPoints(), 100);
    assertEquals(staff.getOwner().getCurrentHitPoints(), 100);
    staff.getOwner().setCurrentHitPoints(100);
    staff.attackItem(sword);
    assertEquals(sword.getOwner().getCurrentHitPoints(),100);
    assertEquals(staff.getOwner().getCurrentHitPoints(),100);
  }
}
