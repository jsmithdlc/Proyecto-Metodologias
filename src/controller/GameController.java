package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.*;

import model.itemsFactory.*;
import model.unitsFactory.*;
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
    private Field map;
    private Map<String,Tactician> tacticians = new HashMap<>();
    private List<String> turns = new ArrayList<>();
    private List<String> winners;
    public int currentTurnIdx;
    private int maxRounds;
    private long seed;
    private FieldFactory fieldFactory = new FieldFactory();
    private Tactician currentTactician;
    private int roundNumber;
    private IUnit unit2Create;
    private List<String> unit2CreateParams = new ArrayList<>();

    private PropertyChangeSupport
        mapChanges = new PropertyChangeSupport(this);

    private Map<String, UnitFactory> unitFactories = new HashMap<>();
    private Map<String, ItemFactory> itemFactories = new HashMap<>();
    private Map<Integer, List<String>> initUnits = new TreeMap<>();
    private int nInitUnits = 0;

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
        currentTurnIdx = 0;
        generateTacticians();
        Collections.shuffle(this.turns,new Random());
        this.currentTactician = this.tacticians.get(turns.get(0));
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Map initialized",null,this.map));
        createUnitFactories();
        createItemFactories();
    }

    /**
     * creates factories of units to facilitate their creation
     */
    private void createUnitFactories(){
        unitFactories.put("Hero", new HeroFactory());
        unitFactories.put("Fighter", new FighterFactory());
        unitFactories.put("Alpaca", new AlpacaFactory());
        unitFactories.put("Cleric", new ClericFactory());
        unitFactories.put("Sorcerer", new SorcererFactory());
        unitFactories.put("SwordMaster", new SwordMasterFactory());
        unitFactories.put("Archer", new ArcherFactory());
    }

    /**
     * creates factories of items to facilitate their creation
     */
    private void createItemFactories(){
        itemFactories.put("Axe", new AxeFactory());
        itemFactories.put("Bow", new BowFactory());
        itemFactories.put("Spear", new SpearFactory());
        itemFactories.put("Staff", new StaffFactory());
        itemFactories.put("Sword", new SwordFactory());
        itemFactories.put("DarkBook", new DarkBookFactory());
        itemFactories.put("LightBook", new LightBookFactory());
        itemFactories.put("SpiritBook", new SpiritBookFactory());
    }

    /**
     * creates a normal unit and sets the parameter unit2Create to this unit
     * @param unit
     *      type (class) of unit to be created
     */
    public void createNormalUnit(String unit){
        unit2CreateParams.clear();
        unit2CreateParams.add("N"+unit);
        unit2CreateParams.add(this.currentTactician.getName());
        unit2Create = unitFactories.get(unit).createNormalUnit();
    }

    /**
     * creates a strong unit and sets the parameter unit2Create to this unit
     * @param unit
     *      type (class) of unit to be created
     */
    public void createStrongUnit(String unit){
        unit2CreateParams.clear();
        unit2CreateParams.add("S"+unit);
        unit2CreateParams.add(this.currentTactician.getName());
        unit2Create = unitFactories.get(unit).createStrongUnit();
    }

    /**
     * creates and assigns a normal type item to unit2Create
     * @param item
     *      type (class) of item to be assigned
     */
    public void assignNormalItem(String item){
        unit2CreateParams.add("N"+item);
        unit2Create.addItem(itemFactories.get(item).createNormalItem());
    }

    /**
     * creates and assigns a strong type item to unit2Create
     * @param item
     *      type (class) of item to be assigned
     */
    public void assignStrongItem(String item){
        unit2CreateParams.add("S"+item);
        unit2Create.addItem(itemFactories.get(item).createStrongItem());
    }

    /**
     * places the unit unit2Create on the coordinates (x,y) of gameMap. Additionally, stores the characteristics
     * of this unit in the map initUnits for possible later reconstruction
     * @param x
     *      horizontal coordinate of game map where unit is set to be placed
     * @param y
     *      vertical coordinate of game map where unit is set to be placed
     */
    public void placeCreatedUnit(int x, int y){
        addUnit(unit2Create);
        selectMyUnit(currentTactician.getUnits().size()-1);
        placeUnit(x,y);
        if(map.getCell(x,y).getUnit().equals(unit2Create)){
            unit2CreateParams.add(Integer.toString(x));
            unit2CreateParams.add(Integer.toString(y));
            initUnits.put(nInitUnits, List.copyOf(unit2CreateParams));
            nInitUnits+=1;
        }
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

    /**
     * sets an initial order of turns for the game's tacticians according to the seed of Random
     */
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
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Map initialized",null,this.map));
    }

    /**
     * generates the initial tacticians of the game, adding the corresponding observers to map changes associated
     * with each one of them
     */
    public void generateTacticians(){
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
    }

    /**
     * @return the tactician that will play in the next turn
     */
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
        if(tacticians.containsKey(tactician)) {
            if (currentTurnIdx + 1 == numberOfPlayers) {
                currentTurnIdx--;
            }
            numberOfPlayers--;
            tacticians.get(tactician).purgeUnits();
            mapChanges.firePropertyChange(new PropertyChangeEvent(this, "Tactician Removed", this.map, tacticians.get(tactician).getMap()));
            this.map = tacticians.get(tactician).getMap();
            tacticians.remove(tactician);
            turns.remove(tactician);
            if (numberOfPlayers == 1) {
                winners = new ArrayList<>(turns);
            }
        }
    }

    /**
     * Starts the game.
     * @param maxTurns
     *  the maximum number of turns the game can last
     */
    public void initGame(final int maxTurns) {
        this.maxRounds = maxTurns;
        regenerateStart();
    }

    /**
     * Starts a game without a limit of turns.
     */
    public void initEndlessGame() {
        this.maxRounds = -1;
        regenerateStart();
    }

    /**
     * regenerates tacticians, maps and units according to the information specified and stored in several indicators
     * such as the ammount of initial players (initPlayers), the size of the map (mapSize) and the characteristics of
     * units stored in initUnits.
     */
    public void regenerateStart(){
        numberOfPlayers = initPlayers;
        this.roundNumber = 0;
        this.winners = null;
        map = fieldFactory.createMap(this.mapSize);
        currentTurnIdx = 0;
        mapChanges = new PropertyChangeSupport(this);
        turns.clear();
        tacticians.clear();
        generateTacticians();
        setInitTurns();
        mapChanges.firePropertyChange(new PropertyChangeEvent(this,"Map initialized",null,this.map));
        resetUnits();
    }

    /**
     * recreates initial units according to the characteristics stored in initUnits, placing them in their respective
     * initial positions and setting their tacticians accordingly
     */
    private void resetUnits(){
        for(Map.Entry<Integer,List<String>> entry : initUnits.entrySet()){
            List<String> tempList = entry.getValue();
            if(tempList.get(0).charAt(0)=='N'){
                createNormalUnit(tempList.get(0).substring(1));
            }
            else{
                createStrongUnit(tempList.get(0).substring(1));
            }
            Tactician owner = tacticians.get(tempList.get(1));
            for(int i=2;i<tempList.size()-2;i++){
                if(tempList.get(i).charAt(0)=='N'){
                    assignNormalItem(tempList.get(i).substring(1));
                }
                else{
                    assignStrongItem(tempList.get(i).substring(1));
                }
            }
            owner.addUnit(unit2Create);
            owner.selectMyUnit(owner.getUnits().size()-1);
            owner.placeUnit(Integer.parseInt(tempList.get(tempList.size()-2)),
                    Integer.parseInt(tempList.get(tempList.size()-1)));
        }
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
     * places a unit in the (x,y) coordinates specified
     * @param x
     *      horizontal coordinate of game map
     * @param y
     *      vertical coordinate of game map
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