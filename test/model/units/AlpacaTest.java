package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Bow;
import org.junit.jupiter.api.Test;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Test
  public void testAlpacaAttack(){
    alpaca.attack(targetAlpaca);
    assertEquals(10,targetAlpaca.getCurrentHitPoints());
  }

  @Test
  public void testEquipAlpaca(){
    assertNull(alpaca.getEquippedItem());
    Bow bowie = new Bow("arco",10,1,2);
    alpaca.equipItem(bowie);
    assertNull(alpaca.getEquippedItem());
  }

  @Override
  public void addItemOverMaxTest(){ }
}