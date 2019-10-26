package model.unitsFactory;

import model.items.IEquipableItem;
import model.units.Fighter;

public class FighterFactory extends AbstractUnitFactory{
    @Override
    public Fighter createUnit(IEquipableItem... items){
        return new Fighter(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }

    @Override
    public Fighter createNormalUnit(IEquipableItem... items){
        this.setMaxHitPoints(400);
        this.setMovement(2);
        return this.createUnit(items);
    }

    @Override
    public Fighter createStrongUnit(IEquipableItem... items){
        this.setMaxHitPoints(700);
        this.setMovement(3);
        return this.createUnit(items);
    }
}
