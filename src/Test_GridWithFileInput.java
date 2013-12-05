/**
 * Created with IntelliJ IDEA.
 * User: reecedoyle
 * Date: 04/12/2013
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class Test_GridWithFileInput {

	public static void main(String[] args){
		DataHandler dataHandler = new DataHandler();
		int[][] data;
		data = dataHandler.readFile();
		Grid test = new Grid(data[0][0]);
		test.populate(data);
		test.visualise();
	}

}
