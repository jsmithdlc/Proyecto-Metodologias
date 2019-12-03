package observer;

import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class defines a unit handler
 * <p>
 * a unit handler keeps a tactician notified if a certain unit is defeated, so that it can properly remove it
 *
 * @author Javier Smith
 * @since 2.0
 */
public class UnitHandler implements PropertyChangeListener {
    private Tactician tactician;

    /**
     * creates a unit handler
     * @param tactician
     *      tactician owner of the unit
     */
    public UnitHandler(Tactician tactician){
        this.tactician = tactician;
    }

    /**
     * if the unit dies, it orders the tactician who owns it to completely remove it from the game
     * @param evt
     *      unit to be removed
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        tactician.removeUnit((IUnit) evt.getNewValue());
    }
}
