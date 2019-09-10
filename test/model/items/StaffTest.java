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
  private Staff wrongStaff;
  private Cleric cleric;
  private Staff staff;
  private Axe axe;
  private Bow bow;
  private Spear spear;
  private Sword sword;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common staff";
    expectedPower = 5;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    staff_set = new Staff(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  @Override
  public void setItems(){
    axe = new Axe("Axe",50,0,10);
    bow = new Bow("Bow",50,2,10);
    spear = new Spear("Spear",50,0,10);
    sword = new Sword("Sword",50,0,10);
    staff = new Staff("Staff",50,0,10);
  }

  /**
   * checks that attack from staff heals
   */
  @Test
  public void axeAttackTest(){
    assertEquals(-50,staff.attack(axe));
    assertEquals(-50,staff.attack(bow));
    assertEquals(-50,staff.attack(spear));
    assertEquals(-50,staff.attack(sword));
    assertEquals(-50,staff.attack(staff));
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
  public void setTestUnit() {
    cleric = new Cleric(10, 5, new Location(0, 0));
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
    return cleric;
  }
}
