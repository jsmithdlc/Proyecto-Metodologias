package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Defines some common methods for all the items tests
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestItem {

  protected Axe axe;
  protected Bow bow;
  protected Spear spear;
  protected Sword sword;
  protected Staff staff;
  protected Alpaca alpaca;
  protected Archer archer;
  protected Cleric cleric;
  protected Fighter fighter;
  protected Hero hero;
  protected SwordMaster swordMaster;
  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;

  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  public void setUp() {
    setTestItems();
    setWrongRangeItem();
    setTestUnits();
    setItems();
    setWarriors();
    equipWarriors();
  }

  /**
   * Sets up a correctly implemented item that's going to be tested
   */
  public abstract void setTestItems();

  /**
   * Sets up an item with wrong ranges setted.
   */
  public abstract void setWrongRangeItem();

  /**
   * Sets the unit that will be equipped with the test item
   */
  public abstract void setTestUnits();

  /**
   * Sets all items for attack tests
   */
  public void setItems(){
    axe = new Axe("Axe",50,0,10);
    bow = new Bow("Bow",50,2,10);
    spear = new Spear("Spear",50,0,10);
    sword = new Sword("Sword",50,0,10);
    staff = new Staff("Staff",50,0,10);
  }

  public void setWarriors(){
    alpaca = new Alpaca(100,5,new Location(1, 0));
    archer = new Archer(100, 5, new Location(0, 1));
    cleric = new Cleric(100,5, new Location(1,1));
    fighter = new Fighter(100, 5, new Location(0, 0));
    hero = new Hero(100,5,new Location(0,2));
    swordMaster = new SwordMaster(100,5,new Location(2,0));
  }

  public void equipWarriors(){
    archer.equipItem(bow);
    cleric.equipItem(staff);
    fighter.equipItem(axe);
    hero.equipItem(spear);
    swordMaster.equipItem(sword);
  }
  /**
   * Checks that the tested item cannot have ranges outside of certain bounds.
   */
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() >= 0);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  public abstract IEquipableItem getWrongTestItem();

  /**
   * Tests that the constructor for the tested item works properly
   */
  @Test
  public void constructorTest() {
    assertEquals(getExpectedName(), getTestItem().getName());
    assertEquals(getExpectedBasePower(), getTestItem().getPower());
    assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
    assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
  }

  /**
   * @return the expected name of the item
   */
  public String getExpectedName() {
    return expectedName;
  }

  /**
   * @return the item being tested
   */
  public abstract IEquipableItem getTestItem();

  /**
   * @return the expected power of the Item
   */
  public int getExpectedBasePower() {
    return expectedPower;
  }

  /**
   * @return the expected minimum range of the item
   */
  public int getExpectedMinRange() {
    return expectedMinRange;
  }

  /**
   * @return the expected maximum range of the item
   */
  public int getExpectedMaxRange() {
    return expectedMaxRange;
  }

  /**
   * Checks that the Item can be correctly equipped to a unit
   */
  @Test
  public void equippedToTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    getTestItem().equipTo(unit);
    assertEquals(unit, getTestItem().getOwner());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  public abstract IUnit getTestUnit();
}
