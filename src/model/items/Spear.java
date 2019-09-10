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
public class Spear extends AbstractItem {

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

  public void equipArcher(Archer archer){}

  public void equipCleric(Cleric cleric) {}

  public void equipFighter(Fighter fighter){}

  public void equipHero(Hero hero){
    this.equipTo(hero);
  }

  public void equipSwordMaster(SwordMaster swordmaster){}

  public int attack(IEquipableItem item){
    return item.receiveSpearAttack(this);
  }

  public int receiveAxeAttack(Axe axe){
    return (int)Math.round(axe.getPower()*1.5);
  }

  public int receiveSpearAttack(Spear spear){
    return spear.getPower();
  }

  public int receiveSwordAttack(Sword sword){
    return sword.getPower()-20;
  }
}
