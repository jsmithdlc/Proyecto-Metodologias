package model;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

public class Tactician {
    private String name;
    private List<IUnit> units = new ArrayList<>();
    private Field map;
    private IUnit selectedUnit;
    private IEquipableItem selectedItem;

    public Tactician(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addUnit(IUnit unit, int x, int y){
        unit.setLocation(map.getCell(x,y));
        unit.setTactician(this);
        this.units.add(unit);
    }

    public List<IUnit> getUnits(){
        return this.units;
    }

    public void selectUnitIn(int x, int y){
        this.selectedUnit = map.getCell(x,y).getUnit();
    }

    public IUnit getSelectedUnit(){
        return this.selectedUnit;
    }

    public void setMap(Field map){
        this.map = map;
    }

    public Field getMap(){
        return this.map;
    }

    public void equipItem(int idx){
        selectedUnit.equipItem(selectedUnit.getItems().get(idx));
    }

    public void selectItem(int idx){
        this.selectedItem = this.selectedUnit.getItems().get(idx);
    }

    public void moveSelectedUnit(int x, int y){
        this.selectedUnit.moveTo(map.getCell(x,y));
    }

    public void useItemOn(int x, int y){
        if(this.selectedItem.equals(this.selectedUnit.getEquippedItem())){
            selectedUnit.attack(map.getCell(x,y).getUnit());
        }
    }

    public void transferTo(int x, int y){
        if(!(map.getCell(x,y).getUnit()==null)){
            this.selectedUnit.transferItem(this.selectedItem,map.getCell(x,y).getUnit());
        }
    }
    @Override
    public boolean equals(Object obj){
        return obj instanceof Tactician
                && ((Tactician) obj).getName().equals(this.name);
    }


}
