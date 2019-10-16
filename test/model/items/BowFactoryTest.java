package model.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowFactoryTest extends AbstractTestItemFactory{

    private DarkBowFactory bowFactory;

    @Override
    public void setTestItemFactory(){
        this.bowFactory = new DarkBowFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.bowFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        bowFactory.setName("Arco");
        bowFactory.setPower(200);
        bowFactory.setMinRange(2);
        bowFactory.setMaxRange(10);
        assertEquals(new Bow("Arco",200,2,10),bowFactory.createItem());
    }
}
