package model.items;

import model.itemsFactory.BowFactory;
import model.itemsFactory.ItemFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowFactoryTest extends AbstractTestItemFactory{

    private BowFactory bowFactory;

    @Override
    public void setTestItemFactory(){
        this.bowFactory = new BowFactory();
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

    @Test
    public void createNormalItemTest(){
        setTestItemFactory();
        assertEquals(new Bow("Arco",75,2,4),bowFactory.createNormalItem("Arco"));
    }

    @Test
    public void createStrongItemTest(){
        setTestItemFactory();
        assertEquals(new Bow("Arco",150,2,6),bowFactory.createStrongItem("Arco"));
    }
}
