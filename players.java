/**
 * @author Weixiong Zhang
 * @author Adam Geringer
 * @author Bozack Goodirich
 * @author Edgar Woode
 */
public class players {
   /** player games won */
   private int gamesWon;
   
   /** player's name */
   private String name;
   
   /**
    * Constructor for player 
    * @param gamesWon
    * @param name
    */
   public players(int gamesWon, String name) {
      if (gamesWon < 0) {
             throw new IllegalArgumentException("gamesWon can't be negative");
         }
      this.gamesWon = gamesWon;
      this.name = name;
   }
   
   public int getGamesWon() {
       return gamesWon;
   }
   
   public void updateGamesWon() {
       gamesWon++;
   }
   
   public String getName() {
       return name;
   }
   
   public String toString() {
	   String s = " " + name;
	   return s;
   }

}
