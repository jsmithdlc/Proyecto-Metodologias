package model.units;

import model.map.Location;
import model.unitsFactory.SwordMasterFactory;
import model.unitsFactory.UnitFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwordMasterFactoryTest extends AbstractTestUnitFactory{
    private SwordMasterFactory swordMasterFactory;

    @Override
    public void setTestUnitFactory(){
        swordMasterFactory = new SwordMasterFactory();
    };

    @Override
    public UnitFactory getTestUnitFactory(){
        return this.swordMasterFactory;
    };

    @Test
    public void createUnitTest(){
        setTestUnitFactory();
        swordMasterFactory.setMaxHitPoints(200);
        swordMasterFactory.setMovement(2);
        swordMasterFactory.setLocation(new Location(0,0));
        SwordMaster swordMaster = swordMasterFactory.createUnit();
        assertEquals(new SwordMaster(200,2,new Location(0,0)),swordMaster);
    }

    @Test
    public void createNormalUnitTest(){
        setTestUnitFactory();
        assertEquals(new SwordMaster(400,1,null),swordMasterFactory.createNormalUnit());
    }

    @Test
    public void createStrongUnitTest(){
        setTestUnitFactory();
        assertEquals(new SwordMaster(600,3,null),swordMasterFactory.createStrongUnit());

    }
}