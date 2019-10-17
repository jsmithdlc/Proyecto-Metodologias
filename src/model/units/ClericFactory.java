package model.units;

import model.items.IEquipableItem;

public class ClericFactory extends AbstractUnitFactory {
    @Override
    public Cleric createUnit(IEquipableItem... items){
        return new Cleric(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }
}
