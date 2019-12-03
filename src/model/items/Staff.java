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
public class Staff extends AbstractItem implements HarmlessItem {

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

  @Override
  public void equipArcher(Archer archer){}

  @Override
  public void equipCleric(Cleric cleric) {
    this.equipTo(cleric);
  }

  @Override
  public void equipFighter(Fighter fighter){}

  @Override
  public void equipHero(Hero hero){}

  @Override
  public void equipSwordMaster(SwordMaster swordmaster){}

  @Override
  public void equipSorcerer(Sorcerer sorcerer){}

  @Override
  public void counterAttack(IEquipableItem item){}

  @Override
  public void receiveAxeAttack(Axe axe){
    this.getOwner().receiveNormalAttack(axe);
  }

  @Override
  public void receiveSpearAttack(Spear spear){
    this.getOwner().receiveNormalAttack(spear);
  }

  @Override
  public void receiveSwordAttack(Sword sword){
    this.getOwner().receiveNormalAttack(sword);
  }

  @Override
  public void useItemOn(IUnit unit){
    unit.healUnit(this);
  }

  @Override
  public boolean equals(Object obj){ return obj instanceof Staff && super.equals(obj);}
}
