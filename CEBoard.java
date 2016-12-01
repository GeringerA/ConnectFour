import java.lang.reflect.Array;

/**
 * @author Weixiong Zhang
 * @author Adam Geringer
 * @author Bozack Goodirich
 * @author Edgar Woode
 */
public class CEBoard {
   /** number of rows in the array of squares */
   int numberOfRows;
   
   /** number of columns in the array of squares */
   int numberOfColumns;
   
   /** Array that builds the board */
   private Array[][] size;
   
   /** 
    * Constructor for the board
    * @param numberOfRows
    * @param numberOfColumns
    * @param size
    */
    
    public CEBoard(int numberOfRows, int numberOfColumns) {
        if (numberOfRows < 1 || numberOfColumns < 1) {
            throw new IllegalArgumentException("numberOfRows can't be less than 1, nor numberOfColumns be less than 1");
        }
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        
    }
    
    public int getNumberOfRows() {
        return numberOfRows;
    }
    
    public int getNumberOfColumns() {
        return numberOfColumns;
    }
}
