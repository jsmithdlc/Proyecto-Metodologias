package model.items;

public class StaffFactory extends AbstractItemFactory {
    @Override
    public Staff createItem(){
        return new Staff(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
