package model;

import model.items.*;
import model.itemsFactory.AxeFactory;
import model.itemsFactory.SpearFactory;
import model.itemsFactory.SwordFactory;
import model.map.Field;
import model.map.Location;
import model.units.*;
import model.unitsFactory.FighterFactory;
import model.unitsFactory.HeroFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


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
    void addSelectPlaceUnitTest(){
        Hero hero = heroFactory.createUnit();
        assertEquals(new ArrayList<IUnit>(),tactician.getUnits());
        tactician.setMap(this.map);
        tactician.addUnit(hero);
        tactician.selectMyUnit(0);
        assertEquals(hero,tactician.getSelectedUnit());
        tactician.placeUnit(0,0);
        assertEquals(hero,tactician.getUnits().get(0));
        assertEquals(hero,tactician.getMap().getCell(0,0).getUnit());
    }

    @Test
    public void selectUnitInTest(){
        Hero hero = heroFactory.createUnit();
        tactician.setMap(map);
        tactician.addUnit(hero);
        tactician.selectMyUnit(0);
        tactician.placeUnit(0,0);
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
        fighter.setLocation(map.getCell(1,0));
        tactician.setMap(map);
        tactician.addUnit(hero);
        tactician.selectMyUnit(0);
        tactician.placeUnit(0,0);
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
        assertNull(hero.getEquippedItem());
        tactician.setMap(map);
        tactician.addUnit(hero);
        tactician.selectMyUnit(0);
        tactician.placeUnit(0,0);
        tactician.selectUnitIn(0,0);
        tactician.equipItem(0);
        assertEquals(spear,tactician.getSelectedUnit().getEquippedItem());
    }


    @Test
    public void useItemOnTest(){
        Axe axe = axeFactory.createNormalItem();
        Fighter fighter = fighterFactory.createStrongUnit(axe);
        tactician.setMap(map);
        tactician.addUnit(fighter);
        tactician.selectMyUnit(0);
        tactician.equipItem(0);
        tactician.placeUnit(0,0);
        Field map1 = tactician.getMap();

        Tactician tactician2 = new Tactician("Juan");
        Hero hero = heroFactory.createStrongUnit();
        tactician2.setMap(map);
        tactician2.addUnit(hero);
        tactician2.selectMyUnit(0);
        tactician2.placeUnit(1,0);
        Field map2 = tactician2.getMap();
        assertEquals(1500,map2.getCell(1,0).getUnit().getCurrentHitPoints());

        tactician.setMap(map2);
        tactician.selectUnitIn(0,0);
        tactician.useItemOn(1,0);
        Field mapFinal = tactician.getMap();

        assertEquals(1450,mapFinal.getCell(1,0).getUnit().getCurrentHitPoints());
    }

    @Test
    public void moveToTest(){
        Fighter fighter = fighterFactory.createStrongUnit();
        tactician.setMap(map);
        tactician.addUnit(fighter);
        tactician.selectMyUnit(0);
        tactician.placeUnit(0,0);
        assertEquals(fighter,tactician.getMap().getCell(0,0).getUnit());
        tactician.selectUnitIn(0,0);
        tactician.moveUnitTo(1,0);
        assertNull(tactician.getMap().getCell(0,0).getUnit());
        assertEquals(fighter,tactician.getMap().getCell(1,0).getUnit());
    }

    @Test
    public void removeUnitTest(){
        fighterFactory.setMaxHitPoints(40);
        Hero hero = heroFactory.createStrongUnit(spearFactory.createNormalItem());
        Fighter fighter = fighterFactory.createUnit();
        Fighter fighter2 = fighterFactory.createStrongUnit();
        tactician.setMap(map);
        tactician.addUnit(hero);
        tactician.selectMyUnit(0);
        tactician.placeUnit(0,0);

        Tactician otherPlayer = new Tactician("Juan");
        otherPlayer.setMap(tactician.getMap());
        otherPlayer.addUnit(fighter);
        otherPlayer.addUnit(fighter2);
        otherPlayer.selectMyUnit(0);
        otherPlayer.placeUnit(0,1);

        tactician.setMap(otherPlayer.getMap());
        assertEquals(fighter,tactician.getMap().getCell(0,1).getUnit());
        tactician.selectUnitIn(0,0);
        tactician.equipItem(0);
        assertEquals(2,otherPlayer.getUnits().size());
        assertEquals(fighter,tactician.getMap().getCell(0,1).getUnit());
        tactician.useItemOn(0,1);
        assertEquals(1,otherPlayer.getUnits().size());
        assertEquals(null,tactician.getMap().getCell(0,1).getUnit());
    }

    @Test
    public void resetMovesTest(){
        Fighter fighter1 = fighterFactory.createNormalUnit();
        Fighter fighter2 = fighterFactory.createStrongUnit();
        Hero hero = heroFactory.createNormalUnit();
        tactician.addUnit(fighter1);
        tactician.addUnit(fighter2);
        tactician.addUnit(hero);
        for(IUnit unit: tactician.getUnits()){
            unit.setUnitUsed(true);
            assertTrue(unit.getUnitUsed());
        }
        tactician.resetMoves();
        for(IUnit unit: tactician.getUnits()){
            assertFalse(unit.getUnitUsed());
        }
    }

}
