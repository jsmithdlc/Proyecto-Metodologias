package model.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpiritBookFactoryTest extends AbstractTestItemFactory{

    private SpiritBookFactory spiritBookFactory;

    @Override
    public void setTestItemFactory(){
        this.spiritBookFactory = new SpiritBookFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.spiritBookFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        spiritBookFactory.setName("Libro Espíritu");
        spiritBookFactory.setPower(200);
        spiritBookFactory.setMinRange(2);
        spiritBookFactory.setMaxRange(10);
        assertEquals(new SpiritBook("Libro Espíritu",200,2,10),spiritBookFactory.createItem());
    }
}