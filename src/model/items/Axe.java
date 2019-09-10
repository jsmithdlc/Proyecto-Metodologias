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

  public void attack(IEquipableItem item){
    item.receiveAxeAttack(this);
  }

  public void receiveAxeAttack(Axe axe){
    this.getOwner().receiveNormalAttack(axe);
  }

  public void receiveSpearAttack(Spear spear){
    this.getOwner().receiveWeakAttack(spear);
  }

  public void receiveSwordAttack(Sword sword){
    this.getOwner().receiveStrongAttack(sword);
  }
}