package model.items;

public class SwordFactory extends AbstractItemFactory {
    @Override
    public Sword createItem(){
        return new Sword(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
}
