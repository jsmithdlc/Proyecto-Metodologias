package model.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldFactoryTest {
    FieldFactory fieldFactory;
    Random random;

    @BeforeEach
    void setUp(){
        fieldFactory = new FieldFactory();
    }

    @Test
    void setSeedTest(){
        assertEquals(-1,fieldFactory.getSeed());
        fieldFactory.setSeed(50);
        assertEquals(50,fieldFactory.getSeed());
    }

    @Test
    void createMapTest(){
        fieldFactory.setSeed(50);
        Field field = fieldFactory.createMap(3);
        Field field2 = new Field();
        field2.setSeed(50);
        field2.addCells(false, new Location(0, 0), new Location(0, 1), new Location(0, 2),
                new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
                new Location(2, 1), new Location(2, 2));
        assertEquals(field2,field);
    }

}
