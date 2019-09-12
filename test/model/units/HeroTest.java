package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Spear;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipHeroTest() {
    hero.addItem(spear);
    assertNull(hero.getEquippedItem());
    spear.equipHero(hero);
    assertEquals(spear, hero.getEquippedItem());
  }
  @Test
  public void equipHeroWrongTest(){
    assertNull(hero.getEquippedItem());
    for(int i = 0; i< weapons_list.size();i++){
      if(weapons_list.get(i).equals(spear)){
        continue;
      }
      hero.addItem(weapons_list.get(i));
      weapons_list.get(i).equipHero(hero);
      assertNull(hero.getEquippedItem());
      hero.removeItem(weapons_list.get(i));
    }
    for(int i = 0; i< books_list.size();i++) {
      hero.addItem(books_list.get(i));
      books_list.get(i).equipHero(hero);
      assertNull(hero.getEquippedItem());
      hero.removeItem(books_list.get(i));
    }
  }
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Spear && super.equals(obj);
  }
}