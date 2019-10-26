package observer;

import controller.GameController;
import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerHandler implements PropertyChangeListener {
    private GameController controller;

    public PlayerHandler(GameController controller){
        this.controller=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        controller.removeTactician((String) evt.getNewValue());
    }

}
