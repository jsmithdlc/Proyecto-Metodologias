package model.itemsFactory;

import model.items.DarkBook;

public class DarkBookFactory extends AbstractItemFactory {

    @Override
    public DarkBook createItem(){
        return new DarkBook(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public DarkBook createNormalItem(String name){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(100);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public DarkBook createStrongItem(String name){
        this.setMinRange(1);
        this.setMaxRange(3);
        this.setPower(150);
        this.setName(name);
        return this.createItem();
    }


}
