import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: reecedoyle
 * Date: 02/12/2013
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class DataHandler {

	public DataHandler(){
		// Empty constructor
	}

	public int[][] readFile(){
		String[][] fileData = new String[3][]; // 3 rows for the 3 lines but undefined width/number of values in lines
		String dataIn;
		String delims = "[ ]+";
		int line = 0;
			Scanner stdin= new Scanner(System.in);
			while(stdin.hasNext() && line < 3){
				dataIn = stdin.nextLine();
				fileData[line] = dataIn.split(delims); // Splits all numbers into separate Strings
				line++;
			}
		return convertData(fileData);
	}

	public int[][] convertData(String[][] dataStrings){
		int[][] data = new int[3][]; // 3 rows as with it's String counterpart
		for(int r = 0; r < 3; r++){
			data[r] = new int[dataStrings[r].length]; // set up a new int[][] to match String[][]
			for(int c = 0; c < data[r].length; c++){
				if(!dataStrings[r][c].equals("0")){ // marks the end of the line
					data[r][c] = Integer.parseInt(dataStrings[r][c]); // set int[][] values as the String[][] values
				}
			}
		}
		return data;
	}


}