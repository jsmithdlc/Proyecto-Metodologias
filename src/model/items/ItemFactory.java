package model.items;

import model.units.IUnit;

public interface ItemFactory {

    void setName(String name);

    String getName();

    void setPower(int power);

    int getPower();

    void setMinRange(int minRange);

    int getMinRange();

    void setMaxRange(int maxRange);

    int getMaxRange();

    IEquipableItem createItem();

    IEquipableItem createNormalItem(String name);

    IEquipableItem createStrongItem(String name);
}
