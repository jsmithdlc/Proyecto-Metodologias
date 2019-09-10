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
  private Bow wrongBow;
  private Archer archer;
  private Axe axe;
  private Bow bow;
  private Spear spear;
  private Sword sword;
  private Staff staff;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common bow";
    expectedPower = 8;
    expectedMinRange = 2;
    expectedMaxRange = 4;
    bow_set = new Bow(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
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
  public void setTestUnit() {
    archer = new Archer(10, 5, new Location(0, 0));
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
    return archer;
  }
}
