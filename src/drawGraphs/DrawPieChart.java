package drawGraphs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

//http://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm
//http://www.jfree.org/jfreechart/

public class DrawPieChart extends ApplicationFrame {

	private static List<PieChartValue> pieValues;
	
	public DrawPieChart(List<PieChartValue> pieValues) {
		super( "Twitter Sentiments" ); 
		setPieValues(pieValues);
		setContentPane(createDemoPanel( ));
	}

	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset( );

		for (PieChartValue p : getPieValues()) {
			dataset.setValue( p.name , new Double(p.value));  
		} 
		return dataset;         
	}

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart(      
				"Twitter Sentiments",  // chart title 
				dataset,        // data    
				true,           // include legend   
				true, 
				false);

		return chart;
	}
	
	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());  
		return new ChartPanel( chart ); 
	}
	
	public static List<PieChartValue> getPieValues() {
		return pieValues;
	}

	public static void setPieValues(List<PieChartValue> pieValues) {
		DrawPieChart.pieValues = pieValues;
	}
	
	public static void main(String[] args) {
		List<PieChartValue> pieValues = new ArrayList<PieChartValue>();	
		pieValues.add(new PieChartValue("IPhone 5s", 20.0));
		pieValues.add(new PieChartValue("SamSung Grand", 20.0));
		pieValues.add(new PieChartValue("MotoG", 40.0));
		pieValues.add(new PieChartValue("Nokia Lumia", 10.0));
		
		DrawPieChart demo = new DrawPieChart(pieValues);  
		demo.setSize( 560 , 367 ); // size of the applet    
		RefineryUtilities.centerFrameOnScreen( demo );    
		demo.setVisible( true ); 
	}
}