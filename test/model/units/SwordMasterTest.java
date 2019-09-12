package model.units;

import model.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  @Test
  @Override
  public void equipSwordMasterTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.addItem(sword);
    sword.equipSwordMaster(swordMaster);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  @Test
  public void equipSwordMasterWrongTest(){
    assertNull(swordMaster.getEquippedItem());
    for(int i = 0; i< weapons_list.size();i++){
      if(weapons_list.get(i).equals(sword)){
        continue;
      }
      swordMaster.addItem(weapons_list.get(i));
      weapons_list.get(i).equipSwordMaster(swordMaster);
      assertNull(swordMaster.getEquippedItem());
      swordMaster.removeItem(weapons_list.get(i));
    }
  }
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Sword && super.equals(obj);
  }
}