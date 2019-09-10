package model.items;

import model.units.*;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name     the name of the Axe
   * @param power    the damage of the axe
   * @param minRange the minimum range of the axe
   * @param maxRange the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  public void equipArcher(Archer archer) {
}

  public void equipCleric(Cleric cleric) {
  }

  public void equipFighter(Fighter fighter) {
    this.equipTo(fighter);
  }

  public void equipHero(Hero hero) {
  }

  public void equipSwordMaster(SwordMaster swordmaster) {
  }

  public int attack(IEquipableItem item){
    return item.receiveAxeAttack(this);
  }

  public int receiveAxeAttack(Axe axe){
    return axe.getPower();
  }

  public int receiveSpearAttack(Spear spear){
    return spear.getPower()-20;
  }

  public int receiveSwordAttack(Sword sword){
    return (int)Math.round(sword.getPower()*1.5);
  }
}