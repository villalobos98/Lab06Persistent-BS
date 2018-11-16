package battleship;
/**
 * Author: Isaias Villalobos
 * Class for the overlap exception:
 * Date: 3/6/18
 */
public class OverlapException extends BattleshipException {
    public static final String OVERLAP = "Ships placed in overlapping positions";

    /**
     * @param row integer
     * @param column integer
     * Constructor for the overlap exception
     */
    public OverlapException(int row, int column) {
        super(row, column, OVERLAP);
    }
}
