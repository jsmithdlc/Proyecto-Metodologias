package model.items;

import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all book items.
 *
 * @author Javier Smith
 * @since 1.0
 */
public abstract class AbstractBook extends AbstractItem implements AttackingItem {

    /**
     * Constructor for a book item
     *
     * @param name
     *     the name of the book
     * @param power
     *      the power of the book (this could be the amount of damage or healing the item does)
     * @param minRange
     *      the minimum range of the item
     * @param maxRange
     *      the maximum range of the item
     */
    public AbstractBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void receiveAxeAttack(Axe axe){
        this.getOwner().receiveStrongAttack(axe);
    };

    @Override
    public void receiveSwordAttack(Sword sword){
        this.getOwner().receiveStrongAttack(sword);
    };

    @Override
    public void receiveSpearAttack(Spear spear){
        this.getOwner().receiveStrongAttack(spear);
    };

    @Override
    public void receiveBowAttack(Bow bow){
        this.getOwner().receiveStrongAttack(bow);
    };

    @Override
    public void equipArcher(Archer archer){}

    @Override
    public void equipCleric(Cleric cleric) {}

    @Override
    public void equipFighter(Fighter fighter){}

    @Override
    public void equipHero(Hero hero){}

    @Override
    public void equipSwordMaster(SwordMaster swordmaster){}
}
