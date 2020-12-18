package Nombre;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

public class Graphique extends ApplicationFrame {
    private ArrayList<ArrayList<Integer>> resultat;
    public Graphique(String applicationTitle , String chartTitle, ArrayList<ArrayList<Integer>> lesResult) {
        super(applicationTitle);
        this.resultat = lesResult;
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Jours","Nombre de personnes",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for (int i = 0; i < this.resultat.get(0).size(); i++) {
            //dataset.addValue( this.resultat.get(0).get(i) , "S" , String.valueOf(i) );
            dataset.addValue( this.resultat.get(1).get(i) , "E" , String.valueOf(i) );
            dataset.addValue( this.resultat.get(2).get(i) , "I" , String.valueOf(i) );
            //dataset.addValue( this.resultat.get(3).get(i) , "R" , String.valueOf(i) );
        }
        return dataset;
    }
}
