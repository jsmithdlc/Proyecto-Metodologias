package model.units;

import model.items.IEquipableItem;

public class ClericFactory extends AbstractUnitFactory {
    @Override
    public Cleric createUnit(IEquipableItem... items) {
        return new Cleric(this.getMaxHitPoints(), this.getMovement(), this.getLocation(), items);
    }

    @Override
    public Cleric createNormalUnit(IEquipableItem... items) {
        this.setMaxHitPoints(250);
        this.setMovement(2);
        return this.createUnit(items);
    }

    @Override
    public Cleric createStrongUnit(IEquipableItem... items) {
        this.setMaxHitPoints(400);
        this.setMovement(4);
        return this.createUnit(items);
    }
}

