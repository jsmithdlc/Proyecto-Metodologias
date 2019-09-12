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
  private final int maxHitPoints;
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
                         final Location location, final int maxItems, IEquipableItem... items) {
    this.maxHitPoints = hitPoints;
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
  public void setCurrentHitPoints(int hitPoints){
    if(hitPoints >= this.maxHitPoints){
      this.currentHitPoints = maxHitPoints;
    }
    else{
      this.currentHitPoints = hitPoints;
    }
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
  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(Location targetLocation) {
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
    if ((this.equippedItem.getMinRange() <= other.getLocation().distanceTo(this.getLocation())) &&
            (this.equippedItem.getMaxRange() >= other.getLocation().distanceTo(this.getLocation()))) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void receiveWeakAttack(IEquipableItem item){
    if(item.getPower()>20) {
      this.currentHitPoints -= item.getPower() - 20;
      if(this.currentHitPoints < 0){
        this.currentHitPoints = 0;
      }
    }
  }

  @Override
  public void receiveNormalAttack(IEquipableItem item){
    this.currentHitPoints -= item.getPower();
    if(this.getCurrentHitPoints() < 0){
      this.currentHitPoints = 0;
    }
  }

  @Override
  public void receiveStrongAttack(IEquipableItem item){
    this.currentHitPoints -= (int)Math.round(item.getPower()*1.5);
    if(this.currentHitPoints < 0){
      this.currentHitPoints = 0;
    }
  }

  @Override
  public void healUnit(IEquipableItem item){
    this.currentHitPoints += item.getPower();
    if(this.getCurrentHitPoints()>this.maxHitPoints){
      this.currentHitPoints = this.maxHitPoints;
    }
  }


  @Override
  public void attack(IUnit other) {
    if (this.checkAlive() && other.checkAlive() && !(this.equippedItem==null)) {
      if (this.inRange(other)) {
        if(!(other.getEquippedItem()==null)){
          this.equippedItem.attackItem(other.getEquippedItem());
        }
        else{
          other.receiveNormalAttack(this.getEquippedItem());
        }
      }
    }
  }

  @Override
  public void addItem(IEquipableItem item){
    if(!(3-this.getItems().size() <= epsilon)){
      this.items.add(item);
    }
  }

  @Override
  public void removeItem(IEquipableItem item){
    if(this.getItems().contains(item)){
      this.items.remove(item);
    }
  }
}

