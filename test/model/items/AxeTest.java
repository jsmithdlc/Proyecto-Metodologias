package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test set for Axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class AxeTest extends AbstractTestItem {

  private Axe axe_set;
  private Axe wrongAxe;
  private Fighter fighter;
  private Axe axe;
  private Bow bow;
  private Spear spear;
  private Sword sword;
  private Staff staff;

  @Override
  public void setTestItem() {
    expectedName = "Common axe";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    axe_set = new Axe(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
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
   * checks that attack from axe deals appropriate damage
   */
  @Test
  public void axeAttackTest(){
    assertEquals(50,axe.attack(axe));
    assertEquals(50,axe.attack(bow));
    assertEquals(75,axe.attack(spear));
    assertEquals(30,axe.attack(sword));
    assertEquals(50,axe.attack(staff));
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongAxe = new Axe("Wrong axe", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongAxe;
  }

  @Override
  public IEquipableItem getTestItem() {
    return axe_set;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }
}