package model.itemsFactory;

import model.items.Spear;

public class SpearFactory extends AbstractItemFactory {
    @Override
    public Spear createItem(){
        return new Spear(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Spear createNormalItem(){
        this.setMinRange(1);
        this.setMaxRange(3);
        this.setPower(100);
        this.setName("Normal Spear");
        return this.createItem();
    }

    @Override
    public Spear createStrongItem(){
        this.setMinRange(1);
        this.setMaxRange(4);
        this.setPower(175);
        this.setName("Strong Spear");
        return this.createItem();
    }
}
