package model.items;

public class BowFactory extends AbstractItemFactory{
    @Override
    public Bow createItem(){
        return new Bow(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Bow createNormalItem(String name){
        this.setMinRange(2);
        this.setMaxRange(4);
        this.setPower(75);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public Bow createStrongItem(String name){
        this.setMinRange(2);
        this.setMaxRange(6);
        this.setPower(150);
        this.setName(name);
        return this.createItem();
    }
}
