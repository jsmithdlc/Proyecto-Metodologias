package observer;

import model.Tactician;
import model.map.Field;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class defines a map handler
 * <p>
 * a map handler keeps a tactician updated with respect to the state of the game's map as it changes from turn to turn
 *
 * @author Javier Smith
 * @since 2.0
 */
public class MapHandler implements PropertyChangeListener {
    private Tactician tactician;

    /**
     * creates a map handler
     * @param tactician
     *      tactician to listen possible map changes
     */
    public MapHandler(Tactician tactician){
        this.tactician = tactician;
    }

    /**
     * updates the map of the tactician with respect to the new map passed as value
     * @param evt
     *      new map
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        tactician.setMap((Field) evt.getNewValue());
    }

}
