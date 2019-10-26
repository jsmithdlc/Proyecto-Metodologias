package controller;

import java.util.*;
import java.util.stream.IntStream;
import model.Tactician;
import model.items.*;
import model.itemsFactory.AxeFactory;
import model.itemsFactory.SpearFactory;
import model.map.Field;
import model.map.FieldFactory;
import model.units.Fighter;
import model.unitsFactory.FighterFactory;
import model.units.Hero;
import model.unitsFactory.HeroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

    private GameController controller;
    private long randomSeed;
    private List<String> testTacticians;
    private HeroFactory heroFactory = new HeroFactory();
    private FighterFactory fighterFactory = new FighterFactory();
    private SpearFactory spearFactory = new SpearFactory();
    private AxeFactory axeFactory = new AxeFactory();

    @BeforeEach
    void setUp() {
        // Se define la semilla como un número aleatorio para generar variedad en los tests
        randomSeed = new Random().nextLong();
        controller = new GameController(4, 2);
        controller.setSeed(randomSeed);
        controller.generateGameMap();
        controller.setInitTurns();
        testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
    }

    @Test
    void getTacticians() {
        List<Tactician> tacticians = controller.getTacticians();
        assertEquals(4, tacticians.size());
        for (int i = 0; i < tacticians.size(); i++) {
            assertEquals("Player " + i, tacticians.get(i).getName());
        }
    }

    @Test
    void getGameMap() {
        Field gameMap = controller.getGameMap();
        assertEquals(2, gameMap.getSize()); // getSize deben definirlo
        assertTrue(controller.getGameMap().isConnected());
        // Para testear funcionalidades que dependen de valores aleatorios se hacen 2 cosas:
        //  - Comprobar las invariantes de las estructuras que se crean (en este caso que el mapa tenga
        //    las dimensiones definidas y que sea conexo.
        Random testRandom = new Random(randomSeed);
        //  - Setear una semilla para el generador de números aleatorios. Hacer esto hace que la
        //    secuencia de números generada sea siempre la misma, así pueden predecir los
        //    resultados que van a obtener.
        //    Hay 2 formas de hacer esto en Java, le pueden pasar el seed al constructor de Random, o
        //    usar el método setSeed de Random.
        FieldFactory fieldFactory = new FieldFactory();
        fieldFactory.setSeed(randomSeed);
        Field map = fieldFactory.createMap(2);
        assertEquals(map, controller.getGameMap());
        //  ESTO ÚLTIMO NO ESTÁ IMPLEMENTADO EN EL MAPA, ASÍ QUE DEBEN AGREGARLO (!)
    }
    @Test
    void getTurnOwner() {
        Map<String,Tactician> tacticians = new HashMap<>();
        tacticians.put("Player 0", new Tactician("Player 0"));
        tacticians.put("Player 1", new Tactician("Player 1"));
        tacticians.put("Player 2", new Tactician("Player 2"));
        tacticians.put("Player 3", new Tactician("Player 3"));
        List<String> tacticians_shuffled = new ArrayList<>(testTacticians);
        Collections.shuffle(tacticians_shuffled,new Random(this.randomSeed));
        assertEquals(tacticians.get(tacticians_shuffled.get(0)),controller.getTurnOwner());
        //  En este caso deben hacer lo mismo que para el mapa
    }

    @Test
    void getRoundNumber() {
        controller.initGame(10);
        for (int i = 1; i < 10; i++) {
            assertEquals(i, controller.getRoundNumber());
            for (int j = 0; j < 4; j++) {
                controller.endTurn();
            }
        }
    }

    @Test
    void getMaxRounds() {
        Random randomTurnSequence = new Random();
        IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
            controller.initGame(nextInt);
            System.out.println(nextInt);
            assertEquals(nextInt, controller.getMaxRounds());
            System.out.println(nextInt);
        });
        controller.initEndlessGame();
        assertEquals(-1, controller.getMaxRounds());
    }

    @Test
    void endTurn() {
        Tactician firstPlayer = controller.getTurnOwner();
        System.out.println(firstPlayer.getName());
        // Nuevamente, para determinar el orden de los jugadores se debe usar una semilla
        Tactician secondPlayer = controller.getNextTurnOwner();
        System.out.println(secondPlayer.getName());
        assertNotEquals(secondPlayer.getName(), firstPlayer.getName());
        controller.endTurn();
        System.out.println(controller.getTurnOwner().getName());
        assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
        assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
    }

    @Test
    void removeTactician() {
        assertEquals(4, controller.getTacticians().size());
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

        controller.removeTactician("Player 0");
        assertEquals(3, controller.getTacticians().size());
        controller.getTacticians().forEach(tactician -> assertNotEquals("Player 0", tactician));
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

        controller.removeTactician("Player 5");
        assertEquals(3, controller.getTacticians().size());
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
    }

    @Test
    void getWinners() {
        controller.initGame(2);
        IntStream.range(0, 8).forEach(i -> controller.endTurn());
        assertEquals(4, controller.getWinners().size());
        controller.getWinners()
                .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

        controller.initGame(2);
        IntStream.range(0, 4).forEach(i -> controller.endTurn());
        assertNull(controller.getWinners());
        controller.removeTactician("Player 0");
        controller.removeTactician("Player 2");
        IntStream.range(0, 2).forEach(i -> controller.endTurn());
        List<String> winners = controller.getWinners();
        assertEquals(2, winners.size());
        assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

        controller.initEndlessGame();
        for (int i = 0; i < 3; i++) {
            System.out.println(controller.getTacticians().size());
            assertNull(controller.getWinners());
            controller.removeTactician("Player " + i);
        }
        assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
    }

    // Desde aquí en adelante, los tests deben definirlos completamente ustedes
    @Test
    void getSelectedUnit() {
        Hero hero = heroFactory.createNormalUnit(spearFactory.createNormalItem("Lanza"));
        controller.addUnit(hero);
        controller.selectMyUnit(0);
        assertEquals(hero,controller.getSelectedUnit());
        controller.getTurnOwner().setMap(controller.getGameMap());
        controller.placeUnit(0,0);
        assertNull(controller.getSelectedUnit());
        controller.selectUnitIn(0,0);
        assertEquals(hero,controller.getSelectedUnit());
    }

    @Test
    void placeUnitTest(){
        Hero hero = heroFactory.createNormalUnit();
        controller.addUnit(hero);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);
        assertNull(controller.getSelectedUnit());
        assertEquals(hero,controller.getTurnOwner().getMap().getCell(0,0).getUnit());
        assertEquals(hero,controller.getNextTurnOwner().getMap().getCell(0,0).getUnit());
    }

    @Test
    void selectUnitIn() {
        Fighter fighter = fighterFactory.createNormalUnit();
        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);
        assertNull(controller.getSelectedUnit());
        controller.selectUnitIn(0,0);
        assertEquals(fighter,controller.getSelectedUnit());
    }

    @Test
    void selectWrongUnitTest(){
        Fighter fighter = fighterFactory.createNormalUnit();
        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);

        Hero hero = heroFactory.createNormalUnit();
        Tactician player2 = controller.getNextTurnOwner();
        player2.addUnit(hero);
        player2.selectMyUnit(0);
        player2.placeUnit(1,0);

        controller.selectUnitIn(1,0);
        assertNull(controller.getSelectedUnit());

        controller.endTurn();
        assertEquals(player2,controller.getTurnOwner());
        controller.selectUnitIn(1,0);
        assertEquals(hero,controller.getSelectedUnit());
    }

    @Test
    void getItems() {
        Axe axe = axeFactory.createNormalItem("hacha");
        Spear spear = spearFactory.createStrongItem("lanza");
        Fighter fighter = fighterFactory.createStrongUnit(axe,spear);
        List<IEquipableItem> realItems = new ArrayList<>();
        realItems.add(axe);
        realItems.add(spear);
        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        List<IEquipableItem> items = controller.getItems();
        assertEquals(realItems,items);
    }

    @Test
    void equipItem() {
        Axe axe = axeFactory.createNormalItem("hacha");
        Spear spear = spearFactory.createStrongItem("lanza");
        Fighter fighter = fighterFactory.createStrongUnit(axe,spear);
        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        controller.equipItem(0);
        assertEquals(axe,controller.getSelectedUnit().getEquippedItem());
        controller.equipItem(1);
        assertEquals(axe,controller.getSelectedUnit().getEquippedItem());
    }

    @Test
    void selectItem() {
        Axe axe = axeFactory.createStrongItem("hacha");
        Fighter fighter = fighterFactory.createStrongUnit(axe);
        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        assertNull(controller.getTurnOwner().getSelectedItem());
        controller.selectItem(0);
        assertEquals(axe,controller.getTurnOwner().getSelectedItem());
    }

    @Test
    void useItemOn() {
        Tactician player1 = controller.getTurnOwner();
        Tactician player2 = controller.getNextTurnOwner();

        Axe axe = axeFactory.createNormalItem("hacha");
        Fighter fighter = fighterFactory.createStrongUnit(axe);
        player1.addUnit(fighter);
        player1.selectMyUnit(0);
        player1.equipItem(0);
        player1.placeUnit(0,0);

        Hero hero = heroFactory.createStrongUnit();
        player2.addUnit(hero);
        player2.selectMyUnit(0);
        player2.placeUnit(1,0);
        assertEquals(1500,controller.getGameMap().getCell(1,0).getUnit().getCurrentHitPoints());

        controller.selectUnitIn(0,0);
        controller.useItemOn(1,0);
        assertEquals(1450,controller.getGameMap().getCell(1,0).getUnit().getCurrentHitPoints());
    }

    @Test
    void giveItemTo() {
        spearFactory.setName("Espada cambio");
        Spear spear = spearFactory.createItem();
        Hero hero = heroFactory.createUnit(spear);
        Fighter fighter = fighterFactory.createUnit();
        controller.addUnit(hero);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);
        controller.addUnit(fighter);
        controller.selectMyUnit(1);
        controller.placeUnit(1,0);
        controller.selectUnitIn(0,0);
        controller.selectItem(0);
        controller.giveItemTo(1,0);
        controller.selectUnitIn(1,0);
        controller.selectItem(0);
        assertEquals(spear,controller.getTurnOwner().getSelectedItem());
    }

    @Test
    public void moveUnitToTest(){
        Fighter fighter = fighterFactory.createNormalUnit();
        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);
        assertEquals(fighter,controller.getTurnOwner().getMap().getCell(0,0).getUnit());
        controller.selectUnitIn(0,0);
        controller.moveUnitTo(1,0);
        assertNull(controller.getGameMap().getCell(0,0).getUnit());
        assertEquals(fighter,controller.getGameMap().getCell(1,0).getUnit());
    }

    @Test
    public void defeatByHeroDeath(){
        Tactician playerDefeated = controller.getTurnOwner();
        Fighter fighter = fighterFactory.createStrongUnit(axeFactory.createStrongItem("Hacha"));
        heroFactory.setMaxHitPoints(50);
        heroFactory.setMovement(1);
        Hero hero = heroFactory.createUnit();
        Fighter fighter2 = fighterFactory.createNormalUnit();

        controller.addUnit(hero);
        controller.addUnit(fighter2);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);
        controller.selectMyUnit(1);
        controller.placeUnit(0,1);
        controller.endTurn();

        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        controller.placeUnit(1,0);
        controller.selectUnitIn(1,0);
        controller.equipItem(0);
        assertEquals(hero,controller.getGameMap().getCell(0,0).getUnit());
        assertEquals(4,controller.getTacticians().size());
        assertTrue(controller.getTacticians().contains(playerDefeated));
        assertEquals(fighter2,controller.getGameMap().getCell(0,1).getUnit());

        controller.useItemOn(0,0);
        assertNull(controller.getGameMap().getCell(0,0).getUnit());
        assertEquals(3,controller.getTacticians().size());
        assertFalse(controller.getTacticians().contains(playerDefeated));
        assertNull(controller.getGameMap().getCell(0,1).getUnit());
    }

    @Test
    public void defeatByNoRemainingUnits(){
        Tactician playerDefeated = controller.getTurnOwner();
        Fighter fighterWinner = fighterFactory.createStrongUnit(axeFactory.createStrongItem("Hacha"));
        fighterFactory.setMaxHitPoints(50);
        fighterFactory.setMovement(1);
        Fighter fighter1 = fighterFactory.createUnit();
        fighterFactory.setMaxHitPoints(20);
        Fighter fighter2 = fighterFactory.createUnit();

        controller.addUnit(fighter1);
        controller.addUnit(fighter2);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);
        controller.selectMyUnit(1);
        controller.placeUnit(0,1);
        controller.endTurn();

        controller.addUnit(fighterWinner);
        controller.selectMyUnit(0);
        controller.placeUnit(1,0);
        controller.selectUnitIn(1,0);
        controller.equipItem(0);
        assertEquals(fighter1,controller.getGameMap().getCell(0,0).getUnit());
        assertEquals(4,controller.getTacticians().size());
        assertTrue(controller.getTacticians().contains(playerDefeated));
        assertEquals(fighter2,controller.getGameMap().getCell(0,1).getUnit());

        controller.useItemOn(0,0);
        controller.useItemOn(0,1);
        assertNull(controller.getGameMap().getCell(0,0).getUnit());
        assertEquals(3,controller.getTacticians().size());
        assertFalse(controller.getTacticians().contains(playerDefeated));
        assertNull(controller.getGameMap().getCell(0,1).getUnit());
    }

    @Test
    public void unitAndItemParamsGetterMethodsTest(){
        fighterFactory.setMaxHitPoints(100);
        fighterFactory.setMovement(3);
        axeFactory.setName("Hacha");
        axeFactory.setPower(200);
        axeFactory.setMinRange(1);
        axeFactory.setMaxRange(2);
        Fighter fighter = fighterFactory.createUnit(axeFactory.createItem());
        controller.addUnit(fighter);
        controller.selectMyUnit(0);
        controller.placeUnit(0,0);

        controller.selectUnitIn(0,0);
        controller.selectItem(0);
        assertEquals(100,controller.getUnitMaxHitPoints());
        assertEquals(100,controller.getUnitCurrentHitPoints());
        assertEquals(3,controller.getUnitMovement());
        assertEquals("Hacha",controller.getItemName());
        assertEquals(200,controller.getItemPower());
        assertEquals(1,controller.getItemMinRange());
        assertEquals(2,controller.getItemMaxRange());
    }


}