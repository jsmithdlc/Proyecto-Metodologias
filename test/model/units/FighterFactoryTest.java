package model.units;

import model.map.Location;
import model.unitsFactory.FighterFactory;
import model.unitsFactory.UnitFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FighterFactoryTest extends AbstractTestUnitFactory{
    private FighterFactory fighterFactory;

    @Override
    public void setTestUnitFactory(){
        fighterFactory = new FighterFactory();
    };

    @Override
    public UnitFactory getTestUnitFactory(){
        return this.fighterFactory;
    };

    @Test
    public void createUnitTest(){
        setTestUnitFactory();
        fighterFactory.setMaxHitPoints(200);
        fighterFactory.setMovement(2);
        fighterFactory.setLocation(new Location(0,0));
        Fighter fighter = fighterFactory.createUnit();
        assertEquals(new Fighter(200,2,new Location(0,0)),fighter);
    }

    @Test
    public void createNormalUnitTest(){
        setTestUnitFactory();
        assertEquals(new Fighter(400,2,null),fighterFactory.createNormalUnit());
    }

    @Test
    public void createStrongUnitTest(){
        setTestUnitFactory();
        assertEquals(new Fighter(700,3,null),fighterFactory.createStrongUnit());

    }
}