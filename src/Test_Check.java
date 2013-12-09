/**
 * Created with IntelliJ IDEA.
 * User: reecedoyle
 * Date: 05/12/2013
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
public class Test_Check {

	public static void main(String[] args){
		DataHandler dataHandler = new DataHandler();
		int[][] data;
		data = dataHandler.readFile();
		Grid test = new Grid(data[0][0]);
		test.populate(data);
		if(test.solve(1)){
			test.printWhites();
		}
		else{
			System.out.println("UNSOLVABLE");
		}
	}

}
