package model.units;

import model.items.Axe;
import model.items.Bow;
import model.map.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SorcererFactoryTest extends AbstractTestUnitFactory{
    private SorcererFactory sorcererFactory;

    @Override
    public void setTestUnitFactory(){
        sorcererFactory = new SorcererFactory();
    };

    @Override
    public UnitFactory getTestUnitFactory(){
        return this.sorcererFactory;
    };

    @Test
    public void createUnitTest(){
        setTestUnitFactory();
        sorcererFactory.setMaxHitPoints(200);
        sorcererFactory.setMovement(2);
        sorcererFactory.setLocation(new Location(0,0));
        Sorcerer sorcerer = sorcererFactory.createUnit(new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2));
        assertEquals(new Sorcerer(200,2,new Location(0,0),
                new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2)),sorcerer);
    }
}