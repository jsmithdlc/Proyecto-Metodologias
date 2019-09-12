package model.units;

import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;
/**
 * This class represents a <i>Cleric</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use staff type weapons</b>.A cleric can only use staff type weapons, which means
 *  * that it can receive attacks but can't counter attack any of those. It heals other units
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {
  /**
   *  creates a new cleric
   * @param hitPoints
   *    initial and max hitpoints this unit can have
   * @param movement
   *    max ammount of cells this unit can move
   * @param location
   *    initial location of this unit
   * @param items
   *    items to put in inventory, if any
   */
  public Cleric(int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {item.equipCleric(this);}

  @Override
  public void attack(IUnit other){
    if(this.checkAlive() && other.checkAlive() && !this.equippedItem.equals(null)){
      if(this.inRange(other)){
        other.healUnit(this.getEquippedItem());
      }
    }
  }
}
