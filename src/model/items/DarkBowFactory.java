package model.items;

public class DarkBowFactory extends AbstractItemFactory{
    @Override
    public Bow createItem(){
        return new Bow(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
