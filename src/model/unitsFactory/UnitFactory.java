package model.unitsFactory;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

import java.util.List;

/**
 * This interface represents all units factories from which all units of the game can be easily created
 * <p>
 *  a unit factory is way of creating the game's units in an easy way. The units created could be highly customizable
 *  or with predefined values
 *
 * @author Javier Smith
 * @since 2.0
 */
public interface UnitFactory {

    /**
     * sets the initial HitPoints of the unit to be created
     * @param maxHitPoints
     */
    void setMaxHitPoints(int maxHitPoints);

    /**
     * @return the maximum HitPoints of te unit to be created
     */
    int getMaxHitPoints();

    /**
     * specifies the range of movement of the unit to be created
     * @param movement
     */
    void setMovement(int movement);

    /**
     * @return the range of movement of the unit to be created
     */
    int getMovement();

    /**
     * specifies where the unit to be created is to be set
     * @param location
     *      location where the to-be-created unit will be set
     */
    void setLocation(Location location);

    /**
     * @return the location where the to-be-created unit will be set
     */
    Location getLocation();

    /**
     * creates a unit based on the parameters defined by the setter methods
     * @param items
     *      items (if any) to be assigned to the unit to be created
     * @return the personalized unit created
     */
    IUnit createUnit(IEquipableItem... items);

    /**
     * creates a normal unit, which is moderately strong and has predefined characteristics specified in each of the
     * corresponding subclasses
     * @param items
     *      items (if any) to be assigned to the unit to be created
     * @return the normal unit created
     */
    IUnit createNormalUnit(IEquipableItem... items);

    /**
     * creates a strong unit, which is superior to the normal unit and has predefined characteristics specified in each
     * of the corresponding subclasses
     * @param items
     * @return
     */
    IUnit createStrongUnit(IEquipableItem... items);
}
