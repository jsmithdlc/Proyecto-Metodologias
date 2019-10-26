package observer;

import model.Tactician;
import model.map.Field;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MapHandler implements PropertyChangeListener {
    private Tactician tactician;


    public MapHandler(Tactician tactician){
        this.tactician = tactician;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        tactician.setMap((Field) evt.getNewValue());
    }

}
