package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Staff;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;
  private Alpaca alpaca;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
    alpaca = new Alpaca(30,3,field.getCell(0,1));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipClericTest() {
    assertNull(cleric.getEquippedItem());
    cleric.addItem(staff);
    staff.equipCleric(cleric);
    assertEquals(staff, cleric.getEquippedItem());
  }

  @Test
  public void equipClericWrongTest(){
    assertNull(cleric.getEquippedItem());
    for(int i = 0; i< weapons_list.size();i++){
      if(weapons_list.get(i).equals(staff)){
        continue;
      }
      cleric.addItem(weapons_list.get(i));
      weapons_list.get(i).equipCleric(cleric);
      assertNull(cleric.getEquippedItem());
      cleric.removeItem(weapons_list.get(i));
    }
  }


  @Test
  public void testHealing(){
    cleric.addItem(staff);
    cleric.equipItem(staff);
    alpaca.setCurrentHitPoints(1);
    cleric.attack(alpaca);
    assertEquals(1+staff.getPower(),alpaca.getCurrentHitPoints());
  }

  @Test
  public void testOverHeal(){
    cleric.addItem(staff);
    cleric.equipItem(staff);
    alpaca.setCurrentHitPoints(25);
    cleric.attack(alpaca);
    assertEquals(30,alpaca.getCurrentHitPoints());
  }

  @Test
  public void testHealDead(){
    cleric.equipItem(staff);
    alpaca.setCurrentHitPoints(0);
    cleric.attack(alpaca);
    assertEquals(0,alpaca.getCurrentHitPoints());
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Staff && super.equals(obj);
  }
}
