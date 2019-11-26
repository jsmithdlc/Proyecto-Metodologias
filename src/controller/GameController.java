package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.*;

import observer.MapHandler;
import model.Tactician;
import model.items.IEquipableItem;
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

    private PropertyChangeSupport
        mapChanges = new PropertyChangeSupport(this),
        playersChanges = new PropertyChangeSupport(this);

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
        fieldFactory.setSeed(new Random().nextLong());
        map = fieldFactory.createMap(mapSize);
        initMap=map;
        currentTurnIdx = 0;
        StringBuilder sb;
        for(int i=0;i<numberOfPlayers;i++) {
            sb = new StringBuilder("Player ");
            sb.append(i);
            turns.add(sb.toString());
            Tactician tactician = new Tactician(sb.toString());
            MapHandler mapHandler = new MapHandler(tactician);
            mapChanges.addPropertyChangeListener(mapHandler);
            tactician.setController(this);
            tacticians.put(sb.toString(), tactician);
        }
        initTacticians = deepCopyTacticians(tacticians);
        Collections.shuffle(this.turns,new Random());
        this.currentTactician = this.tacticians.get(turns.get(0));
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Map initialized",null,this.map));
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
        initTacticians = deepCopyTacticians(tacticians);
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
        initMap = map;
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Map initialized",null,this.map));
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
        this.currentTactician.resetMoves();
        if((this.currentTurnIdx+1)%this.numberOfPlayers==0){
            this.roundNumber++;
            if(roundNumber==maxRounds && roundNumber!=0){
                winners = this.produceWinners();
            }
            else {
                this.currentTurnIdx = 0;

                while (currentTactician.equals(tacticians.get(turns.get(0)))) {
                    Collections.shuffle(turns, new Random());
                }
                this.currentTactician = tacticians.get(turns.get(0));
            }
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
        this.currentTurnIdx = 0;
        this.roundNumber = 0;
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
        this.currentTurnIdx = 0;
        this.roundNumber = 0;
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
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Unit Placed",this.map,getTurnOwner().getMap()));
        this.map = getTurnOwner().getMap();
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
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Unit equipped",this.map,getTurnOwner().getMap()));
        this.map = getTurnOwner().getMap();
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
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Item used between units",this.map,getTurnOwner().getMap()));
        this.map = getTurnOwner().getMap();
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
        this.getTurnOwner().transferTo(x,y);
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Item transfered",this.map,getTurnOwner().getMap()));
        this.map = getTurnOwner().getMap();
    }

    /**
     * moves selected unit to coordinates (x,y) of game map
     * @param x
     *      horizontal coordinate of map
     * @param y
     *      vertical coordinate of map
     */
    public void moveUnitTo(int x, int y){
        this.getTurnOwner().moveUnitTo(x,y);
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Unit moved",this.map,getTurnOwner().getMap()));
        this.map = getTurnOwner().getMap();
    }

    /**
     * @return selected item name
     */
    public String getItemName(){
        return currentTactician.getSelectedItem().getName();
    }

    /**
     * @return selecte item power
     */
    public int getItemPower(){
        return currentTactician.getSelectedItem().getPower();
    }

    /**
     * @return selected item maximum range
     */
    public int getItemMaxRange(){
        return currentTactician.getSelectedItem().getMaxRange();
    }

    /**
     * @return selected item minimum range
     */
    public int getItemMinRange(){
        return currentTactician.getSelectedItem().getMinRange();
    }

    /**
     * @return selected unit current hitPoints
     */
    public int getUnitCurrentHitPoints(){
        return currentTactician.getSelectedUnit().getCurrentHitPoints();
    }

    /**
     * @return selected unit maximum hitPoints
     */
    public int getUnitMaxHitPoints(){
        return currentTactician.getSelectedUnit().getMaxHitPoints();
    }

    /**
     * @return selected unit movement
     */
    public int getUnitMovement(){
        return currentTactician.getSelectedUnit().getMovement();
    }

    /**
     * @return list with names of winners if match is ended by reaching maximum ammount of rounds
     */
    public ArrayList<String> produceWinners(){
        int maxUnits = 0;
        for(String key: tacticians.keySet()){
            if(tacticians.get(key).getUnits().size() > maxUnits){
                maxUnits = tacticians.get(key).getUnits().size();
            }
        }
        TreeMap<String,Tactician> sortedWinners = new TreeMap<>();
        for(String key: tacticians.keySet()){
            if(tacticians.get(key).getUnits().size()==maxUnits){
                sortedWinners.put(key,tacticians.get(key));
            }
        }
        return new ArrayList<>(sortedWinners.keySet());
    }
}