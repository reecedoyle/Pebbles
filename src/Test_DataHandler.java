import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: reecedoyle
 * Date: 02/12/2013
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class Test_DataHandler {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "/Users/reecedoyle/Documents/COMP2008/coursework_2/Pebbles/test.txt";
		DataHandler dataHandler = new DataHandler(fileName);
		int[][] data;
		data = dataHandler.readFile();
		for(int r = 0; r < 3; r++){
			for(int c = 0; c < data[r].length; c++){
				System.out.print(data[r][c]+",");
			}
			System.out.print("\n");
		}
	}
}
