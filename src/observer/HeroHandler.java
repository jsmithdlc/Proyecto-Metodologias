package observer;

import model.Tactician;
import model.units.Hero;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class defines a hero handler
 * <p>
 * a hero handler manages the removal of a player from the game if its hero dies
 *
 * @author Javier Smith
 * @since 2.0
 */
public class HeroHandler implements PropertyChangeListener {
    private Tactician tactician;

    /**
     * Creates a new handler
     * @param tactician
     *      tactician to be removed of game in case of losing hero
     */
    public HeroHandler(Tactician tactician) {
        this.tactician = tactician;
    }

    /**
     * fires a property change to the tactician ordering it to remove the hero from the map and ending its own
     * participation in the game
     * @param evt
     *      Hero which lost all its life
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        tactician.removeUnit((Hero) evt.getNewValue());
        tactician.endPlayer();
    }
}