package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

/**
 * Test set for spears
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SpearTest extends AbstractTestItem {

  private Spear javelin_set;
  private Spear wrongSpear;
  private Hero hero;
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
    expectedName = "Javelin";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 3;
    javelin_set = new Spear(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
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
   * checks that attack from spear deals appropriate damage
   */
  @Test
  public void spearAttackTest(){
    assertEquals(30,spear.attack(axe));
    assertEquals(50,spear.attack(bow));
    assertEquals(50,spear.attack(spear));
    assertEquals(75,spear.attack(sword));
    assertEquals(50,spear.attack(staff));
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSpear = new Spear("Wrong spear", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSpear;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return javelin_set;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }
}
