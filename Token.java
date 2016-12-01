/**
 * @author Edgar Woode
 * @author Adam Geringer
 * @author Bozack Goodirich
 * @author Weixiong Zhang
 */
public class Token {
    /** startRow */
    //rivate int startRow;
    
    /** startColumn */
    //private int startColumn;
    
    /** number of tokens connected */
    private int numTokensConn;
    
    /**
     * Constructor for token object
     * @param startRow
     * @param startCol
     */
     
     public Token() {
         /**if (startRow < 0 || startCol < 0) {
             throw new IllegalArgumentException("length can't be less than 1, startRow and startCol can't be negative");
         }
         this.startRow = startRow;
         this.startColumn = startCol;*/
         this.numTokensConn = numTokensConn;
         this.playerOneToken = playerOneToken;
         this.playerTwoToken = playerTwoToken;
     }
     
     /**public int getStartRow() {
         return startRow;
     }
     
     public int getStartCol() {
         return startColumn;
     }*/
     
     public int getNumTokensConn() {
         return numTokensConn;
     }
}
