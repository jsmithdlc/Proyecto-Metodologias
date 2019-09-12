package model.items;

public abstract class AbstractBook extends AbstractItem{

    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
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
}
