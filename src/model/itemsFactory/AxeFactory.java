package model.itemsFactory;

import model.items.Axe;

public class AxeFactory extends AbstractItemFactory {
    @Override
    public Axe createItem(){
        return new Axe(this.getName(), this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Axe createNormalItem(String name){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(50);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public Axe createStrongItem(String name){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(100);
        this.setName(name);
        return this.createItem();
    }
}
