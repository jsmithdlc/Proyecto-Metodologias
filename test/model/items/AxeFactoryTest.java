package model.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AxeFactoryTest extends AbstractTestItemFactory{

    private AxeFactory axeFactory;

    @Override
    public void setTestItemFactory(){
        this.axeFactory = new AxeFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.axeFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        axeFactory.setName("Hacha");
        axeFactory.setPower(200);
        axeFactory.setMinRange(1);
        axeFactory.setMaxRange(4);
        assertEquals(new Axe("Hacha",200,1,4),axeFactory.createItem());
    }

    @Test
    public void createNormalItemTest(){
        setTestItemFactory();
        assertEquals(new Axe("Hacha",50,1,1),axeFactory.createNormalItem("Hacha"));
    }

    @Test
    public void createStrongItemTest(){
        setTestItemFactory();
        assertEquals(new Axe("Hacha",100,1,2),axeFactory.createStrongItem("Hacha"));
    }

}
