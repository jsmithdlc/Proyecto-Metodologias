package model.itemsFactory;

import model.items.Staff;

public class StaffFactory extends AbstractItemFactory {
    @Override
    public Staff createItem(){
        return new Staff(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Staff createNormalItem(){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(50);
        this.setName("Normal Staff");
        return this.createItem();
    }

    @Override
    public Staff createStrongItem(){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(100);
        this.setName("Strong Staff");
        return this.createItem();
    }
}
