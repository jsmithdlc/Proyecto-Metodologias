package model.items;
import model.units.*;

public class LightBook extends AbstractBook{
    /**
     * Creates a new LightBook.
     * <p>
     * lightBook is a type of magic book
     *
     * @param name
     *     the name of the lightBook
     * @param power
     *     the damage power of the lightBook
     * @param minRange
     *     the minimum range of the lightBook
     * @param maxRange
     *     the maximum range of the lightBook
     */
    public LightBook(final String name, final int power, final int minRange, final int maxRange) {
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
        item.receiveLightBookAttack(this);
        item.counterAttack(this);
    }

    @Override
    public void counterAttack(IEquipableItem item){
        if(this.getOwner().checkAlive() && this.getOwner().inRange(item.getOwner())){
            item.receiveLightBookAttack(this);
        }
    }

    @Override
    public void receiveDarkBookAttack(DarkBook darkBook){
        this.getOwner().receiveWeakAttack(darkBook);
    }

    @Override
    public void receiveLightBookAttack(LightBook lightBook){
        this.getOwner().receiveNormalAttack(lightBook);
    }

    @Override
    public void receiveSpiritBookAttack(SpiritBook spiritBook){
        this.getOwner().receiveStrongAttack(spiritBook);
    }

    @Override
    public boolean equals(Object obj){ return obj instanceof LightBook && super.equals(obj);}
}