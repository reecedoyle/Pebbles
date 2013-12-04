/**
 * Created with IntelliJ IDEA.
 * User: reecedoyle
 * Date: 02/12/2013
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
public class Grid {

	final static int ROW = 0;
	final static int COL = 1;
    private int size; // length of row/col
    private int[][] grid; // Array representation of the grid
    private boolean[][] complete; // Indicates if rows/cols are complete. Row 1 is rows, row 2 is cols.

    public Grid(){
        // Empty constructor
    }

    public Grid(int size){
        this.size = size;
		this.grid = new int[size][size];
        this.complete = new boolean[2][size];
    }

	public int getSize(){
		return size;
	}

	public int[][] getGrid(){
		return grid;
	}

	public boolean[][] getComplete(){
		return complete;
	}

	public void populate(int[][] inputArray){
		int temp,row, col;
		String position;
		for(int r = 0; r < 2; r++){
			for(int c = 0; c < inputArray[r].length; c ++){
				temp = inputArray[r][c] - 1;
				if(temp < 0){ // No more values to allocate (possibly sent array of 0s to indicate no input)
					return;
				}
				else {
					position = Integer.toString(temp, size); // convert to the base of the size e.g. 9 base 4 -> "21"
					while(position.length()<2){
						position = "0" + position;
					}
					row = Character.getNumericValue(position.charAt(0));
					col = Character.getNumericValue(position.charAt(1));
					grid[row][col] = r*(-2) + 1; // to give 1 for first row (white) and -1 for second row (black)
				}
			}
		}
	}


	public boolean checkComplete(){ // Checks if the whole grid is completed by summing and checking for 0s
		boolean total = true;
		for(int r = 0; r < 2; r++){
			for(int c = 0; c < size; c++){
				complete[r][c] = (sum(c, r) == 0) && !checkEmpty(c, r);
				total = total && complete[r][c];
			}
		}
		return total;
	}

	private boolean checkEmpty(int index, int type){ // returns true if empty (0s)
		switch(type){
			case ROW: // check row
				for(int c = 0; c < size; c++){
					if(grid[index][c] == 0){
						return true;
					}
				} break;
			default: // check col
				for(int r = 0; r < size; r++){
					if(grid[r][index] == 0){
						return true;
					}
				} break;
		}
		return false;
	}

	private int sum(int index, int type){ // where type is ROW or COL and index is that ROW/COL index
		int sum = 0;
		switch(type){
			case ROW: // sum row
				for(int c = 0; c < size; c++){
					sum += grid[index][c];
				} break;
			default: // sum col
				for(int r = 0; r < size; r++){
					sum += grid[r][index];
				} break;
		}
		return sum;
	}

	public void visualise(){
		for(int r = 0; r < size; r++){
			for(int c = 0; c < size; c++){
				System.out.print(grid[r][c]);
			}
			System.out.print("\n");
		}
	}

	/* CHECKING FUNCTIONS */

	public boolean check(int r, int c){

		/* check statements:
		- current sq is blank
		- 2 in a row/col
		- sandwiched
		- one colour has reached capacity in a row/col

		try to do more than one of these at a time. So if sq to left is blank, don't check 2 in a row or row sandwich
		 */

		return false;
	}


}
