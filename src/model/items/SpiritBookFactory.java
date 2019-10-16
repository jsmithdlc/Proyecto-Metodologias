package model.items;

public class SpiritBookFactory extends AbstractItemFactory {
    @Override
    public SpiritBook createItem(){
        return new SpiritBook(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
