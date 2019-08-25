package model.map;

import java.util.HashSet;
import java.util.Set;
import model.units.IUnit;

/**
 * This class represents a <i>location</i> in the game's map.
 * <p>
 * A location is simply a graph node with connections to all adjacent positions to it. Every node
 * in the graph contains an id that represents it's position, with rows and columns with numbers
 * (just like a cartesian plane), a list of references to all of it's neighbours and a reference to
 * the unit that's currently in that position (in case there is one).
 * <p>
 * Note that a structure like this let's it's user implement more complicated maps than a simple
 * chess one, but for simplicity, it will be assumed that the distance between any node and it's
 * neighbours will be always 1.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Location {

  private final int row;
  private final int column;
  private final String id;
  private Set<Location> neighbours = new HashSet<>();
  private IUnit unit;

  /**
   * Creates a new location of the game map.
   *
   * @param row
   *     a char representing a row in the map
   * @param column
   *     an int representing the column in the map
   */
  public Location(final int row, final int column) {
    this.row = row;
    this.column = column;
    id = "(" + row + ", " + column + ")";
  }

  /**
   * Checks if a location is equal to another object.
   * <p>
   * Two locations are equal when their id's match. It is assumed that there can't be 2 locations
   * with the same id in the game.
   *
   * @param other
   *     the object to compare this location to
   * @return <code>true</code> if the id's match; <code>false</code> otherwise
   */
  @Override
  public boolean equals(final Object other) {
    return other instanceof Location && row == ((Location) other).row
        && column == ((Location) other).column;
  }

  @Override
  public String toString() {
    return id;
  }

  /**
   * Sets a location as adjacent to this one
   *
   * @param neighbour
   *     the location to be added
   */
  public void addNeighbour(final Location neighbour) {
    neighbour.addTo(this);
    neighbour.neighbours.add(this);
  }

  /**
   * Adds this location as a neighbour to another
   *
   * @param location
   *     location to add this as neighbour
   */
  protected void addTo(final Location location) {
    location.neighbours.add(this);
  }

  /**
   * Checks if a cell is adjacent to this one
   *
   * @param otherLocation
   *     the cell to be checked
   * @return <code>true</code> if the two locations are adjacent; <code>false</code> otherwise
   */
  boolean isNeighbour(final Location otherLocation) {
    return neighbours.contains(otherLocation);
  }

  /**
   * @return the unit currently located in this cell
   */
  public IUnit getUnit() {
    return unit;
  }

  /**
   * Setter for the unit contained in this location.
   *
   * @param unit
   *     the unit to be placed in this cell
   */
  public void setUnit(final IUnit unit) {
    this.unit = unit;
  }

  /**
   * Removes a reighbour from this location.
   *
   * @param neighbour
   *     the neighbour to be removed
   */
  public void removeNeighbour(final Location neighbour) {
    neighbours.remove(neighbour);
    neighbour.neighbours.remove(neighbour);
  }

  /**
   * @return a hash set of this location adjacent cells
   */
  public Set<Location> getNeighbours() {
    return Set.copyOf(neighbours);
  }

  /**
   * Calculates the distance from this location to another
   *
   * @param otherNode
   *     the other location
   * @return the length of the shortest path to the other location
   */
  public double distanceTo(final Location otherNode) {
    return shortestPathTo(otherNode, new HashSet<>());
  }

  /**
   * Gets the shortest path to another node storing a set of already visited nodes
   *
   * @return the distance between the nodes
   */
  private double shortestPathTo(final Location otherNode, final Set<Location> visited) {
    if (otherNode.equals(this)) {
      return 0;
    }
    visited.add(this);
    double distance = Double.POSITIVE_INFINITY;
    for (Location node :
        neighbours) {
      if (!visited.contains(node)) {
        distance = Math.min(distance, 1 + node.shortestPathTo(otherNode, new HashSet<>(visited)));
      }
    }
    return distance;
  }

  /**
   * @return the row of the current location
   */
  public int getRow() {
    return row;
  }

  /**
   * @return the column of the current location
   */
  public int getColumn() {
    return column;
  }

}
