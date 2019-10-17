package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  /**
   *  creates a new swordMaster
   * @param hitPoints
   *    initial and max hitpoints this unit can have
   * @param movement
   *    max ammount of cells this unit can move
   * @param location
   *    initial location of this unit
   * @param items
   *    items to put in inventory, if any
   */
  public SwordMaster(int hitPoints, final int movement, final Location location,
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
  public void equipItem(final IEquipableItem item) {
    item.equipSwordMaster(this);
  }

  @Override
  public boolean equals(Object obj){ return obj instanceof SwordMaster && super.equals(obj);}

  @Override
  public boolean ownerEquals(Object obj){ return obj instanceof SwordMaster && super.ownerEquals(obj);}
}
