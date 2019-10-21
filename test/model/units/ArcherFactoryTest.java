package model.units;

import model.items.Axe;
import model.items.Bow;
import model.map.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArcherFactoryTest extends AbstractTestUnitFactory{
    private ArcherFactory archerFactory;

    @Override
    public void setTestUnitFactory(){
        archerFactory = new ArcherFactory();
    };

    @Override
    public UnitFactory getTestUnitFactory(){
        return this.archerFactory;
    };

    @Test
    public void createUnitTest(){
        setTestUnitFactory();
        archerFactory.setMaxHitPoints(200);
        archerFactory.setMovement(2);
        archerFactory.setLocation(new Location(0,0));
        Archer archer = archerFactory.createUnit();
        assertEquals(new Archer(200,2,new Location(0,0)),archer);
    }

    @Test
    public void createNormalUnitTest(){
        setTestUnitFactory();
        assertEquals(new Archer(300,2,null),archerFactory.createNormalUnit());
    }

    @Test
    public void createStrongUnitTest(){
        setTestUnitFactory();
        assertEquals(new Archer(500,3,null),archerFactory.createStrongUnit());

    }
}
