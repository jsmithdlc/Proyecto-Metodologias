package model.items;
/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 *  a weapon differs from other items in the sense that it can perform damage on another unit, thereby qualifying as
 *  a combat item
 * @author Javier Smith
 * @since 2.0
 */
public interface AttackingItem {

    /**
     * attacks owner of item
     * @param item
     *      item of owner to attack
     */
    void attackItem(IEquipableItem item);

}
