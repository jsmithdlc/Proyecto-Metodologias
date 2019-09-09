package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Bow;
import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipArcherTest() {
    assertNull(archer.getEquippedItem());
    bow.equipArcher(archer);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Test
  public void equipArcherWrongTest(){
    assertNull(archer.getEquippedItem());
    for(int i = 0; i< weapons_list.size();i++){
      if(weapons_list.get(i).equals(bow)){
        continue;
      }
      weapons_list.get(i).equipArcher(archer);
      assertNull(archer.getEquippedItem());
    }
  }
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Bow && super.equals(obj);
  }
}