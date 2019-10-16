package model.items;

import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between items which are not magic books.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public void equipTo(final IUnit unit) {
    if (this.getOwner() == unit) {
      unit.setEquippedItem(this);
    }
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  @Override
  public void setOwner(IUnit unit){this.owner = unit;}

  @Override
  public void receiveBowAttack(Bow bow){
    this.getOwner().receiveNormalAttack(bow);
  }
  @Override
  public void receiveDarkBookAttack(DarkBook darkBook){
    this.getOwner().receiveStrongAttack(darkBook);
  };

  @Override
  public void receiveLightBookAttack(LightBook lightBook){
    this.getOwner().receiveStrongAttack(lightBook);
  };

  @Override
  public void receiveSpiritBookAttack(SpiritBook spiritBook){
    this.getOwner().receiveStrongAttack(spiritBook);
  };

  @Override
  public boolean equals(Object obj) {
    return obj instanceof IEquipableItem && ((IEquipableItem) obj).getName().equals(name)
            && ((IEquipableItem) obj).getOwner() == owner
            && ((IEquipableItem) obj).getPower() == power
            && ((IEquipableItem) obj).getMaxRange() == maxRange
            && ((IEquipableItem) obj).getMinRange() == minRange;
  }
}
