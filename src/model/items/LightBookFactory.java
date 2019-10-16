package model.items;

public class LightBookFactory extends AbstractItemFactory {
    @Override
    public LightBook createItem(){
        return new LightBook(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
