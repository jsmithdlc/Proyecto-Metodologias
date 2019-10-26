package observer;

import model.Tactician;
import model.units.Hero;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HeroHandler implements PropertyChangeListener {
    private Tactician tactician;

    public HeroHandler(Tactician tactician) {
        this.tactician = tactician;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        tactician.removeUnit((Hero) evt.getNewValue());
        tactician.endPlayer();
    }
}