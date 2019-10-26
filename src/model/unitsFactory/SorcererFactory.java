package model.unitsFactory;

import model.items.IEquipableItem;
import model.units.Sorcerer;

public class SorcererFactory extends AbstractUnitFactory{
    @Override
    public Sorcerer createUnit(IEquipableItem... items){
        return new Sorcerer(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }

    @Override
    public Sorcerer createNormalUnit(IEquipableItem... items){
        this.setMaxHitPoints(200);
        this.setMovement(2);
        return this.createUnit(items);
    }

    @Override
    public Sorcerer createStrongUnit(IEquipableItem... items){
        this.setMaxHitPoints(400);
        this.setMovement(4);
        return this.createUnit(items);
    }
}
