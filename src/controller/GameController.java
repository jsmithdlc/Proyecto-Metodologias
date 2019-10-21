package controller;

import java.util.*;

import model.Tactician;
import model.items.IEquipableItem;
import model.items.ItemFactory;
import model.map.Field;
import model.map.FieldFactory;
import model.units.*;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController {

    private int numberOfPlayers;
    private int initPlayers;
    private int mapSize;
    private Field map = new Field();
    private Field initMap;
    private Map<String,Tactician> tacticians = new HashMap<>();
    private Map<String,Tactician> initTacticians;
    private List<String> turns = new ArrayList<>();
    private List<String> winners;
    public int currentTurnIdx;
    private int maxRounds;
    private long seed;
    private FieldFactory fieldFactory = new FieldFactory();
    private Tactician currentTactician;
    private int roundNumber;

    private HeroFactory heroFactory = new HeroFactory();
    private SwordMasterFactory swordMasterFactory = new SwordMasterFactory();
    private ArcherFactory archerFactory = new ArcherFactory();
    private FighterFactory fighterFactory = new FighterFactory();
    private SorcererFactory sorcererFactory = new SorcererFactory();
    private ClericFactory clericFactory = new ClericFactory();

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers
     *     the number of players for this game
     * @param mapSize
     *     the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize) {
        this.mapSize = mapSize;
        this.numberOfPlayers = numberOfPlayers;
        this.initPlayers = numberOfPlayers;
        StringBuilder sb;
        for(int i=0;i<numberOfPlayers;i++) {
            sb = new StringBuilder("Player ");
            sb.append(i);
            turns.add(sb.toString());
            tacticians.put(sb.toString(), new Tactician(sb.toString()));
        }
        initTacticians = deepCopyTacticians(tacticians);
        fieldFactory.setSeed(new Random().nextLong());
        map = fieldFactory.createMap(mapSize);
        initMap=map;
        currentTurnIdx = 0;
        Collections.shuffle(this.turns,new Random());
        this.currentTactician = this.tacticians.get(turns.get(0));
    }

    public Map<String,Tactician> deepCopyTacticians( Map<String, Tactician> original){
        Map<String,Tactician> copy =  new HashMap<String,Tactician>();
        for(Map.Entry<String,Tactician> entry : original.entrySet()){
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    /**
     * @return the list of all the tacticians participating in the game.
     */
    public List<Tactician> getTacticians() {
        TreeMap<String,Tactician> sorted = new TreeMap<>();
        sorted.putAll(this.tacticians);
        return new ArrayList<>(sorted.values());
    }

    /**
     * @return the map of the current game
     */
    public Field getGameMap() {
        return this.map;
    }

    public void setInitTurns(){
        TreeMap<String,Tactician> sorted = new TreeMap<>();
        sorted.putAll(this.tacticians);
        List<String> tacticianNames = new ArrayList<>(sorted.keySet());
        Collections.shuffle(tacticianNames,new Random(seed));
        turns = tacticianNames;
        this.currentTactician = this.tacticians.get(turns.get(0));
    }

    /**
     *  sets seed for generation of gameMap
     * @param seed random seed for map generation
     */
    public void setSeed(long seed){
        this.seed = seed;
    }

    /**
     * generates gameMap according to set seed and to mapSize given in constructor
     */
    public void generateGameMap(){
        fieldFactory.setSeed(this.seed);
        this.map = fieldFactory.createMap(this.mapSize);
    }

    public Tactician getNextTurnOwner(){
        return this.tacticians.get(this.turns.get(currentTurnIdx+1));
    }

    /**
     * @return the tactician that's currently playing
     */
    public Tactician getTurnOwner() {
        return this.currentTactician;
    }

    /**
     * @return the number of rounds since the start of the game.
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }

    /**
     * @return the maximum number of rounds a match can last
     */
    public int getMaxRounds() {
        return this.maxRounds;
    }

    /**
     * Finishes the current player's turn.
     */
    public void endTurn() {
        if(roundNumber == maxRounds && roundNumber!=0){
            TreeMap<String,Tactician> sorted = new TreeMap<>();
            sorted.putAll(this.tacticians);
            winners = new ArrayList<>(sorted.keySet());
        }
        else if((this.currentTurnIdx+1)%this.numberOfPlayers==0){
            this.currentTurnIdx = 0;
            this.roundNumber++;
            while(currentTactician.equals(tacticians.get(turns.get(0)))){
                Collections.shuffle(turns,new Random());
            }
            this.currentTactician = tacticians.get(turns.get(0));
        }
        else{
            this.currentTurnIdx++;
            this.currentTactician = tacticians.get(turns.get(this.currentTurnIdx));
        }
    }

    /**
     * Removes a tactician and all of it's units from the game.
     *
     * @param tactician
     *     the player to be removed
     */
    public void removeTactician(String tactician) {
        numberOfPlayers--;
        tacticians.remove(tactician);
        turns.remove(tactician);
        if(numberOfPlayers==1){
            winners = new ArrayList<>(turns);
        }
    }

    /**
     * Starts the game.
     * @param maxTurns
     *  the maximum number of turns the game can last
     */
    public void initGame(final int maxTurns) {
        numberOfPlayers = initPlayers;
        tacticians = deepCopyTacticians(initTacticians);
        map = initMap;
        this.setInitTurns();
        this.maxRounds = maxTurns;
        this.roundNumber = 1;
        this.winners = null;
    }

    /**
     * Starts a game without a limit of turns.
     */
    public void initEndlessGame() {
        numberOfPlayers = initPlayers;
        tacticians = deepCopyTacticians(initTacticians);
        map = initMap;
        this.setInitTurns();
        this.maxRounds = -1;
        this.roundNumber = 1;
        this.winners = null;
    }

    /**
     * @return the winner of this game, if the match ends in a draw returns a list of all the winners
     */
    public List<String> getWinners() {
        return winners;
    }

    /**
     * @return the current player's selected unit
     */
    public IUnit getSelectedUnit() {
        return this.getTurnOwner().getSelectedUnit();
    }

    /**
     * Selects a unit in the game map
     *
     * @param x
     *     horizontal position of the unit
     * @param y
     *     vertical position of the unit
     */
    public void selectUnitIn(int x, int y) {
        this.currentTactician.selectUnitIn(x,y);
    }

    /**
     * Selects a unit from players list of units
     * @param idx index of unit to select from list of units of player
     */
    public void selectMyUnit(int idx){
        this.getTurnOwner().selectMyUnit(idx);
    }

    /**
     * Adds unit to player's list of units
     * @param unit unit to be assigned to player
     */
    public void addUnit(IUnit unit){
        this.getTurnOwner().addUnit(unit);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void placeUnit(int x, int y){
        this.getTurnOwner().setMap(this.map);
        this.getTurnOwner().placeUnit(x,y);
    }

    /**
     * @return the inventory of the currently selected unit.
     */
    public List<IEquipableItem> getItems() {
        return this.currentTactician.getItems();
    }

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index
     *     the location of the item in the inventory.
     */
    public void equipItem(int index) {
        this.currentTactician.equipItem(index);
    }

    /**
     * Uses the equipped item on a target
     *
     * @param x
     *     horizontal position of the target
     * @param y
     *     vertical position of the target
     */
    public void useItemOn(int x, int y) {
        this.currentTactician.useItemOn(x,y);
    }

    /**
     * Selects an item from the selected unit's inventory.
     *
     * @param index
     *     the location of the item in the inventory.
     */
    public void selectItem(int index) {
        this.getTurnOwner().selectItem(index);

    }

    /**
     * Gives the selected item to a target unit.
     *
     * @param x
     *     horizontal position of the target
     * @param y
     *     vertical position of the target
     */
    public void giveItemTo(int x, int y) {
    }
}