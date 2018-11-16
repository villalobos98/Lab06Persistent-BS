package battleship;

import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 */
public class Cell implements Serializable {

    /**
     * Character to display for a ship that has been entirely sunk
     */
    public static final char SUNK_SHIP_SECTION = '*';

    /**
     * Character to display for a ship that has been hit but not sunk
     */
    public static final char HIT_SHIP_SECTION = '☐';

    /**
     * Character to display for a water cell that has been hit
     */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    int row, column;
    Ship ship;
    boolean hit;

    /**
     * @param row integer
     * @param column integer
     *Constructor for the cell.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.ship = null;
        this.hit = false;
    }


    /**
     * Place a ship on this cell. Of course, ships typically cover
     * more than one Cell, so the same ship will usually be passed
     * to more than one Cell's putShip method.
     *
     * @param ship the ship that is to be on this Cell
     * @throws OverlapException if there is already a ship here.
     */
    public void putShip(Ship ship) throws OverlapException {


        if (this.ship == null) {
            this.ship = ship;
        }
        else {
            throw new OverlapException(row,column);
        }
    }

    /**
     * @throws CellPlayedException
     * hits the ship.
     */
    public void hit() throws CellPlayedException {
        if (isHit()) {
            throw new CellPlayedException(row, column);
        }
        this.hit = true;
        if(ship != null)
            ship.hit();

    }

    /**
     * @return the regular board display
     */
    public char displayHitStatus() {
        char c = displayChar();
        if (c == HIDDEN_SHIP_SECTION) {
            return PRISTINE_WATER;
        }
        return c;
    }

    /**
     * @return the cheat display
     */
    public char displayChar() {
        if (!isHit()) {
            if (this.ship == null)
                return PRISTINE_WATER;
            else {
                return HIDDEN_SHIP_SECTION;
            }

        } else if (this.ship == null) {
            return HIT_WATER;
        } else if (this.ship.isSunk()) {
            return SUNK_SHIP_SECTION;
        } else {
            return HIT_SHIP_SECTION;
        }

    }

    /**
     * @return true or false is ship is hit
     */
    public boolean isHit() {
        return this.hit;
    }

}
