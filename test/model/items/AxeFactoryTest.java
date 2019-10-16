package model.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
