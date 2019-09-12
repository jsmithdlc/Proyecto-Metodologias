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
  private Axe axe_receive;
  private Axe wrongAxe;
  private Fighter fighter_set;
  private Fighter fighter_receive;

  @Override
  public void setTestItems() {
    expectedName = "Common axe";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    axe_set = new Axe(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    axe_receive = new Axe("Axe",50,0,10);
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
  public void setTestUnits() {
    fighter_set = new Fighter(10, 5, new Location(0, 0));
    fighter_receive = new Fighter(100, 5, new Location(0, 0),axe_receive);
    fighter_receive.equipItem(axe_receive);
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
    return fighter_set;
  }

  @Test
  public void testReceiveAxeAttack(){
    assertEquals(axe_receive.getOwner().getCurrentHitPoints(),100);
    axe_receive.receiveAxeAttack(axe);
    assertEquals(axe_receive.getOwner().getCurrentHitPoints(),100-axe.getPower());
  }

  @Test
  public void testReceiveSpearAttack(){
    assertEquals(axe_receive.getOwner().getCurrentHitPoints(),100);
    axe_receive.receiveSpearAttack(spear);
    assertEquals(axe_receive.getOwner().getCurrentHitPoints(),100-(spear.getPower()-20));
  }

  @Test
  public void testReceiveSwordAttack(){
    assertEquals(axe_receive.getOwner().getCurrentHitPoints(),100);
    axe_receive.receiveSwordAttack(sword);
    assertEquals(axe_receive.getOwner().getCurrentHitPoints(),100-((int)Math.round(sword.getPower()*1.5)));
  }

  @Test
  public void testAxeAttackAndCounter(){
    axe.attackItem(axe_receive);
    assertEquals(axe_receive.getOwner().getCurrentHitPoints(),100-axe.getPower());
    assertEquals(axe.getOwner().getCurrentHitPoints(),100-axe_receive.getPower());
    axe.getOwner().setCurrentHitPoints(100);
    axe.attackItem(bow);
    assertEquals(bow.getOwner().getCurrentHitPoints(),100-axe.getPower());
    assertEquals(axe.getOwner().getCurrentHitPoints(),100-bow.getPower());
    axe.getOwner().setCurrentHitPoints(100);
    axe.attackItem(spear);
    assertEquals(spear.getOwner().getCurrentHitPoints(),100-(int)Math.round(axe.getPower()*1.5));
    assertEquals(axe.getOwner().getCurrentHitPoints(),100-(spear.getPower()-20));
    axe.getOwner().setCurrentHitPoints(100);
    axe.attackItem(staff);
    assertEquals(staff.getOwner().getCurrentHitPoints(),100-axe.getPower());
    assertEquals(axe.getOwner().getCurrentHitPoints(),100);
    axe.getOwner().setCurrentHitPoints(100);
    axe.attackItem(sword);
    assertEquals(sword.getOwner().getCurrentHitPoints(),100-(axe.getPower()-20));
    assertEquals(axe.getOwner().getCurrentHitPoints(),100-(int)Math.round(sword.getPower()*1.5));
  }
}