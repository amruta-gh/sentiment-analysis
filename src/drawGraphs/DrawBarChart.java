package drawGraphs;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

//http://www.tutorialspoint.com/jfreechart/jfreechart_bar_chart.htm
public class DrawBarChart extends ApplicationFrame {

	private static List<BarChartValue> barValues;

	public DrawBarChart( String applicationTitle , String chartTitle,  List<BarChartValue> bValues) {
		super( applicationTitle );        
		setBarValues(bValues);
		JFreeChart barChart = ChartFactory.createBarChart(
				chartTitle,           
				"", //words           
				"Frequency",            
				createDataset(),          
				PlotOrientation.VERTICAL,           
				true, true, false);

		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		setContentPane( chartPanel ); 
	}
	
	private CategoryDataset createDataset() {
		final String word = "words";      
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();  

		for (BarChartValue b : getBarValues()) {
			dataset.setValue(b.frequency, word, b.word);  
		} 
		             
		return dataset; 
	}
	
	public static List<BarChartValue> getBarValues() {
		return barValues;
	}

	public static void setBarValues(List<BarChartValue> barValues) {
		DrawBarChart.barValues = barValues;
	}
	
	public static void main(String[] args) {
		String title = "Frequency of words for keyword";
		List<BarChartValue> barValues = new ArrayList<BarChartValue>();
		barValues.add(new BarChartValue (1, "speed"));
		barValues.add(new BarChartValue (3, "userrating"));
		barValues.add(new BarChartValue (5, "millage"));
		barValues.add(new BarChartValue (5, "safety"));
		barValues.add(new BarChartValue (7, "millage2"));
		
		DrawBarChart chart = new DrawBarChart(title, title, barValues);
		chart.pack();        
		RefineryUtilities.centerFrameOnScreen(chart);        
		chart.setVisible( true ); 
	}
}