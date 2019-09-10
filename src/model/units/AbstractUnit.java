package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  private double epsilon = 0.000001;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints the maximum amount of damage a unit can sustain
   * @param movement  the number of panels a unit can move
   * @param location  the current position of this unit on the map
   * @param maxItems  maximum amount of items this unit can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
                         final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }


  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
            && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  public boolean checkAlive() {
    if ((this.getCurrentHitPoints() - 0) <= epsilon) {
      return false;
    } else {
      return true;
    }
  }

  public boolean inRange(IUnit other) {
    if ((this.equippedItem.getMinRange() < other.getLocation().distanceTo(this.getLocation())) &&
            (this.equippedItem.getMaxRange() > other.getLocation().distanceTo(this.getLocation()))) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void receiveWeakAttack(IEquipableItem item){
    this.currentHitPoints -= item.getPower()-20;
  }

  @Override
  public void receiveNormalAttack(IEquipableItem item){
    this.currentHitPoints -= item.getPower();
  }

  @Override
  public void receiveStrongAttack(IEquipableItem item){
    this.currentHitPoints -= (int)Math.round(item.getPower()*1.5);
  }

  @Override
  public void healUnit(Staff staff){
    this.currentHitPoints += staff.getPower();
  }


  public void attack(IUnit other) {
    if (this.checkAlive() && other.checkAlive()) {
      if (!this.equippedItem.equals(null) && this.inRange(other)) {
        if(!other.getEquippedItem().equals(null)){
          this.equippedItem.attack(other.getEquippedItem());
          other.counterAttack(this);
        }
        else{
          other.receiveNormalAttack(this.equippedItem);
        }
      }
    }
  }

  public void counterAttack(IUnit other){
    if(this.checkAlive() && this.inRange(other)){
      this.equippedItem.attack(other.getEquippedItem());
    }
  }
}

