package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.ArrayList;
import java.util.List;

public class AbstractUnitFactory implements UnitFactory {
    private int maxHitPoints;
    private int movement;
    private Location location;

    protected AbstractUnitFactory(){};

    @Override
    public void setMaxHitPoints(int maxHitPoints){
        this.maxHitPoints = maxHitPoints;
    }

    @Override
    public int getMaxHitPoints(){
        return this.maxHitPoints;
    }

    @Override
    public void setMovement(int movement){
        this.movement = movement;
    }

    @Override
    public int getMovement(){
        return this.movement;
    }

    @Override
    public void setLocation(Location location){
        this.location = location;
    }

    @Override
    public Location getLocation(){
        return this.location;
    }

    @Override
    public IUnit createUnit(IEquipableItem... items){
        return null;
    }

    @Override
    public IUnit createNormalUnit(IEquipableItem... items){
        return null;
    }

    @Override
    public IUnit createStrongUnit(IEquipableItem... items){
        return null;
    }
}
