package model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test set for the field component of the game model.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class FieldTest {

  private Field map;

  @BeforeEach
  void setUp() {
    map = new Field();
    map.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Checks if the map was created correctly
   */
  @Test
  public void testMapConstruction() {
    assertEquals(9, map.getMap().size());
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        assertTrue(map.getMap().containsKey("(" + row + ", " + col + ")"));
        checkConnections(row, col);
      }
    }
  }

  /**
   * Checks for the connections of a cell
   *
   * @param row
   *     the number that identifies the row of the cell
   * @param col
   *     the number that identifies the column of the cell
   */
  private void checkConnections(final int row, final int col) {
    Location[] neighbours = new Location[]{new Location(row - 1, col), new Location(row + 1, col),
        new Location(row, col - 1), new Location(row, col + 1)};
    for (Location neighbour :
        neighbours) {
      final String key = neighbour.toString();
      if (map.getMap().containsKey(key)) {
        assertTrue(map.getCell(row, col).isNeighbour(map.getMap().get(key)));
      }
    }
  }

  /**
   * Checks if a cell can be retrieved correctly
   */
  @Test
  public void testGetCell() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        assertEquals(new Location(row, col), map.getCell(row, col));
      }
    }
  }

  @Test
  public void testConnectedness() {
    for (int i = 0; i < 50; i++) {
      Field randomField = new Field();
      randomField.addCells(false, new Location(0, 0), new Location(0, 1), new Location(0, 2),
          new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
          new Location(2, 1), new Location(2, 2));
      assertTrue(randomField.isConnected());
    }
  }

  /**
   * Checks that the distance between 2 cells is calculated correctly.
   */
  @Test
  public void testDistances() {
    Location location00 = map.getCell(0, 0);
    assertEquals(0, location00.distanceTo(location00));
    assertEquals(1, location00.distanceTo(map.getCell(0, 1)));
    assertEquals(1, location00.distanceTo(map.getCell(1, 0)));
    assertEquals(2, location00.distanceTo(map.getCell(0, 2)));
    assertEquals(2, location00.distanceTo(map.getCell(1, 1)));
    assertEquals(3, location00.distanceTo(map.getCell(1, 2)));
    assertEquals(4, location00.distanceTo(map.getCell(2, 2)));
  }

  @Test
  public void testRemoveConnection() {
    Location
        cell00 = map.getCell(0, 0),
        cell01 = map.getCell(0, 1),
        cell10 = map.getCell(1, 0);
    assertTrue(map.checkConnection(cell00, cell01));
    assertTrue(map.checkConnection(cell00, cell10));
    assertFalse(map.checkConnection(cell01, cell10));

    map.removeConnection(map.getCell(0, 0), map.getCell(0, 1));
    assertFalse(map.checkConnection(cell00, cell01));
    assertTrue(map.checkConnection(cell00, cell10));
    assertFalse(map.checkConnection(cell01, cell10));

    map.removeConnection(map.getCell(0, 0), map.getCell(1, 0));
    assertFalse(map.checkConnection(cell00, cell01));
    assertTrue(map.checkConnection(cell00, cell10));
    assertFalse(map.checkConnection(cell01, cell10));
  }
}