package observer;

import controller.GameController;
import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class defines a player handler
 * <p>
 * a player handler informs the game controller of a player losing the game, so that it can properly remove it
 *
 * @author Javier Smith
 * @since 2.0
 */
public class PlayerHandler implements PropertyChangeListener {
    private GameController controller;

    /**
     * creates a player handler
     * @param controller
     *      game controller listening for possible player exits
     */
    public PlayerHandler(GameController controller){
        this.controller=controller;
    }

    /**
     * informs the controller of the exit of a player and the controller removes it from the game
     * @param evt
     *      name of the player who lost the game
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        controller.removeTactician((String) evt.getNewValue());
    }

}
