package model.units;

import model.items.IEquipableItem;

public class SorcererFactory extends AbstractUnitFactory{
    @Override
    public Sorcerer createUnit(IEquipableItem... items){
        return new Sorcerer(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }
}
