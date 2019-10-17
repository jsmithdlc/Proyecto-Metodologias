package model.units;

import model.items.IEquipableItem;

public class ArcherFactory extends AbstractUnitFactory{

    @Override
    public Archer createUnit(IEquipableItem... items){
        return new Archer(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }
}
