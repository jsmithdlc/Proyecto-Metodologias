package model.units;


import model.items.*;
import model.map.Field;
import org.junit.jupiter.api.Test;

/**
 * Interface that defines the common behaviour of all the test for the units classes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface ITestUnit {

  /**
   * Set up the game field
   */
  void setField();

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  void setTestUnit();

  void setTargetAlpaca();

  /**
   * Creates a set of testing weapons
   */
  void setWeapons();

  /**
   * Checks that the constructor works properly.
   */
  @Test
  void constructorTest();

  /**
   * @return the current unit being tested
   */
  IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  void equipFighterTest();


  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedItem(IEquipableItem item);

  /**
   * @return the test axe
   */
  Axe getAxe();

  @Test
  void equipSwordMasterTest();


  /**
   * @return the test sword
   */
  Sword getSword();

  @Test
  void equipHeroTest();

  /**
   * @return the test spear
   */
  Spear getSpear();

  @Test
  void equipClericTest();

  /**
   * @return the test staff
   */
  Staff getStaff();

  @Test
  void equipArcherTest();

  /**
   * @return the test bow
   */
  Bow getBow();

  /**
   * Checks if the unit moves correctly
   */
  @Test
  void testMovement();

  /**
   * @return the test field
   */
  Field getField();

  /**
   * @return the target Alpaca
   */
  Alpaca getTargetAlpaca();
}
