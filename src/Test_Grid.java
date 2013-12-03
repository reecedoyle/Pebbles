/**
 * Created with IntelliJ IDEA.
 * User: reecedoyle
 * Date: 02/12/2013
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public class Test_Grid {

	public static void main(String[] args){
		int[][] data = {{1,4},{2,3}};
		Grid test = new Grid(2);
		test.populate(data);
		test.visualise();
	}
}
