package model.items;
import model.units.*;

public class DarkBook extends AbstractBook{
    /**
     * Creates a new DarkBook.
     * <p>
     * darkBook is a type of magic book
     *
     * @param name
     *     the name of the darkBook
     * @param power
     *     the damage power of the darkBook
     * @param minRange
     *     the minimum range of the darkBook
     * @param maxRange
     *     the maximum range of the darkBook
     */
    public DarkBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

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

    @Override
    public void equipSorcerer(Sorcerer sorcerer){
        this.equipTo(sorcerer);
    }

    @Override
    public void attackItem(IEquipableItem item){
        item.receiveDarkBookAttack(this);
        item.counterAttack(this);
    }

    @Override
    public void counterAttack(IEquipableItem item){
        if(this.getOwner().checkAlive() && this.getOwner().inRange(item.getOwner())){
            item.receiveDarkBookAttack(this);
        }
    }

    @Override
    public void receiveDarkBookAttack(DarkBook darkBook){
        this.getOwner().receiveNormalAttack(darkBook);
    }

    @Override
    public void receiveLightBookAttack(LightBook lightBook){
        this.getOwner().receiveStrongAttack(lightBook);
    }

    @Override
    public void receiveSpiritBookAttack(SpiritBook spiritBook){
        this.getOwner().receiveWeakAttack(spiritBook);
    }

    @Override
    public boolean equals(Object obj){ return obj instanceof DarkBook && super.equals(obj);}
}