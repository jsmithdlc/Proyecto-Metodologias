package model.unitsFactory;

import model.items.IEquipableItem;
import model.units.SwordMaster;

public class SwordMasterFactory  extends AbstractUnitFactory{
    @Override
    public SwordMaster createUnit(IEquipableItem... items){
        return new SwordMaster(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }

    @Override
    public SwordMaster createNormalUnit(IEquipableItem... items){
        this.setMaxHitPoints(400);
        this.setMovement(1);
        return this.createUnit(items);
    }

    @Override
    public SwordMaster createStrongUnit(IEquipableItem... items){
        this.setMaxHitPoints(600);
        this.setMovement(3);
        return this.createUnit(items);
    }
}
