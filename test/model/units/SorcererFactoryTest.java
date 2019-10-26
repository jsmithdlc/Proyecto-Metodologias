package model.units;

import model.map.Location;
import model.unitsFactory.SorcererFactory;
import model.unitsFactory.UnitFactory;
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
        Sorcerer sorcerer = sorcererFactory.createUnit();
        assertEquals(new Sorcerer(200,2,new Location(0,0)),sorcerer);
    }

    @Test
    public void createNormalUnitTest(){
        setTestUnitFactory();
        assertEquals(new Sorcerer(200,2,null),sorcererFactory.createNormalUnit());
    }

    @Test
    public void createStrongUnitTest(){
        setTestUnitFactory();
        assertEquals(new Sorcerer(400,4,null),sorcererFactory.createStrongUnit());

    }
}