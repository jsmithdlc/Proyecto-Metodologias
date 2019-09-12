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

  @Override
  public void equipCleric(Cleric cleric) {}

  @Override
  public void equipFighter(Fighter fighter){}

  @Override
  public void equipHero(Hero hero){}

  @Override
  public void equipSwordMaster(SwordMaster swordmaster){}

  @Override
  public void equipSorcerer(Sorcerer sorcerer){}

  @Override
  public void attackItem(IEquipableItem item){
    item.receiveBowAttack(this);
    item.counterAttack(this);
  }

  @Override
  public void counterAttack(IEquipableItem item){
    if(this.getOwner().checkAlive() && this.getOwner().inRange(item.getOwner())){
      item.receiveBowAttack(this);
    }
  }

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
  public boolean equals(Object obj){ return obj instanceof Bow && super.equals(obj);}
}
