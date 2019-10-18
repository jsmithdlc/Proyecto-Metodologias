package model.units;

import model.items.Axe;
import model.items.Bow;
import model.map.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClericFactoryTest extends AbstractTestUnitFactory{
    private ClericFactory clericFactory;

    @Override
    public void setTestUnitFactory(){
        clericFactory = new ClericFactory();
    };

    @Override
    public UnitFactory getTestUnitFactory(){
        return this.clericFactory;
    };

    @Test
    public void createUnitTest(){
        setTestUnitFactory();
        clericFactory.setMaxHitPoints(200);
        clericFactory.setMovement(2);
        clericFactory.setLocation(new Location(0,0));
        Cleric cleric = clericFactory.createUnit(new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2));
        assertEquals(new Cleric(200,2,new Location(0,0),
                new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2)),cleric);
    }

    @Test
    public void createNormalUnitTest(){
        setTestUnitFactory();
        assertEquals(new Cleric(250,2,null),clericFactory.createNormalUnit());
    }

    @Test
    public void createStrongUnitTest(){
        setTestUnitFactory();
        assertEquals(new Cleric(400,4,null),clericFactory.createStrongUnit());

    }
}