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
  private Spear javelin_receive;
  private Spear wrongSpear;
  private Hero hero_set;
  private Hero hero_receive;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItems() {
    expectedName = "Javelin";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 3;
    javelin_set = new Spear(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    javelin_receive = new Spear("Javalina",50,0,10);
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
  public void setTestUnits() {
    hero_set = new Hero(10, 5, new Location(0, 0));
    hero_receive = new Hero(100,10,new Location(0,0));
    hero_receive.equipItem(javelin_receive);
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
    return hero_set;
  }

  @Test
  public void testReceiveAxeAttack(){
    assertEquals(javelin_receive.getOwner().getCurrentHitPoints(),100);
    javelin_receive.receiveAxeAttack(axe);
    assertEquals(javelin_receive.getOwner().getCurrentHitPoints(),100-(int)Math.round(axe.getPower()*1.5));
  }

  @Test
  public void testReceiveSpearAttack(){
    assertEquals(javelin_receive.getOwner().getCurrentHitPoints(),100);
    javelin_receive.receiveSpearAttack(spear);
    assertEquals(javelin_receive.getOwner().getCurrentHitPoints(),100-spear.getPower());
  }

  @Test
  public void testReceiveSwordAttack(){
    assertEquals(javelin_receive.getOwner().getCurrentHitPoints(),100);
    javelin_receive.receiveSwordAttack(sword);
    assertEquals(javelin_receive.getOwner().getCurrentHitPoints(),100- (sword.getPower()-20));
  }
}
