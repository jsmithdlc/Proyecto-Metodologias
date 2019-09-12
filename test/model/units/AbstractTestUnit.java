package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected ArrayList<IEquipableItem> weapons_list;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(10, 10, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 50, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.weapons_list = new ArrayList<IEquipableItem>(Arrays.asList(getAxe(),getSword(),getSpear(),getStaff(),getBow()));
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipFighterTest() {
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */

  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  @Test
  public void addItemTest(){
    getTestUnit().addItem(bow);
    assertEquals(true,getTestUnit().getItems().contains(bow));
  }

  @Test
  public void removeItemTest(){
    getTestUnit().addItem(bow);
    assertEquals(true,getTestUnit().getItems().contains(bow));
    getTestUnit().removeItem(bow);
    assertEquals(false,getTestUnit().getItems().contains(bow));
  }

  @Test
  public void addItemOverMaxTest(){
    getTestUnit().addItem(bow);
    getTestUnit().addItem(axe);
    getTestUnit().addItem(staff);
    assertEquals(3,getTestUnit().getItems().size());
    getTestUnit().addItem(sword);
    assertEquals(3,getTestUnit().getItems().size());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordMasterTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipHeroTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipClericTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipArcherTest() {
    checkEquippedItem(getBow());
  }

  @Test

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  @Override
  @Test
  public void inRangeTest(){
    Archer archer = new Archer(50,1,field.getCell(0,1));
    archer.equipItem(new Bow("Arco",2,2,3));
    assertEquals(true,archer.inRange(targetAlpaca));
    targetAlpaca.moveTo(field.getCell(0,0));
    assertEquals(false,archer.inRange(targetAlpaca));
    targetAlpaca.moveTo(field.getCell(2,2));
    assertEquals(true,archer.inRange(targetAlpaca));
    archer.moveTo(field.getCell(0,0));
    assertEquals(false,archer.inRange(targetAlpaca));
  }

  @Test
  public void testFatalBlow(){
    Fighter fighter_killer = new Fighter(30,4,field.getCell(0,0));
    fighter_killer.equipItem(axe);
    Hero hero_weak = new Hero(5,4,field.getCell(1,1));
    hero_weak.equipItem(spear);
    SwordMaster swordMaster_weak = new SwordMaster(5,4,field.getCell(0,1));
    swordMaster_weak.equipItem(sword);
    fighter_killer.attack(hero_weak);
    fighter_killer.attack(swordMaster_weak);
    fighter_killer.attack(targetAlpaca);
    assertEquals(false, hero_weak.checkAlive());
    assertEquals(false, swordMaster_weak.checkAlive());
    assertEquals(false, targetAlpaca.checkAlive());
    assertEquals(fighter_killer.getCurrentHitPoints(),30);
  }

  @Test
  public void testZeroAttack(){
    Hero hero = new Hero(30,4,field.getCell(0,0));
    hero.equipItem(spear);
    Fighter fighter = new Fighter(30,4,field.getCell(0,1));
    fighter.equipItem(axe);
    hero.attack(fighter);
    assertEquals(30,fighter.getCurrentHitPoints());
  }

}
