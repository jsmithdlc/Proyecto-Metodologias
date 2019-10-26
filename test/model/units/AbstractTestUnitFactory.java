package model.units;

import model.map.Location;
import model.unitsFactory.UnitFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractTestUnitFactory {

    @BeforeEach
    public void setUp(){
        setTestUnitFactory();
    }

    @Test
    public void setMaxHitPointsTest(){
        assertEquals(0,getTestUnitFactory().getMaxHitPoints());
        getTestUnitFactory().setMaxHitPoints(200);
        assertEquals(200,getTestUnitFactory().getMaxHitPoints());
    }

    @Test
    public void setMovementTest(){
        assertEquals(0,getTestUnitFactory().getMovement());
        getTestUnitFactory().setMovement(2);
        assertEquals(2,getTestUnitFactory().getMovement());
    }

    @Test
    public void setLocationTest(){
        assertNull(getTestUnitFactory().getLocation());
        getTestUnitFactory().setLocation(new Location(0,1));
        assertEquals(new Location(0,1),getTestUnitFactory().getLocation());
    }

    public abstract void setTestUnitFactory();

    public abstract UnitFactory getTestUnitFactory();
}

