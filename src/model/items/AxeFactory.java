package model.items;

public class AxeFactory extends AbstractItemFactory {
    @Override
    public Axe createItem(){
        return new Axe(this.getName(), this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
