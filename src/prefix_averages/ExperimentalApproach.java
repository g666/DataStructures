/**
 * 
 */
package prefix_averages;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import util.Pair;

/**
 * 
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class ExperimentalApproach  {
	
	public static void report(int[] numerOfInput, int times, String file, PrefixAveragesAlgorithm alg) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSZ");
		FileOutputStream fos = new FileOutputStream(file);
		for(int k=0; k<numerOfInput.length; k++){
			int num = numerOfInput[k];
			int timeI = 0;
			for(int i=0; i<times; i++){
				double[] d = new double[num];
				for(int t=0; t<d.length-1; t++)
					d[t]=t+1;
				GregorianCalendar g = new GregorianCalendar();
				String s = sdf.format(g.getTime());
				long start = System.currentTimeMillis();
				alg.solve(d);
				long end = System.currentTimeMillis();
				GregorianCalendar g1 = new GregorianCalendar();
				String e = sdf.format(g1.getTime());
				System.out.println(num+": "+s+" --> "+e);
				timeI+=(int)end-start;
			}
			
			int average = timeI/times;
			fos.write((num+","+String.valueOf(average)+"\n").getBytes());
		}
		fos.flush();
		fos.close();
		
	}

	private static List<Pair<Double, Double>> readResult(String file) throws Exception{
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = buf.readLine();
		List<Pair<Double, Double>> l = new ArrayList<Pair<Double,Double>>();
		while(line!=null){
			StringTokenizer tok = new StringTokenizer(line, ",");
			if(tok.countTokens()==2){
				String s1 = tok.nextToken();
				String s2 = tok.nextToken();
				l.add(new Pair<Double, Double>(new Double(s1), new Double(s2)));
			}
			
			line = buf.readLine();
		}
		buf.close();
		
		return l;
	}
	
	public static void showResult(String file1, String file2){
		try {

			XYSeries xy1 = new XYSeries("ExhaustiveSearch");
			List<Pair<Double, Double>> l = readResult(file1);
			for(int i=0; i<l.size(); i++){
				Pair<Double, Double> p = l.get(i);
				xy1.add(p.getFirst(), p.getSecond());
			}
			XYSeries xy2 = new XYSeries("BetterExhaustiveSearch");
			l = readResult(file2);
			for(int i=0; i<l.size(); i++){
				Pair<Double, Double> p = l.get(i);
				xy2.add(p.getFirst(), p.getSecond());
			}
			XYSeriesCollection coll = new XYSeriesCollection();
			coll.addSeries(xy1);
			coll.addSeries(xy2);
			
			JFreeChart chart=ChartFactory.createXYLineChart(
					"ExhaustiveSearch vs. BetterExhaustiveSearch",
					"Length of input array",
					"Time (sec)",
					coll,
					PlotOrientation.VERTICAL,
					true,true,false);
			ChartPanel panel = new ChartPanel(chart);
			JFrame f = new JFrame();
			f.setContentPane(panel);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.pack();
			f.setVisible(true);
		}
		catch (Exception i)
		{
			i.printStackTrace();
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) throws Exception {
		int[] inp = new int[]{
				(int) (1*Math.pow(10, 2)),(int) (2*Math.pow(10, 2)),(int) (5*Math.pow(10, 2)),
				(int) (1*Math.pow(10, 3)),(int) (2*Math.pow(10, 3)),(int) (5*Math.pow(10, 3)),
				(int) (1*Math.pow(10, 4)),(int) (2*Math.pow(10, 4)),(int) (5*Math.pow(10, 4)),
				(int) (1*Math.pow(10, 5)),(int) (2*Math.pow(10, 5)),(int) (5*Math.pow(10, 5)),
				(int) (1*Math.pow(10, 6)),(int) (2*Math.pow(10, 6)),(int) (5*Math.pow(10, 6)),
				(int) (1*Math.pow(10, 7))
//				(int) (1*Math.pow(10, 8)),(int) (2*Math.pow(10, 8)),(int) (5*Math.pow(10, 8))
		};
		ExperimentalApproach.report(inp
				, 1, "src/prefix_averages/report.txt", new ExhaustiveSearch());
//		ExperimentalApproach.report(inp
//				, 1, "src/prefix_averages/report_better.txt", new BetterExhaustiveSearch());
		
//		ExperimentalApproach.showResult("src/prefix_averages/report.txt", 
//				"src/prefix_averages/report_better.txt");
		
	}
}
