package model.units;

import model.items.Axe;
import model.items.Bow;
import model.map.Location;
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
        Fighter fighter = fighterFactory.createUnit(new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2));
        assertEquals(new Fighter(200,2,new Location(0,0),
                new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2)),fighter);
    }
}