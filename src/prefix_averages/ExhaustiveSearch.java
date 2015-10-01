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
public class ExhaustiveSearch implements PrefixAveragesAlgorithm{

	@Override
	public double[] solve(double[] inputArray) {
		double[] newArray = new double[inputArray.length];
		for(int i=0; i<inputArray.length; i++){
			double sum = 0.0;
			for(int j=0; j<=i; j++)
				sum+=inputArray[j];
			newArray[i] = sum/(i+1);
		}
		return newArray;
	}
	
	public static void main(String[] args) {
		ExhaustiveSearch s = new ExhaustiveSearch();
//		double[] d = new double[]{
//				10,100,1000,2000,5000,10000,20000,50000,Math.pow(10, 5),
//				2*Math.pow(10, 5),5*Math.pow(10, 5),Math.pow(10, 6),
//				Math.pow(10, 7)
//		};
		double[] d = new double[100000];
		for(int i=0; i<d.length-1; i++)
			d[i]=i+1;
		long start = System.nanoTime();
		s.solve(new double[]{34});
//		double sum = 0.0;
		long end = System.nanoTime();
		System.out.println(((double)end-start));
		
		
	}

	

}
