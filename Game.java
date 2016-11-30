import java.util.Scanner;
/**
 * The program is used to gather different classes to work as a connect-four game
 * You can decide the size of grid and the number of pieces to be connected for victory
 * @author Weixiong Zhang
 *
 */
public class Game {
	/**
	 * The main method prompts the players for basic board and game settings
	 * follow the instructions to enter CORRECT INTEGERS
	 * @param args
	 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
    	System.out.println("Start board setting");
        System.out.print("Please set required connect number of pieces.");
        while (!in.hasNextInt()) {  //Cases that the input is not integer
            System.out.println("Invalid input, the input number must be an integer");
            in.next();
            System.out.print("Please set required connect number of pieces.");
	    }
        int connectNum = in.nextInt(); //The number needed to be connected
        int gridRow = connectNum * 2; //number of rows and columns is the same
        int gridColumn = connectNum * 2;
        System.out.println("Board setting completed, the row and column numbers are " + gridRow + ".");
        System.out.println("You have to connect " + connectNum + " pieces to win. ");
        System.out.println("Game start");
        
        CEBoard Board = new CEBoard(gridRow, gridColumn); //Get a board set from sub-class
        int rowAndColumn = Board.getNumberOfRows(); //get the row/column number
    	
    	char[][] Grid = new char[rowAndColumn][rowAndColumn]; //create the board for the game
    	displayGrid(Grid); //construct the grid
    	playConnectFour(Grid, in, connectNum); //start playing
    }
    
    /**
     * Show the total amount of pieces placed in the board
     * @param Grid
     * @return
     */
    public static int totalPieces(char[][] Grid) {
    	int piececount = 0;
    	for (int i = 0; i < Grid.length; i++) {
    		for (int j = 0; j < Grid[i].length; j++) {
    			if (Grid[i][j] != 0) {
    				piececount ++;
    			}
    		}
    	}
		return piececount;
    }
    
    
    /**
     * Play
     * @param Grid
     * @param input
     * @param connectNum
     */
    public static void playConnectFour(char[][] Grid, Scanner input, int connectNum) {
    	boolean gameOver = false;
    	boolean playersTurn = true; //true, player1's turn; else, player2's turn
    	int columnPosition = 0;
    	int inputNum = 0;
    	char shape; //shape is O or X
    	while (!gameOver) {
    		if (playersTurn) { //true - player 1
    			System.out.print("Player1 drops a piece: ");
    			shape = 'O';
    			if (!input.hasNextInt()) {
    				input.next();
    				System.out.println("Input must be an integer.");
    				System.out.print("Player1 drops a piece: ");
    			}
    		} else { //false - player 2
    			System.out.print("Player2 drops a piece: ");
    			shape = 'X';
    			if (!input.hasNextInt()) {
    				input.next();
    				System.out.println("Input must be an integer.");
    				System.out.print("Player2 drops a piece: ");
    			}
    			
    		}
    		
    		inputNum = input.nextInt(); //get the number of column input, the input number is what the player sees
    		columnPosition = inputNum - 1; //the number in the program, used to work in array and loops
    		
    		while (columnPosition < 0 || columnPosition > Grid.length - 1) { //cases the number is out of range
    			System.out.println("Column position must be in the range.");
    			columnPosition = input.nextInt();
    		}
    		playersTurn = !playersTurn;
    		if (dropChip(Grid, columnPosition, shape)) { //cases that the column is full
    		    playersTurn = !playersTurn; 
    		} else {
    		    displayGrid(Grid); //If it's not full, grid should be shown
    		    if (gameStatus(Grid, columnPosition, shape, connectNum)) { //If connect is achieved
    		    	gameOver = true;
    		    	System.out.println(shape + " player wins!");
    		    }
    		    else if (checkFull(Grid)) { //If the board is full
    		    	gameOver = true;
    		    	System.out.println("The grid is full");
    		    }
    		}
    	}
    	input.close();
    }
    
    /**
     * Check whether the grid is full
     * @param Grid
     * @return
     */
    public static boolean checkFull(char[][] Grid) {
    	for (int i = 0; i<Grid[0].length;i++) {
    		if (Grid[0][i] == 0) { // =0 means that this cell is still blank
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    /**
     * Check the game status
     * @param Grid
     * @param columnPosition
     * @param shape
     * @param connectNum
     * @return
     */
    public static boolean gameStatus(char[][] Grid, int columnPosition, char shape, int connectNum) {
    	int rowPosition = 0;
    	for (int i = 0; i < Grid.length; i++) {
    		if (Grid[i][columnPosition] != 0) {
    			rowPosition = i;
    			break;
    		}
    	}
    	
    	int pieceInGird = totalPieces(Grid); //show the total pieces dropped in the board
    	System.out.println(pieceInGird + " piece(s) in total. ");
    	
    	if (checkColumn(Grid, columnPosition, shape, rowPosition, connectNum)) { //see whether connected vertically
    		return true;
    	}
    	if (checkRow(Grid, columnPosition, shape, rowPosition, connectNum)) { //see whether connected horizontally
    		return true;
    	}
    	if (checkDiagonal(Grid, columnPosition, shape, rowPosition, connectNum)) { //see whether connected diagonally
    		return true;
    	}
    	if (checkLittleDiagonal(Grid, columnPosition, shape, rowPosition, connectNum)) { //see special cases of connecting diagonally
    		return true;
    	}
    	return false;
    }
    

    /**
     * see special cases of connecting diagonally, row increases; column decreases and row decreases; column increases
     * @param Grid
     * @param columnPosition
     * @param shape
     * @param rowPosition
     * @param connectNum
     * @return
     */
    public static boolean checkLittleDiagonal(char[][] Grid, int columnPosition, char shape, int rowPosition, int connectNum) {
    	int chipCounter = 1;
    	for (int i = rowPosition + 1, j = columnPosition - 1; i < Grid.length && j >= 0; i++, j--) {
    		if (shape == Grid[i][j]) {
    			chipCounter ++;
    		} else {
    			break;
    		}
    	}
    	if (chipCounter >= connectNum) {
    		return true;
    	}
    	for (int i = rowPosition - 1, j = columnPosition + 1; i >= 0 && j < Grid[0].length; i--, j++) {
    		if (shape == Grid[i][j]) {
    			chipCounter ++;
    		} else {
    			break;
    		}
    	}
    	if (chipCounter >= connectNum) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Diagonally, row and column increase and decrease at the same time
     * @param Grid
     * @param columnPosition
     * @param shape
     * @param rowPosition
     * @param connectNum
     * @return
     */
    public static boolean checkDiagonal(char[][] Grid, int columnPosition, char shape, int rowPosition, int connectNum) {
    	int chipCounter = 1;
    	for (int i = rowPosition - 1, j = columnPosition - 1; i >= 0 && j >= 0; i--, j--) {
    		if (shape == Grid[i][j]) {
    			chipCounter ++;
    		} else {
    			break;
    		}
    	}
    	if (chipCounter >= connectNum) {
    		return true;
    	}
    	for (int i = rowPosition + 1, j = columnPosition + 1; i < Grid.length && j < Grid.length; i++, j++) {
    		if (shape == Grid[i][j]) {
    			chipCounter ++;
    		} else {
    			break;
    		}
    	}
    	if (chipCounter >= connectNum) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Connect in a row
     * @param Grid
     * @param columnPosition
     * @param shape
     * @param rowPosition
     * @param connectNum
     * @return
     */
    public static boolean checkRow(char[][] Grid, int columnPosition, char shape, int rowPosition, int connectNum) {
    	int chipCounter = 1;
        for (int i = columnPosition - 1; i >= 0; i--) {
        	if (shape == Grid[rowPosition][i]) {
        		chipCounter ++;
        	} else {
        		break;
        	}
        }
        if (chipCounter >= connectNum) {
        	return true;
        }
        for (int i = columnPosition + 1; i < Grid[0].length; i ++) {
        	if (shape == Grid[rowPosition][i]) {
        		chipCounter ++;
        	} else {
        		break;
        	}
        }
        if (chipCounter >= connectNum) {
        	return true;
        }
        return false;
    }
    
    /**
     * Connect in a column
     * @param Grid
     * @param columnPosition
     * @param shape
     * @param rowPosition
     * @param connectNum
     * @return
     */
    public static boolean checkColumn(char[][] Grid, int columnPosition, char shape, int rowPosition, int connectNum) {
    	int chipCounter = 1;
    	
    	if ((rowPosition + connectNum) <= connectNum*2) {
    		for (int i = rowPosition + 1; i <= (rowPosition +(connectNum - 1)); i++) {
    			if (shape == Grid[i][columnPosition]) {
    				chipCounter ++;
    			} else {
    				break;
    			}
    		}
    	}
    	if (chipCounter == connectNum) {
    		return true;
    	}
		return false;
    }
    
    /**
     * See whether the column is full
     * @param Grid
     * @param columnPosition
     * @param shape
     * @return
     */
    public static boolean dropChip(char[][] Grid, int columnPosition, char shape) {
		for (int i = Grid.length - 1; i >= 0; i --) {
			if (Grid[i][columnPosition] == 0) {
				Grid[i][columnPosition] = shape;
				return false;
			}
		}
    	System.out.println("Column full" + shape + "player, try again.");
    	return true;
    }
    
    
    /**
     * Show the grid
     * @param Grid
     */
    public static void displayGrid(char[][] Grid) {
    	for (int i = 0; i < Grid.length; i++) {
    		for (int j = 0; j< Grid[i].length; j++) {
    			System.out.print("|" + Grid[i][j]);
    			
    		}
    		System.out.println("|");
    	}
    	for (int i = 0; i<Grid.length; i++) {
    		System.out.print(" " + (i+1));
    	}
    	System.out.println();
    }
}