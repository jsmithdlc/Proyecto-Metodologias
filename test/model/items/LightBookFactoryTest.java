package model.items;

import model.itemsFactory.ItemFactory;
import model.itemsFactory.LightBookFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightBookFactoryTest extends AbstractTestItemFactory{

    private LightBookFactory lightBookFactory;

    @Override
    public void setTestItemFactory(){
        this.lightBookFactory = new LightBookFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.lightBookFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        lightBookFactory.setName("Libro Luz");
        lightBookFactory.setPower(200);
        lightBookFactory.setMinRange(2);
        lightBookFactory.setMaxRange(10);
        assertEquals(new LightBook("Libro Luz",200,2,10),lightBookFactory.createItem());
    }

    @Test
    public void createNormalItemTest(){
        setTestItemFactory();
        assertEquals(new LightBook("Normal LightBook",100,1,1),lightBookFactory.createNormalItem());
    }

    @Test
    public void createStrongItemTest(){
        setTestItemFactory();
        assertEquals(new LightBook("Strong LightBook",150,1,2),lightBookFactory.createStrongItem());
    }
}