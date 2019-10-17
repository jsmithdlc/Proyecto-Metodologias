package model.units;

import model.items.Axe;
import model.items.Bow;
import model.map.Location;
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
        Hero hero = heroFactory.createUnit(new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2));
        assertEquals(new Hero(200,2,new Location(0,0),
                new Bow("arco", 10,3,6),
                new Axe("hacha",10,1,2)),hero);
    }
}