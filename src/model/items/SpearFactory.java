package model.items;

public class SpearFactory extends AbstractItemFactory {
    @Override
    public Spear createItem(){
        return new Spear(this.getName(),this.getPower(),this.getMinRange(),this.getMaxRange());
    }

    @Override
    public Spear createNormalItem(String name){
        this.setMinRange(1);
        this.setMaxRange(3);
        this.setPower(100);
        this.setName(name);
        return this.createItem();
    }

    @Override
    public Spear createStrongItem(String name){
        this.setMinRange(1);
        this.setMaxRange(4);
        this.setPower(175);
        this.setName(name);
        return this.createItem();
    }
}
