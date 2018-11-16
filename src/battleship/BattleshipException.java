package battleship;

/**
 * Author: Isaias Villalobos
 * Class for the battle ships exception:
 * Date: 3/6/18
 */
public class BattleshipException extends Exception {

    public static final int UNSET = -1;
    public final int row;
    public final int column;

    /**
     * @param row integer
     * @param column integer
     * @param message string
     * Constructor for the Battleship exception.
     */
    public BattleshipException(int row, int column, String message) {
        super(message + ", row=" + row + ", column=" + column);
        this.row = row;
        this.column = column;

    }

    /**
     * @param message string
     * Other batleship exception with only a string message.
     */
    public BattleshipException(String message ){
        super(message);
        this.row = UNSET;
        this.column = UNSET;
    }

}
