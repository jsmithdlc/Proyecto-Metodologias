package model.units;

import model.items.IEquipableItem;

public class SwordMasterFactory  extends AbstractUnitFactory{
    @Override
    public SwordMaster createUnit(IEquipableItem... items){
        return new SwordMaster(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }
}
