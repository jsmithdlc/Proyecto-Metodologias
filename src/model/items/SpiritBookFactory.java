package model.items;

public class SpiritBookFactory extends AbstractItemFactory {
    @Override
    public SpiritBook createItem(){
        return new SpiritBook(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }
    @Override
    public SpiritBook createNormalItem(String name){
        this.setMinRange(1);
        this.setMaxRange(1);
        this.setPower(100);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public SpiritBook createStrongItem(String name){
        this.setMinRange(1);
        this.setMaxRange(2);
        this.setPower(150);
        this.setName(name);
        return this.createItem();
    }

}
