package model.items;

import model.itemsFactory.ItemFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractTestItemFactory {

    @BeforeEach
    public void setUp(){
        setTestItemFactory();
    }

    @Test
    public void setNameTest(){
        assertNull(getTestItemFactory().getName());
        getTestItemFactory().setName("Manolo");
        assertEquals("Manolo",  getTestItemFactory().getName());
    }

    @Test
    public void setPowerTest(){
        assertEquals(0,getTestItemFactory().getPower());
        getTestItemFactory().setPower(400);
        assertEquals(400,  getTestItemFactory().getPower());
    }

    @Test
    public void setMinRangeTest(){
        assertEquals(0,getTestItemFactory().getMinRange());
        getTestItemFactory().setMinRange(2);
        assertEquals(2,getTestItemFactory().getMinRange());
    }

    @Test
    public void setMaxRangeTest(){
        assertEquals(0,getTestItemFactory().getMaxRange());
        getTestItemFactory().setMaxRange(10);
        assertEquals(10,getTestItemFactory().getMaxRange());
    }

    public abstract void setTestItemFactory();

    public abstract ItemFactory getTestItemFactory();
}
