package battleship;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The class to represent the grid of cells (squares).
 * A collection of ships is also kept so the Board
 * can be asked if the game is over.
 * The class is Serializable so that its instance can
 * be saved to a file in binary form using an
 * {@link java.io.ObjectOutputStream} and restored
 * with an {@link java.io.ObjectInputStream}.
 * Because the object holds references to all other
 * objects in the system, no other objects need to
 * be separately saved.
 */
public class Board implements Serializable {

    private int row;
    private int column;
    private Cell[][] cells;
    private List<Ship> ships;

    public Board(int row, int column) {
        this.row = row;
        this.column = column;
        this.cells = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
        this.ships = new ArrayList<>();
    }


    /**
     * Fetch the Cell object at the given location.
     *
     * @param row    row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    public Cell getCell(int row, int column) throws OutOfBoundsException {

        if (row < 0 || column < 0 && row > this.row || column > this.column) {
            throw new OutOfBoundsException(row, column);
        }
        return cells[row][column];
    }


    /**
     * Add a ship to the board. The only current reason that the
     * board needs direct access to the ships is to poll them
     * to see if they are all sunk and the game is over.
     *
     * @param ship the as-yet un-added ship
     * @rit.pre This ship has already informed the Cells of the board
     * that are involved.
     * @see Cell#putShip(Ship)
     */
    public void addShip(Ship ship) throws OverlapException {
        if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {

            for (int i = 0; i < ship.getLength(); i++) {
                cells[ship.getuRow()][ship.getlCol() + i].putShip(ship);
            }
        } else {
            for (int i = 0; i < ship.getLength(); i++) {
                cells[ship.getuRow() + i][ship.getlCol()].putShip(ship);
            }
        }
        ships.add(ship);


    }

    /**
     * @return height, which the row
     */
    public int getHeight() {
        return this.row;
    }

    /**
     * @return the column
     */
    public int getWidth() {
        return this.column;
    }

    /**
     * @return string representation of the object
     */
    public String toString() {
        return "Board " + this.row + " " + this.column;
    }

    /**
     * @param out Prinstream
     * Print out of the board with no cheating
     */
    public void display(PrintStream out) {
        String st;
        System.out.print("  ");
        for(int k = 0; k <column; k++){
            out.print(k + " ");

        }
        out.println();
        for (int i = 0; i < row; i++) {
            st = i + " ";
            for (int j = 0; j < column; j++) {
                st +=  cells[i][j].displayHitStatus() + " ";
            }
            out.println(st);
        }
    }

    /**
     * @param out Prinstream
     * Printout of the cheat display.
     */
    public void fullDisplay(PrintStream out) {
        String st;
        for (int i = 0; i < row; i++) {
            st = "";
            for (int j = 0; j < column; j++) {
                st += cells[i][j].displayChar() + " ";
            }
            out.println(st);
        }
    }

    /**
     * @return true or false if all the ships are sunk.
     */
    public boolean allSunk() {
        for (Ship s : ships)
            if (!s.isSunk()) return false;
        return true;
    }

}
