package model.items;

public class DarkBookFactory extends AbstractItemFactory {

    @Override
    public DarkBook createItem(){
        return new DarkBook(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
