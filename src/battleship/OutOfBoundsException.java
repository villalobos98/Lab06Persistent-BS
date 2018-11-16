package battleship;
/**
 * Author: Isaias Villalobos
 * Class for the out of bounds exception:
 * Date: 3/6/18
 */
public class OutOfBoundsException extends BattleshipException {
    public static final String PAST_EDGE = "Co\u00f6rdinates are past board edge.";

    /**
     * @param row integer
     * @param column integer
     * Contructor for the out of bounds exception
     */
    public OutOfBoundsException(int row, int column) {
        super(row, column, PAST_EDGE);
    }
}
