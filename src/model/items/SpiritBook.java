package model.items;
import model.units.*;

/**
 * This class represents a <i>SpiritBook</i> type item.
 * <p>
 * A spiritBook is an item that is strong against lightBooks but weak against darkBooks
 * @author Javier Smith
 *
 */
public class SpiritBook extends AbstractBook{
    /**
     * Creates a new SpiritBook.
     *
     * @param name
     *     the name of the spiritBook
     * @param power
     *     the damage power of the spiritBook
     * @param minRange
     *     the minimum range of the spiritBook
     * @param maxRange
     *     the maximum range of the spiritBook
     */
    public SpiritBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }


    @Override
    public void equipSorcerer(Sorcerer sorcerer){
        this.equipTo(sorcerer);
    }

    @Override
    public void attackItem(IEquipableItem item){
        item.receiveSpiritBookAttack(this);
        item.counterAttack(this);
    }

    @Override
    public void counterAttack(IEquipableItem item){
        if(this.getOwner().checkAlive() && this.getOwner().inRange(item.getOwner())){
            item.receiveSpiritBookAttack(this);
        }
    }

    @Override
    public void receiveDarkBookAttack(DarkBook darkBook){
        this.getOwner().receiveStrongAttack(darkBook);
    }

    @Override
    public void receiveLightBookAttack(LightBook lightBook){
        this.getOwner().receiveWeakAttack(lightBook);
    }

    @Override
    public void receiveSpiritBookAttack(SpiritBook spiritBook){
        this.getOwner().receiveNormalAttack(spiritBook);
    }

    @Override
    public boolean equals(Object obj){ return obj instanceof SpiritBook && super.equals(obj);}
}