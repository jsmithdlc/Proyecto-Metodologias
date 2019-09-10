package model.items;

import model.units.*;

/**
 * @author Ignacio Slater Muñoz
 * @since
 */
public class Bow extends AbstractItem {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  public void equipArcher(Archer archer){
    this.equipTo(archer);
  }

  public void equipCleric(Cleric cleric) {}

  public void equipFighter(Fighter fighter){}

  public void equipHero(Hero hero){}

  public void equipSwordMaster(SwordMaster swordmaster){}

  public int attack(IEquipableItem item){
    return this.getPower();
  }

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
