package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Field;
import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
  protected ArrayList<IEquipableItem> weapons_list;
  protected LightBook lightBook;
  protected DarkBook darkBook;
  protected SpiritBook spiritBook;
  protected ArrayList<IEquipableItem> books_list;
  protected Alpaca alpaca;
  protected Archer archer;
  protected Cleric cleric;
  protected Fighter fighter;
  protected Hero hero;
  protected SwordMaster swordMaster;
  protected Sorcerer sorcererLight;
  protected Sorcerer sorcererDark;
  protected Sorcerer sorcererSpirit;
  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;
  protected Field field;
  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  public void setUp() {
    setTestItems();
    setWrongRangeItem();
    setTestUnits();
    setItems();
    setField();
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
    this.weapons_list = new ArrayList<IEquipableItem>(Arrays.asList(axe,bow,spear,sword,staff));
    lightBook = new LightBook("Light Book",50,0,10);
    darkBook = new DarkBook("Dark Book",50,0,10);
    spiritBook = new SpiritBook("Spirit Book",50,0,10);
    this.books_list = new ArrayList<IEquipableItem>(Arrays.asList(lightBook,darkBook,spiritBook));
  }

  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
            new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
            new Location(2, 1), new Location(2, 2), new Location (1,3), new Location(1,4));
  }

  public void setWarriors(){
    alpaca = new Alpaca(100,5,field.getCell(0,0));
    archer = new Archer(100, 5, field.getCell(1,4));
    cleric = new Cleric(100,5, field.getCell(1,1));
    fighter = new Fighter(100, 5,field.getCell(1,2));
    hero = new Hero(100,5,field.getCell(2,1));
    swordMaster = new SwordMaster(100,5,field.getCell(2,0));
    sorcererLight = new Sorcerer(100,5,field.getCell(0,1));
    sorcererDark = new Sorcerer(100,5,field.getCell(1,0));
    sorcererSpirit = new Sorcerer(100,5,field.getCell(2,2));
  }

  public void equipWarriors(){
    archer.addItem(bow);
    archer.equipItem(bow);
    cleric.addItem(staff);
    cleric.equipItem(staff);
    fighter.addItem(axe);
    fighter.equipItem(axe);
    hero.addItem(spear);
    hero.equipItem(spear);
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    sorcererLight.addItem(lightBook);
    sorcererLight.equipItem(lightBook);
    sorcererDark.addItem(darkBook);
    sorcererDark.equipItem(darkBook);
    sorcererSpirit.addItem(spiritBook);
    sorcererSpirit.equipItem(spiritBook);
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
    unit.addItem(getTestItem());
    getTestItem().equipTo(unit);
    assertEquals(unit, getTestItem().getOwner());
  }

  /**
   * @return a unit that can equip the item being tested
   */

  @Test
  public void testItemReceiveLightAttack(){
    assertEquals(axe.getOwner().getCurrentHitPoints(),100);
    axe.receiveLightBookAttack(lightBook);
    assertEquals(axe.getOwner().getCurrentHitPoints(),100-((int)Math.round(lightBook.getPower()*1.5)));
  }

  @Test
  public void testItemReceiveDarkAttack(){
    assertEquals(bow.getOwner().getCurrentHitPoints(),100);
    bow.receiveDarkBookAttack(darkBook);
    assertEquals(bow.getOwner().getCurrentHitPoints(),100-((int)Math.round(darkBook.getPower()*1.5)));
  }

  @Test
  public void testItemReceiveSpiritAttack(){
    assertEquals(sword.getOwner().getCurrentHitPoints(),100);
    sword.receiveSpiritBookAttack(spiritBook);
    assertEquals(sword.getOwner().getCurrentHitPoints(),100-((int)Math.round(spiritBook.getPower()*1.5)));
  }

  @Test
  public void testBookReceiveItemAttack(){
    assertEquals(darkBook.getOwner().getCurrentHitPoints(),100);
    darkBook.receiveAxeAttack(axe);
    assertEquals(darkBook.getOwner().getCurrentHitPoints(),100-(int)Math.round(axe.getPower()*1.5));
  }

  @Test
  public void testBookReceiveSpearAttack(){
    assertEquals(lightBook.getOwner().getCurrentHitPoints(),100);
    lightBook.receiveSpearAttack(spear);
    assertEquals(lightBook.getOwner().getCurrentHitPoints(),100-(int)Math.round(spear.getPower()*1.5));
  }

  @Test
  public void testBookReceiveSwordAttack(){
    assertEquals(spiritBook.getOwner().getCurrentHitPoints(),100);
    spiritBook.receiveSwordAttack(sword);
    assertEquals(spiritBook.getOwner().getCurrentHitPoints(),100-(int)Math.round(sword.getPower()*1.5));
  }

  @Test
  public void testBookReceiveBowAttack(){
    assertEquals(spiritBook.getOwner().getCurrentHitPoints(),100);
    spiritBook.receiveBowAttack(bow);
    assertEquals(spiritBook.getOwner().getCurrentHitPoints(),100-(int)Math.round(bow.getPower()*1.5));
  }



  public abstract IUnit getTestUnit();
}
