package observer;

import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UnitHandler implements PropertyChangeListener {
    private Tactician tactician;

    public UnitHandler(Tactician tactician){
        this.tactician = tactician;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        tactician.removeUnit((IUnit) evt.getNewValue());
    }
}
