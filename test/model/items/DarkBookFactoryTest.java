package model.items;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DarkBookFactoryTest extends AbstractTestItemFactory{

    private DarkBookFactory darkBookFactory;

    @Override
    public void setTestItemFactory(){
        this.darkBookFactory = new DarkBookFactory();
    }

    @Override
    public ItemFactory getTestItemFactory(){
        return this.darkBookFactory;
    }

    @Test
    public void createItemTest(){
        setTestItemFactory();
        darkBookFactory.setName("Libro Negro");
        darkBookFactory.setPower(200);
        darkBookFactory.setMinRange(2);
        darkBookFactory.setMaxRange(10);
        assertEquals(new DarkBook("Libro Negro",200,2,10),darkBookFactory.createItem());
    }

    @Test
    public void createNormalItemTest(){
        setTestItemFactory();
        assertEquals(new DarkBook("Libro Negro",100,1,1),darkBookFactory.createNormalItem("Libro Negro"));
    }

    @Test
    public void createStrongItemTest(){
        setTestItemFactory();
        assertEquals(new DarkBook("Libro Negro",150,1,3),darkBookFactory.createStrongItem("Libro Negro"));
    }
}