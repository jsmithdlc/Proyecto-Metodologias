package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TactiticanTest {
    private Tactician tactician;

    @BeforeEach
    public void setTactician(){
        tactician = new Tactician("Manolo");
    }

    @Test
    public void getNameTest(){
        assertEquals("Manolo",tactician.getName());
    }
}
