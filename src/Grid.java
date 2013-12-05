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
	final static int BLANK = 0;
	final static int BLACK = -1;
	final static int WHITE = 1;
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
		for(int r = 1; r < 3; r++){
			for(int c = 0; c < inputArray[r].length; c ++){
				temp = inputArray[r][c] - 1;
				if(temp < 0){ // No more values to allocate (possibly sent array of 0s to indicate no input)
					// ignore it as it's not a real value
				}
				else {
					position = Integer.toString(temp, size); // convert to the base of the size e.g. 9 base 4 -> "21"
					while(position.length()<2){
						position = "0" + position;
					}
					row = Character.getNumericValue(position.charAt(0));
					col = Character.getNumericValue(position.charAt(1));
					grid[row][col] = (r-1)*(-2) + 1; // to give 1 for first row (white) and -1 for second row (black)
				}
			}
		}
	}


	public boolean checkComplete(){ // Checks if the whole grid is completed by summing and checking for 0s
		boolean total = true;
		for(int r = 0; r < 2; r++){
			for(int c = 0; c < size; c++){
				complete[r][c] = (sum(r, c) == 0) && !checkEmpty(c, r);
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

	private int sum(int type, int index){ // where type is ROW or COL and index is that ROW/COL index
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
				if(grid[r][c] == -1){
					System.out.print('b');
				}
				else if(grid[r][c] == 1){
					System.out.print('w');
				}
				else{
					System.out.print('-');
				}
			}
			System.out.print("\n");
		}
		System.out.println("$$$$$$$$$$");
	}

	/* CHECKING FUNCTIONS */

	public void checkAll(){ // need to somehow sum the checks and iterate through grid in a logical way
		int r = 0, c = 0;
		while(r < grid.length && c < grid[r].length){
			if(check(r,c) > 0){ // if the square was changed
				if(r < grid.length - 1){
					if(c < grid[r].length - 1){
						r++; c++;
					}
					else{
						r++; c = 0;
					}
				}
			}
			else{
				if(c < grid[r].length - 1){ // if not at the end of the line, move along the row
					c++;
				}
				else{ // if at the end of the line, move to the start of the next line
					r++; c = 0;
				}
			}
		}
	}

	public int check(int r, int c){ // returns number of changes made

		//this.visualise();
		/* check statements:
		- current sq is blank
		- 2 in a row/col
		- sandwiched
		- one colour has reached capacity in a row/col

		try to do more than one of these at a time. So if sq to left is blank, don't check 2 in a row or row sandwich
		Also, when checking affected, don't recheck the one we went from
		 */

		if(grid[r][c] == BLANK){ // if the square isn't already filled, inspect the conditions

			// sandwiched vertically: not top or bottom row, up and down are the same non-zero
			if((r > 0 && r < (grid.length-1)) && (grid[r-1][c] == grid[r+1][c]) && (grid[r-1][c] != BLANK) && (grid[r+1][c] != BLANK)){
				grid[r][c] = grid[r-1][c] * -1;
				return 1 + checkAffected(r,c);
			}

			// sandwiched horizontally: not left or right col, left and right are the same non-zero
			if((c > 0 && c < (grid[r].length-1)) && (grid[r][c-1] == grid[r][c+1]) && (grid[r][c-1] != BLANK) && (grid[r][c+1] != BLANK)){
				grid[r][c] = grid[r][c-1] * -1;
				return 1 + checkAffected(r,c);
			}

			// pair of same-coloured pebbles above
			if((r > 1) && (grid[r-1][c] == grid[r-2][c]) && (grid[r-1][c] != 0) && (grid[r-2][c] != 0)){
				grid[r][c] = grid[r-1][c] * -1;
				return 1 + checkAffected(r,c);
			}

			// pair of same-coloured pebbles below
			if((r < grid.length-2) && (grid[r+1][c] == grid[r+2][c]) && (grid[r+1][c] != 0) && (grid[r+2][c] != 0)){
				grid[r][c] = grid[r+1][c] * -1;
				return 1 + checkAffected(r,c);
			}

			// pair of same-coloured pebbles to the left
			if((c > 1) && (grid[r][c-1] == grid[r][c-2]) && (grid[r][c-1] != 0) && (grid[r][c-2] != 0)){
				grid[r][c] = grid[r][c-1] * -1;
				return 1 + checkAffected(r,c);
			}

			// pair of same-coloured pebbles to the right
			if((c < grid[r].length-2) && (grid[r][c+1] == grid[r][c+2]) && (grid[r][c+1] != 0) && (grid[r][c+2] != 0)){
				grid[r][c] = grid[r][c+1] * -1;
				return 1 + checkAffected(r,c);
			}
			// row or column are already at maximum capacity for a colour
			return checkLine(ROW, r) + checkLine(COL, r);
		}
		return 0;
	}

	public int checkLine(int line, int index){
		int lineSum = sum(line, index);
		int lineZeroCount = 0;
		boolean[] lineBlanks = new boolean[grid[index].length]; // store position of blank squares in the row
		int sum = 0; // to count the changes made

		switch (line){
			case ROW:
				for(int i = 0; i < grid[index].length; i++){ // grid.length should be the same as grid[r].length
					if(grid[index][i] == BLANK){ // counts the blanks and stores the index of them
						lineZeroCount += 1;
						lineBlanks[i] = true;
					}
				}
				// |lineSum| < lineZeroCount = normal/unfilled
				// |lineSum| == lineZeroCount = at least one colour complete
				// |lineSum| > lineZeroCount = unsolvable
				if(StrictMath.abs(lineSum) == lineZeroCount){ // at least one colour complete
					if(lineSum > 0){
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							if(lineBlanks[i]){
								grid[index][i] = BLACK;
								sum++;
							}
						}
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							sum += checkAffected(index, i);
						}
					}
					else if(lineSum < 0){
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							if(lineBlanks[i]){
								grid[index][i] = WHITE;
								sum++;
							}
						}
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							sum += checkAffected(index, i);
						}
					}
					// add extra statements to check the other conditions above like "unsolvable"
				}
				else if(StrictMath.abs(lineSum) > lineZeroCount){
					System.out.println("UNSOLVABLE");
					System.exit(0);
				}
			case COL:
				for(int i = 0; i < grid.length; i++){ // grid.length should be the same as grid[r].length
					if(grid[i][index] == BLANK){ // counts the blanks and stores the index of them
						lineZeroCount += 1;
						lineBlanks[i] = true;
					}
				}
				// |lineSum| < lineZeroCount = normal/unfilled
				// |lineSum| == lineZeroCount = at least one colour complete
				// |lineSum| > lineZeroCount = unsolvable
				if(StrictMath.abs(lineSum) == lineZeroCount){ // at least one colour complete
					if( lineSum > 0){
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							if(lineBlanks[i]){
								grid[i][index] = BLACK;
								sum++;
							}
						}
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							sum += checkAffected(i, index);
						}
					}
					else if(lineSum < 0){
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							if(lineBlanks[i]){
								grid[i][index] = WHITE;
								sum++;
							}
						}
						for(int i = 0; i < lineBlanks.length; i++){ // change all blanks to the missing colour
							sum += checkAffected(i, index);
						}
					}
					// add extra statements to check the other conditions above like "unsolvable"
				}
				else if(StrictMath.abs(lineSum) > lineZeroCount){
					System.out.println("UNSOLVABLE");
					System.exit(0);
				}
		}
		return sum;
	}

	// returns the number of changes made, applies the change() function to all squares affected by the input square
	public int checkAffected(int r, int c){
		// cycle through affected adding up their checks
		int sum = 0;
		for(int i = 0; i < grid[r].length; i++){ // cycle through the row
			if(i != c){
				sum += check(r,i);
			}
		}
		for(int i = 0; i < grid.length; i++){ // cycle through the col
			if(i != r){
				sum += check(i,c);
			}
		}
		return sum;
	}

}
