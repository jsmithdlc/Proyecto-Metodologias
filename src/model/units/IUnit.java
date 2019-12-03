package model.units;

import java.util.List;

import model.Tactician;
import model.items.Bow;
import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   *
   * @return maximum hitpoints of this unit
   */
  int getMaxHitPoints();

  /**
   * set currentHiiPoints of this unit
   * @param hitPoints
   *    hitPoints to set
   */
  void setCurrentHitPoints(int hitPoints);
  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  int getMaxItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * checks if other Unit is in range of this attacking weapon
   * @param other
   *      other unit to attack
   * @return true if other is in range, false otherwise
   */
  boolean inRange(IUnit other);

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /**
   * Makes this unit receive weak attack from item
   * @param item
   *      item that attacks this unit
   */
  void receiveWeakAttack(IEquipableItem item);

  /**
   * Makes this unit receive normal attack from item
   * @param item
   *      item that attacks this unit
   */
  void receiveNormalAttack(IEquipableItem item);

  /**
   * Makes this unit receive strong attack from item
   * @param item
   *      item that attacks this unit
   */
  void receiveStrongAttack(IEquipableItem item);

  /**
   * Makes this unit receive healing from staff
   * @param item
   *      staff that heals this unit
   */
  void healUnit(IEquipableItem item);
  /**
   * implements attack from one unit to the other, modifying corresponding hitpoints
   * @param other
   *      The other unit being attacked
   */
  void attack(IUnit other);

  /**
   *
   * @return true if unit is alive, false otherwise
   */
  boolean checkAlive();

  /**
   * Notifies tactician if this unit is dead and adjusts currentHitPoints
   */
  void notifyIfDead();

  /**
   *  adds item to list of items of this
   * @param item
   *    item to add
   */
  void addItem(IEquipableItem item);

  /**
   * removes item from list of items of this
   * @param item
   *    item to remove
   */
  void removeItem(IEquipableItem item);

  /**
   *  transfers an item from this unit to another
   * @param item
   *    item to transfer
   * @param unit
   *    unit which receives item
   */
  void transferItem(IEquipableItem item, IUnit unit);

  /**
   * sets tactician to unit
   * @param tactician
   *    tactician to be set to unit
   */
  void setTactician(Tactician tactician);

  /**
   * returns the tactician of this unit
   * @return tactician of the unit
   */
  Tactician getTactician();

  /**
   * sets a boolean indicating if this unit has been used in the present turn
   * @param used
   *      indicator of state of unit's actions
   */
  void setUnitUsed(boolean used);

  /**
   * @return indicator of state of unit's actions
   */
  boolean getUnitUsed();

}
