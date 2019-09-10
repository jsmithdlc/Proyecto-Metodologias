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
  private Sword wrongSword;
  private SwordMaster swordMaster;
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
    expectedName = "Common sword";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    sword_set = new Sword(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
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
   * checks that attack from sword deals appropriate damage
   */
  @Test
  public void swordAttackTest(){
    assertEquals(75,sword.attack(axe));
    assertEquals(50,sword.attack(bow));
    assertEquals(30,sword.attack(spear));
    assertEquals(50,sword.attack(sword));
    assertEquals(50,sword.attack(staff));
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
  public void setTestUnit() {
    swordMaster = new SwordMaster(10, 5, new Location(0, 0));
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
    return swordMaster;
  }
}
