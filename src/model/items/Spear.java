package model.items;

import model.units.*;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem implements AttackingItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipArcher(Archer archer){}

  @Override
  public void equipCleric(Cleric cleric) {}

  @Override
  public void equipFighter(Fighter fighter){}

  @Override
  public void equipHero(Hero hero){
    this.equipTo(hero);
  }

  @Override
  public void equipSwordMaster(SwordMaster swordmaster){}

  @Override
  public void equipSorcerer(Sorcerer sorcerer){}

  @Override
  public void attackItem(IEquipableItem item){
    item.receiveSpearAttack(this);
    item.counterAttack(this);
  }

  @Override
  public void counterAttack(IEquipableItem item){
    if(this.getOwner().checkAlive() && this.getOwner().inRange(item.getOwner())){
      item.receiveSpearAttack(this);
    }
  }

  @Override
  public void receiveAxeAttack(Axe axe){
    this.getOwner().receiveStrongAttack(axe);
  }

  @Override
  public void receiveSpearAttack(Spear spear){
    this.getOwner().receiveNormalAttack(spear);
  }

  @Override
  public void receiveSwordAttack(Sword sword){
    this.getOwner().receiveWeakAttack(sword);
  }

  @Override
  public boolean equals(Object obj){ return obj instanceof Spear && super.equals(obj);}
}
