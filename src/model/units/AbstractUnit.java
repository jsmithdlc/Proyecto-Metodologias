package model.units;

import static java.lang.Math.min;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Tactician;
import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;
import observer.UnitHandler;

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

  protected List<IEquipableItem> items = new ArrayList<>();
  private final int maxItems;
  private int currentHitPoints;
  private final int maxHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  protected Tactician tactician;
  private boolean used;
  protected double epsilon = 0.000001;
  private PropertyChangeSupport unitDeath = new PropertyChangeSupport(this);

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
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    for(IEquipableItem item:items){
      item.setOwner(this);
    }
    this.used = false;

  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public int getMaxHitPoints(){
    return maxHitPoints;
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
  public int getMaxItems(){return maxItems;}

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
    this.location.setUnit(this);
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
            && targetLocation.getUnit() == null) {
      this.location.removeUnit();
      setLocation(targetLocation);
    }
  }

  @Override
  public boolean checkAlive() {
    if ((this.getCurrentHitPoints() - 0) <= epsilon) {
      return false;
    } else {
      return true;
    }
  }

  @Override
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
      notifyIfDead();
    }
  }

  @Override
  public void receiveNormalAttack(IEquipableItem item){
    this.currentHitPoints -= item.getPower();
    notifyIfDead();
  }

  @Override
  public void receiveStrongAttack(IEquipableItem item){
    this.currentHitPoints -= (int)Math.round(item.getPower()*1.5);
    notifyIfDead();
  }

  @Override
  public void notifyIfDead(){
    if((this.currentHitPoints-0) <= epsilon){
      this.currentHitPoints = 0;
      unitDeath.firePropertyChange(new PropertyChangeEvent(this,"Unit death",null,this));
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
    if(!(this.getMaxItems()-this.getItems().size() <= epsilon) && item.getOwner()==null){
      item.setOwner(this);
      this.items.add(item);
    }
  }

  @Override
  public void removeItem(IEquipableItem item){
    if(this.getItems().contains(item)){
      if(item.equals(this.equippedItem)) {
        this.equippedItem = null;
      }
      item.setOwner(null);
      this.items.remove(item);
    }
  }

  @Override
  public void transferItem(IEquipableItem item, IUnit unit){
    if(((this.location.distanceTo(unit.getLocation())-1)<=epsilon) && this.items.contains(item) &&
            unit.getItems().size()<unit.getMaxItems()){
      this.removeItem(item);
      unit.addItem(item);
    }
  }

  @Override
  public void setTactician(Tactician tactician){
    UnitHandler unitHandler = new UnitHandler(tactician);
    unitDeath.addPropertyChangeListener(unitHandler);
    this.tactician = tactician;
  }

  @Override
  public void setUnitUsed(boolean used){
    this.used = used;
  }

  @Override
  public boolean getUnitUsed(){
    return this.used;
  }

  @Override
  public Tactician getTactician(){
    return this.tactician;
  }



  @Override
  public boolean equals(Object obj) {
    boolean cond1= obj instanceof IUnit && ((IUnit) obj).getItems().equals(items)
            && ((IUnit) obj).getCurrentHitPoints()==currentHitPoints
            && ((IUnit) obj).getMaxItems() == maxItems
            && ((IUnit) obj).getMovement() == movement;
    boolean cond2;
    if(cond1){
      if(((IUnit)obj).getLocation()==null){
        cond2 = this.location == null;
      }
      else{
        cond2 = ((IUnit)obj).getLocation().equals(this.location);
      }
      return cond1 && cond2;
    }
    else{
      return false;
    }
  }
}

