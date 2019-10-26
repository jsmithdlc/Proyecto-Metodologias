package model;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a single player of the game identified by its name
 * The player performs all actions requested by the controller, which interacts with the user
 * through the game's GUI, from the game's model.
 *
 * @author Javier Smith
 * @version 2.1
 * @since 2.1
 */
public class Tactician {
    private String name;
    private List<IUnit> units = new ArrayList<>();
    private Field map;
    private IUnit selectedUnit;
    private IEquipableItem selectedItem;

    /**
     * Creates a player for the game
     * @param name
     *      name of the player that is created
     */
    public Tactician(String name){
        this.name = name;
    }

    /**
     * Adds unit to player's list of units
     * @param unit
     *      unit to be added
     */
    public void addUnit(IUnit unit){
        unit.setTactician(this);
        this.units.add(unit);
    }

    /**
     * Sets selectedUnit to unit of index "idx" from list of units of player
     * @param idx
     *      idx of unit to be selected
     */
    public void selectMyUnit(int idx){
        this.selectedUnit = this.units.get(idx);
    }

    /**
     * places selectedUnit on coordinates (x,y) of game´s map
     * @param x
     *      horizontal coordinate of game map
     * @param y
     *      vertical coordinate of game map
     */
    public void placeUnit(int x, int y){
        this.selectedUnit.setLocation(map.getCell(x,y));
        this.selectedUnit = null;
    }

    /**
     * sets selelectedUnit to unit in coordinates (x,y) of game's map
     * @param x
     *      horizontal coordinate of game map
     * @param y
     *      vertical coordinate of game map
     */
    public void selectUnitIn(int x, int y){
        if(map.getCell(x,y).getUnit().getTactician().equals(this)){
            this.selectedUnit = map.getCell(x,y).getUnit();
        }
    }

    /**
     * Sets selectedItem to item of index "idx" from inventory of selectedUnit
     * @param idx
     *      index of item to be selected
     */
    public void selectItem(int idx){
        this.selectedItem = this.selectedUnit.getItems().get(idx);
    }

    /**
     * Sets players map instance to given map
     * @param map
     *      given map
     */
    public void setMap(Field map){
        this.map = map;
    }

    /**
     *
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the player's list of units
     */
    public List<IUnit> getUnits(){
        return this.units;
    }

    /**
     *
     * @return inventory of items of selectedUnit
     */
    public List<IEquipableItem> getItems(){
        return this.getSelectedUnit().getItems();
    }

    /**
     *
     * @return player's selected unit
     */
    public IUnit getSelectedUnit(){
        return this.selectedUnit;
    }

    /**
     *
     * @return selected item from selected unit
     */
    public IEquipableItem getSelectedItem(){
        return this.selectedItem;
    }

    /**
     *
     * @return player's instance of the game map
     */
    public Field getMap(){
        return this.map;
    }

    /**
     * Equips item to selected unit from its inventory (list of items) using index idx
     * @param idx
     *      index of list for item to equip
     */
    public void equipItem(int idx){
        selectedUnit.equipItem(selectedUnit.getItems().get(idx));
    }

    /**
     * Moves selectedUnit to coordinates (x,y) from game map
     * @param x
     *      horizontal coordinate of game map
     * @param y
     *      vertical coordinate of game map
     */
    public void moveSelectedUnit(int x, int y){
        this.selectedUnit.moveTo(map.getCell(x,y));
    }

    /**
     * uses equipped item from players selected unit on unit on coordinates (x,y) of game map
     * @param x
     *      horizontal coordinate of game map
     * @param y
     *      vertical coordinate of game map
     */
    public void useItemOn(int x, int y){
        selectedUnit.attack(map.getCell(x, y).getUnit());
    }

    /**
     * transfers selectedItem, from selectedUnits inventory, to unit in coordinates (x,y) of game map
     * @param x
     *      horizontal coordinate of game map
     * @param y
     *      vertical coordiante of game map
     */
    public void transferTo(int x, int y){
        if(!(map.getCell(x,y).getUnit()==null)){
            this.selectedUnit.transferItem(this.selectedItem,map.getCell(x,y).getUnit());
        }
    }

    /**
     *  moves selected unit to coordinates (x,y) of game's map
     * @param x
     *      horizontal coordinate of game map
     * @param y
     *      vertical coordinate of game map
     */
    public void moveUnitTo(int x, int y){
        this.selectedUnit.moveTo(map.getCell(x,y));
    }

    public void removeUnit(IUnit unit){
        unit.getLocation().removeUnit();
        this.units.remove(unit);
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof Tactician
                && ((Tactician) obj).getName().equals(this.name);
    }


}
