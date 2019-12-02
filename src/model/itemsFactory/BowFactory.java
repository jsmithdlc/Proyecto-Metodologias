package model.itemsFactory;

import model.items.Bow;

public class BowFactory extends AbstractItemFactory{
    @Override
    public Bow createItem(){
        return new Bow(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Bow createNormalItem(){
        this.setMinRange(2);
        this.setMaxRange(4);
        this.setPower(75);
        this.setName("Normal Bow");
        return this.createItem();
    }

    @Override
    public Bow createStrongItem(){
        this.setMinRange(2);
        this.setMaxRange(6);
        this.setPower(150);
        this.setName("Strong Bow");
        return this.createItem();
    }
}
