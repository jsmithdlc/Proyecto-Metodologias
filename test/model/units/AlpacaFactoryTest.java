package model.units;

import model.map.Location;
import model.unitsFactory.AlpacaFactory;
import model.unitsFactory.UnitFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlpacaFactoryTest extends AbstractTestUnitFactory{
    private AlpacaFactory alpacaFactory;

    @Override
    public void setTestUnitFactory(){
        alpacaFactory = new AlpacaFactory();
    };

    @Override
    public UnitFactory getTestUnitFactory(){
        return this.alpacaFactory;
    };

    @Test
    public void createUnitTest(){
        setTestUnitFactory();
        alpacaFactory.setMaxHitPoints(200);
        alpacaFactory.setMovement(2);
        alpacaFactory.setLocation(new Location(0,0));
        Alpaca alpaca = alpacaFactory.createUnit();
        assertEquals(new Alpaca(200,2,new Location(0,0)),alpaca);
    }

    @Test
    public void createNormalUnitTest(){
        setTestUnitFactory();
        Alpaca alpaca = alpacaFactory.createNormalUnit();
        assertEquals(new Alpaca(500,3,null),alpaca);
    }

    @Test
    public void createStrongUnitTest(){
        setTestUnitFactory();
        Alpaca alpaca = alpacaFactory.createStrongUnit();
        assertEquals(new Alpaca(750,5,null),alpaca);

    }
}
