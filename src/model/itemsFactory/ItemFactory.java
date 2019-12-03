package model.itemsFactory;

import model.items.IEquipableItem;
import model.units.IUnit;
/**
 * This interface represents all items factories from which all items of the game can be easily created
 * <p>
 *  a item factory is way of creating the game's items in an easy way. The items created could be highly customizable
 *  or with predefined values
 *
 * @author Javier Smith
 * @since 2.0
 */
public interface ItemFactory {

    /**
     * sets the name of the item to be created
     * @param name
     *      name of the item to be created
     */
    void setName(String name);

    /**
     * @return the name of the item to be created
     */
    String getName();

    /**
     * sets the power of the item to be created
     * @param power
     *      power of the item to be created
     */
    void setPower(int power);

    /**
     * @return the power of the item to be created
     */
    int getPower();

    /**
     * sets the minimum range of effectiveness of the item to be created
     * @param minRange
     *      minimum range of effectiveness of the item to be created
     */
    void setMinRange(int minRange);

    /**
     * @return the minimum range of effectiveness of the item to be created
     */
    int getMinRange();

    /**
     * sets the maximum range of effectiveness of the item to be created
     * @param maxRange
     *      maximum range of effectiveness of the item to be created
     */
    void setMaxRange(int maxRange);

    /**
     * @return the maximum range of effectiveness of the item to be created
     */
    int getMaxRange();

    /**
     * creates an item based on the values predefined by the setter methods. This, therefore, could be a customizable
     * way of creating units
     * @return
     *      the personalized item created
     */
    IEquipableItem createItem();

    /**
     * creates a normal item, which has a moderate power and range, with predefined values specified in each of the
     * corresponding subclasses
     * @return
     *      the normal item created
     */
    IEquipableItem createNormalItem();

    /**
     * creates a strong item, which has a strong power and range, with predefined values specified in each of the
     * corresponding subclasses
     * @return
     *      the strong item to be created
     */
    IEquipableItem createStrongItem();
}
