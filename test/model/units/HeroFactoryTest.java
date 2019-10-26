package model.units;

import model.map.Location;
import model.unitsFactory.HeroFactory;
import model.unitsFactory.UnitFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeroFactoryTest extends AbstractTestUnitFactory{
    private HeroFactory heroFactory;

    @Override
    public void setTestUnitFactory(){
        heroFactory = new HeroFactory();
    };

    @Override
    public UnitFactory getTestUnitFactory(){
        return this.heroFactory;
    };

    @Test
    public void createUnitTest(){
        setTestUnitFactory();
        heroFactory.setMaxHitPoints(200);
        heroFactory.setMovement(2);
        heroFactory.setLocation(new Location(0,0));
        Hero hero = heroFactory.createUnit();
        assertEquals(new Hero(200,2,new Location(0,0)),hero);
    }

    @Test
    public void createNormalUnitTest(){
        setTestUnitFactory();
        assertEquals(new Hero(1000,1,null),heroFactory.createNormalUnit());
    }

    @Test
    public void createStrongUnitTest(){
        setTestUnitFactory();
        assertEquals(new Hero(1500,2,null),heroFactory.createStrongUnit());

    }
}