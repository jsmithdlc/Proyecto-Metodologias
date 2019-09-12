package model.items;

import model.units.*;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   *  sets owner of item
   * @param unit
   *     unit to set owner
   */
  void setOwner(IUnit unit);

  /**
   *  method to equip archer with this
   * @param archer
   *    archer to equip
   */
  void equipArcher(Archer archer);

  /**
   *  method to equip cleric with this
   * @param cleric
   *      cleric to equip
   */
  void equipCleric(Cleric cleric);

  /**
   *  method to equip fighter with this
   * @param fighter
   *    fighter to equip
   */
  void equipFighter(Fighter fighter);

  /**
   *  method to equip hero with this
   * @param hero
   *    hero to equip
   */
  void equipHero(Hero hero);

  /**
   *  method to equip swordMaster with this
   * @param swordmaster
   *    swordMaster to equip
   */
  void equipSwordMaster(SwordMaster swordmaster);

  /**
   * method to equip sorcerer with this
   * @param sorcerer
   *     sorcerer to equip
   */
  void equipSorcerer(Sorcerer sorcerer);

  /**
   * attack other item with this, begins combat
   * @param item
   *     item to attack
   */
  void attackItem(IEquipableItem item);

  /**
   *  counter-attack other item with this, ends combat
   * @param item
   *    item to counterAttack
   */
  void counterAttack(IEquipableItem item);

  /**
   *  this receives axe attack
   * @param axe
   *    axe which attacks
   */
  void receiveAxeAttack(Axe axe);

  /**
   * this receives sword attack
   * @param sword
   *    sword which attacks
   */
  void receiveSwordAttack(Sword sword);

  /**
   * this receives spear attack
   * @param spear
   *    spear which attacks
   */
  void receiveSpearAttack(Spear spear);

  /**
   * this receives bow attack
   * @param bow
   */
  void receiveBowAttack(Bow bow);

  /**
   * this receives dark book attack
   * @param darkBook
   *    dark book which attacks
   */
  void receiveDarkBookAttack(DarkBook darkBook);

  /**
   * this receives light book attack
   * @param lightBook
   *    light book which attacks
   */
  void receiveLightBookAttack(LightBook lightBook);

  /**
   * this receives spirit book attack
   * @param spiritBook
   *    spirit book which attacks
   */
  void receiveSpiritBookAttack(SpiritBook spiritBook);
}
