package model.items;

import model.units.*;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units nut cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  public void equipArcher(Archer archer){}

  public void equipCleric(Cleric cleric) {
    this.equipTo(cleric);
  }

  public int attack(IEquipableItem item){
    return -this.getPower();
  }

  public void equipFighter(Fighter fighter){}

  public void equipHero(Hero hero){}

  public void equipSwordMaster(SwordMaster swordmaster){}

  public int receiveAxeAttack(Axe axe){
    return axe.getPower();
  }

  public int receiveSpearAttack(Spear spear){
    return spear.getPower();
  }

  public int receiveSwordAttack(Sword sword){
    return sword.getPower();
  }
}
