package model.itemsFactory;

import model.items.LightBook;

public class LightBookFactory extends AbstractItemFactory {
    @Override
    public LightBook createItem(){
        return new LightBook(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public LightBook createNormalItem(String name){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(100);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public LightBook createStrongItem(String name){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(150);
        this.setName(name);
        return this.createItem();
    }
}
