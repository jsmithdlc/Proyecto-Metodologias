package model.items;

public class SpearFactory extends AbstractItemFactory {
    @Override
    public Spear createItem(){
        return new Spear(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
