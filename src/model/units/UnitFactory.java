package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

public interface UnitFactory {

    void setMaxHitPoints(int maxHitPoints);

    int getMaxHitPoints();

    void setMovement(int movement);

    int getMovement();

    void setLocation(Location location);

    Location getLocation();

    IUnit createUnit(IEquipableItem... items);

    IUnit createNormalUnit(IEquipableItem... items);

    IUnit createStrongUnit(IEquipableItem... items);
}
