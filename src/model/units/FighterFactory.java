package model.units;

import model.items.IEquipableItem;

public class FighterFactory extends AbstractUnitFactory{
    @Override
    public Fighter createUnit(IEquipableItem... items){
        return new Fighter(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }
}
