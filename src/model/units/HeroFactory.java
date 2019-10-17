package model.units;

import model.items.IEquipableItem;

public class HeroFactory extends AbstractUnitFactory{
    @Override
    public Hero createUnit(IEquipableItem... items){
        return new Hero(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }
}
