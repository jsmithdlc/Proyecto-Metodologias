package model.units;

import model.items.IEquipableItem;

public class HeroFactory extends AbstractUnitFactory{
    @Override
    public Hero createUnit(IEquipableItem... items){
        return new Hero(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }

    @Override
    public Hero createNormalUnit(IEquipableItem... items){
        this.setMaxHitPoints(1000);
        this.setMovement(1);
        return this.createUnit(items);
    }

    @Override
    public Hero createStrongUnit(IEquipableItem... items){
        this.setMaxHitPoints(1500);
        this.setMovement(2);
        return this.createUnit(items);
    }
}
