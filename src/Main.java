/**
 * Created with IntelliJ IDEA.
 * User: reecedoyle
 * Date: 10/12/2013
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */
public class Main {
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
