package battleship;
/**
 * Author: Isaias Villalobos
 * Class for the cell played exception:
 * Date: 3/6/18
 */
public class CellPlayedException extends BattleshipException {
    public static final String ALREADY_HIT = "Co\u00f6rdinates are past board edge.";

    /**
     * @param row integer
     * @param column integer
     * Constructor for the cell played exception
     */
    public CellPlayedException(int row, int column) {
        super(row, column, ALREADY_HIT);
    }

}
