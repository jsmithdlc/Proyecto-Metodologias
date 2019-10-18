package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.items.*;
import model.map.Field;
import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


public class TactiticanTest {
    private Tactician tactician;
    private Field map;
    private HeroFactory heroFactory;
    private FighterFactory fighterFactory;
    private SpearFactory spearFactory;
    private AxeFactory axeFactory;
    private SwordFactory swordFactory;

    public void setTactician(){
        tactician = new Tactician("Manolo");
    }

    public void setUnitsFactories(){
        heroFactory = new HeroFactory();
        fighterFactory = new FighterFactory();
        heroFactory.setMaxHitPoints(500);
        heroFactory.setMovement(1);
        fighterFactory.setMaxHitPoints(300);
        heroFactory.setMovement(2);
    }

    public void setItemsFactories(){
        spearFactory = new SpearFactory();
        spearFactory.setMinRange(1);
        spearFactory.setMaxRange(3);
        spearFactory.setPower(300);
        axeFactory = new AxeFactory();
        axeFactory.setMinRange(1);
        axeFactory.setMaxRange(2);
        axeFactory.setPower(200);
        swordFactory = new SwordFactory();
        swordFactory.setMinRange(1);
        swordFactory.setMaxRange(1);
        swordFactory.setPower(400);
    }

    public void setField(){
        map = new Field();
        map.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
                new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
                new Location(2, 1), new Location(2, 2));
    }

    @BeforeEach
    public void setUp(){
        setTactician();
        setUnitsFactories();
        setItemsFactories();
        setField();
    }

    @Test
    public void getNameTest(){
        assertEquals("Manolo",tactician.getName());
    }

    @Test
    public void addUnitTest(){
        heroFactory.setLocation(map.getCell(0,0));
        Hero hero = heroFactory.createUnit();
        assertEquals(new ArrayList<IUnit>(),tactician.getUnits());
        tactician.addUnit(hero);
        assertEquals(hero,tactician.getUnits().get(0));
    }

    @Test
    public void selectUnitInTest(){
        Hero hero = heroFactory.createUnit();
        hero.setLocation(map.getCell(0,0));
        tactician.addUnit(hero);
        tactician.setMap(map);
        assertEquals(null,tactician.getSelectedUnit());
        tactician.selectUnitIn(0,0);
        assertEquals(hero,tactician.getSelectedUnit());
    }

    @Test
    public void setMapTest(){
        assertNull(tactician.getMap());
        tactician.setMap(map);
        assertEquals(map,tactician.getMap());
    }

    @Test
    public void transferToTest(){
        swordFactory.setName("Espada cambio");
        Sword sword = swordFactory.createItem();
        Hero hero = heroFactory.createUnit(sword);
        Fighter fighter = fighterFactory.createUnit();
        hero.setLocation(map.getCell(0,0));
        fighter.setLocation(map.getCell(1,0));
        tactician.addUnit(hero);
        tactician.setMap(map);
        tactician.selectUnitIn(0,0);
        tactician.selectItem(0);
        assertEquals(new ArrayList<IEquipableItem>(), fighter.getItems());
        tactician.transferTo(1,0);
        map = tactician.getMap();
        assertEquals(sword,map.getCell(1,0).getUnit().getItems().get(0));
    }

    @Test
    public void equipItemTest(){
        spearFactory.setName("Lanza");
        Spear spear = spearFactory.createItem();
        Hero hero = heroFactory.createUnit(spear);
        hero.setLocation(map.getCell(0,0));
        tactician.addUnit(hero);
        assertNull(hero.getEquippedItem());
        tactician.setMap(map);
        tactician.selectUnitIn(0,0);
        tactician.equipItem(0);
        assertEquals(spear,tactician.getSelectedUnit().getEquippedItem());
    }





}
