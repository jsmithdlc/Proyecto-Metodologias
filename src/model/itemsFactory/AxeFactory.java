package model.itemsFactory;

import model.items.Axe;

public class AxeFactory extends AbstractItemFactory {
    @Override
    public Axe createItem(){
        return new Axe(this.getName(), this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Axe createNormalItem(){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(50);
        this.setName("Normal Axe");
        return this.createItem();
    }

    @Override
    public Axe createStrongItem(){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(100);
        this.setName("Strong Axe");
        return this.createItem();
    }
}
