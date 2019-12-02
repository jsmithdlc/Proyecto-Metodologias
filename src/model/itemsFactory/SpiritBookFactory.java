package model.itemsFactory;

import model.items.SpiritBook;

public class SpiritBookFactory extends AbstractItemFactory {
    @Override
    public SpiritBook createItem(){
        return new SpiritBook(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
    @Override
    public SpiritBook createNormalItem(){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(100);
        this.setName("Normal SpiritBook");
        return this.createItem();
    }

    @Override
    public SpiritBook createStrongItem(){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(150);
        this.setName("Strong SpiritBook");
        return this.createItem();
    }

}
