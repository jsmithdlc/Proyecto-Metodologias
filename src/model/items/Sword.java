package model.items;

import model.units.*;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipArcher(Archer archer){}

  @Override
  public void equipCleric(Cleric cleric) {}

  @Override
  public void equipFighter(Fighter fighter){}

  @Override
  public void equipHero(Hero hero){}

  @Override
  public void equipSwordMaster(SwordMaster swordmaster){
    this.equipTo(swordmaster);
  }

  @Override
  public void equipSorcerer(Sorcerer sorcerer){}

  @Override
  public void attackItem(IEquipableItem item){
    item.receiveSwordAttack(this);
    item.counterAttack(this);
  }

  @Override
  public void counterAttack(IEquipableItem item){
    if(this.getOwner().checkAlive() && this.getOwner().inRange(item.getOwner())){
      item.receiveSwordAttack(this);
    }
  }

  @Override
  public void receiveAxeAttack(Axe axe){
    this.getOwner().receiveWeakAttack(axe);
  }

  @Override
  public void receiveSpearAttack(Spear spear){
    this.getOwner().receiveStrongAttack(spear);
  }

  @Override
  public void receiveSwordAttack(Sword sword){
    this.getOwner().receiveNormalAttack(sword);
  }

  @Override
  public boolean equals(Object obj){ return obj instanceof Sword && super.equals(obj);}
}
