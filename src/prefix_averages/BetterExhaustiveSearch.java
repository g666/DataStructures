/**
 * 
 */
package prefix_averages;



/**
 * Strategia di progettazione dell'algoritmo Prefix Averages basata 
 * sulla ricerca esaustiva.</br></br>
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class BetterExhaustiveSearch implements PrefixAveragesAlgorithm{

	@Override
	public double[] solve(double[] inputArray) {
		double[] newArray = new double[inputArray.length];
		double sum = 0.0;
		for(int i=0; i<inputArray.length; i++){
			sum += inputArray[i];
			newArray[i] = sum/(i+1);
		}
		return newArray;
	}
	
	public static void main(String[] args) {
		BetterExhaustiveSearch s = new BetterExhaustiveSearch();
		double[] d = new double[11000000];
		for(int i=0; i<d.length-1; i++)
			d[i]=i+1;
		long start = System.currentTimeMillis();
		s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
//		s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);s.solve(d);
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);
	}

	

}
