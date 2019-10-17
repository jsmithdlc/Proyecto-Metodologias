package model.units;

import model.items.IEquipableItem;

public class AlpacaFactory extends AbstractUnitFactory {

    @Override
    public Alpaca createUnit(IEquipableItem... items){
        return new Alpaca(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }
}
