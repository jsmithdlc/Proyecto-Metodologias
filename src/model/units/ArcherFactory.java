package model.units;

import model.items.IEquipableItem;

public class ArcherFactory extends AbstractUnitFactory{

    @Override
    public Archer createUnit(IEquipableItem... items){
        return new Archer(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }

    @Override
    public Archer createNormalUnit(IEquipableItem... items){
        this.setMaxHitPoints(300);
        this.setMovement(2);
        return this.createUnit(items);
    }

    @Override
    public Archer createStrongUnit(IEquipableItem... items){
        this.setMaxHitPoints(500);
        this.setMovement(3);
        return this.createUnit(items);
    }
}
