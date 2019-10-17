package model.units;

import model.items.IEquipableItem;
import model.items.Spear;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   *  creates a new hero
   * @param hitPoints
   *    initial and max hitpoints this unit can have
   * @param movement
   *    max ammount of cells this unit can move
   * @param location
   *    initial location of this unit
   * @param items
   *    items to put in inventory, if any
   */
  public Hero(int hitPoints, final int movement, final Location location,
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
    item.equipHero(this);
  }

  @Override
  public boolean equals(Object obj){ return obj instanceof Hero && super.equals(obj);}

  @Override
  public boolean ownerEquals(Object obj){ return obj instanceof Hero && super.ownerEquals(obj);}
}
