package model.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpearFactoryTest extends AbstractTestItemFactory{

    private SpearFactory spearFactory;

    @Override
    public void setTestItemFactory(){
        this.spearFactory = new SpearFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.spearFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        spearFactory.setName("Libro Luz");
        spearFactory.setPower(200);
        spearFactory.setMinRange(2);
        spearFactory.setMaxRange(10);
        assertEquals(new Spear("Libro Luz",200,2,10),spearFactory.createItem());
    }
}