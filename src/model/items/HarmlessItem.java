package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>NonAttackingItems</i> that the units of the game can use.
 * <p>
 *  A non-attacking item differs from other items in the sense that it does not inflict damage on other units. It is
 *  characterized by a special non aggressive power which is cast upon the target unit
 * @author Javier Smith
 * @since 2.0
 */
public interface HarmlessItem {

    /**
     * uses the item's abilities on unit
     * @param unit
     *      unit to which effects of item are to be applied
     */
    void useItemOn(IUnit unit);
}
