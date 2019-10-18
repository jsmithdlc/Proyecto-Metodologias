package model.items;

public class StaffFactory extends AbstractItemFactory {
    @Override
    public Staff createItem(){
        return new Staff(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Staff createNormalItem(String name){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(50);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public Staff createStrongItem(String name){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(100);
        this.setName(name);
        return this.createItem();
    }
}
