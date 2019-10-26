package model.itemsFactory;

import model.items.Sword;

public class SwordFactory extends AbstractItemFactory {
    @Override
    public Sword createItem(){
        return new Sword(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Sword createNormalItem(String name){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(75);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public Sword createStrongItem(String name){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(100);
        this.setName(name);
        return this.createItem();
    }
}
