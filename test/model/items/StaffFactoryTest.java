package model.items;

import model.itemsFactory.ItemFactory;
import model.itemsFactory.StaffFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffFactoryTest extends AbstractTestItemFactory{

    private StaffFactory staffFactory;

    @Override
    public void setTestItemFactory(){
        this.staffFactory = new StaffFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.staffFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        staffFactory.setName("Bastón");
        staffFactory.setPower(200);
        staffFactory.setMinRange(2);
        staffFactory.setMaxRange(10);
        assertEquals(new Staff("Bastón",200,2,10),staffFactory.createItem());
    }

    @Test
    public void createNormalItemTest(){
        setTestItemFactory();
        assertEquals(new Staff("Normal Staff",50,1,1),staffFactory.createNormalItem());
    }

    @Test
    public void createStrongItemTest(){
        setTestItemFactory();
        assertEquals(new Staff("Strong Staff",100,1,2),staffFactory.createStrongItem());
    }
}
