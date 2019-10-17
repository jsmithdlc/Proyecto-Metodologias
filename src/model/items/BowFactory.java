package model.items;

public class BowFactory extends AbstractItemFactory{
    @Override
    public Bow createItem(){
        return new Bow(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
