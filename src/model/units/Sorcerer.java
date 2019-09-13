package model.units;
import model.items.IEquipableItem;
import model.map.Location;
/**
 * This class represents a <i>Sorcerer</i> type unit.
 * <p>
 * A <i>Sorcerer</i> is a unit that <b>can only use book type weapons</b>.
 *
 * @author @author Javier Smith
 */
public class Sorcerer extends AbstractUnit {
    /**
     *  creates a new sorcerer
     * @param hitPoints
     *    initial and max hitpoints this unit can have
     * @param movement
     *    max ammount of cells this unit can move
     * @param location
     *    initial location of this unit
     * @param items
     *    items to put in inventory, if any
     */
    public Sorcerer(int hitPoints, final int movement, final Location location,
                IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    @Override
    public void equipItem(final IEquipableItem item) {
        item.equipSorcerer(this);
    }

    @Override
    public boolean equals(Object obj){ return obj instanceof Sorcerer && super.equals(obj);}
}
