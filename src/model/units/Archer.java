package model.units;

import model.items.Bow;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an <i>Archer</i> type unit.
 * <p>
 * This kind of unit <b>can only use bows</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Archer extends AbstractUnit {

  /**
   * Creates a new archer
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param position
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(final int hitPoints, final int movement, final Location position,
      final IEquipableItem... items) {
    super(hitPoints, movement, position, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   * <p>
   * The <i>Archer</i> can <b>only equip Bows</b>.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    if (item instanceof Bow) {
      equippedItem = item;
    }
  }
}
