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

	private File file;

	public DataHandler(){
		// Empty constructor
	}

	public DataHandler(String fileName){
		file = new File(fileName);
	}

	public void setFile(String fileName){
		file = new File(fileName);
	}

	public int[][] readFile(){
		String[][] fileData = new String[3][]; // 3 rows for the 3 lines but undefined width/number of values in lines
		String dataIn;
		String delims = "[ ]+";
		int line = 0;
		if(file.exists()){
			Scanner stdin= new Scanner(System.in);
			while(stdin.hasNext() && line < 3){
				dataIn = stdin.nextLine();
				fileData[line] = dataIn.split(delims); // Splits all numbers into separate Strings
				line++;
			}
		}
		else{
			System.out.println("Cannot locate file. Exiting...");
			System.exit(0);
		}
		return convertData(fileData);
	}

	public int[][] convertData(String[][] dataStrings){
		int[][] data = new int[3][]; // 3 rows as with it's String counterpart
		for(int r = 0; r < 3; r++){
			data[r] = new int[dataStrings[r].length]; // set up a new int[][] to match String[][]
			for(int c = 0; c < data[r].length; c++){
				if(!dataStrings[r][c].equals("0")){ // marks the end of the line
					//System.out.println(r + ":" + c + ":" + data[r][c]);
					data[r][c] = Integer.parseInt(dataStrings[r][c]); // set int[][] values as the String[][] values
				}
			}
		}
		return data;
	}


}
