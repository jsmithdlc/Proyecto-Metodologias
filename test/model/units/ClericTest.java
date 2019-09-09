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

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
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
      weapons_list.get(i).equipCleric(cleric);
      assertNull(cleric.getEquippedItem());
    }
  }
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Staff && super.equals(obj);
  }
}
