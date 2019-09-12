package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Axe;
import model.items.Bow;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipFighterTest() {
    fighter.addItem(axe);
    assertNull(fighter.getEquippedItem());
    axe.equipFighter(fighter);
    assertEquals(axe, fighter.getEquippedItem());
  }

  @Test
  public void equipFighterWrongTest(){
    assertNull(fighter.getEquippedItem());
    for(int i = 0; i< weapons_list.size();i++){
      if(weapons_list.get(i).equals(axe)){
        continue;
      }
      fighter.addItem(weapons_list.get(i));
      weapons_list.get(i).equipFighter(fighter);
      assertNull(fighter.getEquippedItem());
      fighter.removeItem(weapons_list.get(i));
    }
    for(int i = 0; i< books_list.size();i++) {
      fighter.addItem(books_list.get(i));
      books_list.get(i).equipFighter(fighter);
      assertNull(fighter.getEquippedItem());
      fighter.removeItem(books_list.get(i));
    }
  }
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Axe && super.equals(obj);
  }
}