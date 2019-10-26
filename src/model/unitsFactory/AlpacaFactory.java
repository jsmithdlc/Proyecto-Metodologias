package model.unitsFactory;

import model.items.IEquipableItem;
import model.units.Alpaca;

public class AlpacaFactory extends AbstractUnitFactory {

    @Override
    public Alpaca createUnit(IEquipableItem... items){
        return new Alpaca(this.getMaxHitPoints(),this.getMovement(),this.getLocation(),items);
    }

    @Override
    public Alpaca createNormalUnit(IEquipableItem... items){
        this.setMaxHitPoints(500);
        this.setMovement(3);
        return this.createUnit(items);
    }

    @Override
    public Alpaca createStrongUnit(IEquipableItem... items){
        this.setMaxHitPoints(750);
        this.setMovement(5);
        return this.createUnit(items);
    }
}
