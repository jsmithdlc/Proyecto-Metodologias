package model.units;

import model.items.Axe;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>Fighter</i> type unit.
 * <p>
 * A <i>Fighter</i> is a unit that <b>can only use axe type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  /**
   *  creates a new fighter
   * @param hitPoints
   *    initial and max hitpoints this unit can have
   * @param movement
   *    max ammount of cells this unit can move
   * @param location
   *    initial location of this unit
   * @param items
   *    items to put in inventory, if any
   */
  public Fighter(int hitPoints, final int movement, final Location location,
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
    item.equipFighter(this);
  }

  @Override
  public boolean equals(Object obj){ return obj instanceof Fighter && super.equals(obj);}
}
