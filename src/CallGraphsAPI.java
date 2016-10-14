import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import drawGraphs.BarChartValue;
import drawGraphs.DrawBarChart;
import drawGraphs.PieChartValue;
import drawGraphs.DrawPieChart;

public class CallGraphsAPI {

	public static void main(String[ ] args) {
		List<PieChartValue> pieValues = new ArrayList<PieChartValue>();	
		pieValues.add(new PieChartValue("IPhone 5s", 20.0));
		pieValues.add(new PieChartValue("SamSung Grand", 20.0));
		pieValues.add(new PieChartValue("MotoG", 40.0));
		pieValues.add(new PieChartValue("Nokia Lumia", 10.0));
		
		callDrawPieChart(pieValues);
		
		List<BarChartValue> barValues = new ArrayList<BarChartValue>();
		barValues.add(new BarChartValue (1, "speed"));
		barValues.add(new BarChartValue (3, "userrating"));
		barValues.add(new BarChartValue (5, "millage"));
		barValues.add(new BarChartValue (5, "safety"));
		barValues.add(new BarChartValue (7, "millage2"));
		
		callDrawBarChart(barValues);
		
	}
	
	public static void callDrawPieChart(List<PieChartValue> pieValues) {
		DrawPieChart demo = new DrawPieChart(pieValues);  
		demo.setSize( 560 , 367 ); // size of the applet    
		RefineryUtilities.centerFrameOnScreen(demo);    
		demo.setVisible( true ); 		
	}
	

	public static void callDrawBarChart(List<BarChartValue> barValues) {
		String title = "Frequency of words for keyword";		
		DrawBarChart chart = new DrawBarChart(title, title, barValues);
		chart.pack();    
		//RefineryUtilities.centerFrameOnScreen(chart);        
		RefineryUtilities.positionFrameRandomly(chart);
		chart.setVisible(true);
	}	
}
