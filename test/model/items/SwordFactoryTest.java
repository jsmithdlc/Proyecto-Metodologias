package model.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwordFactoryTest extends AbstractTestItemFactory{

    private SwordFactory swordFactory;

    @Override
    public void setTestItemFactory(){
        this.swordFactory = new SwordFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.swordFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        swordFactory.setName("Espada");
        swordFactory.setPower(200);
        swordFactory.setMinRange(2);
        swordFactory.setMaxRange(10);
        assertEquals(new Sword("Espada",200,2,10),swordFactory.createItem());
    }
}