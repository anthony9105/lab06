import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        // axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price ($)");
        xAxis.setLabel("Year");

        //** Bar Chart **
        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series housingPricesSeries = new XYChart.Series();
        XYChart.Series commercialPricesSeries = new XYChart.Series();

        // data for the bar chart
        for (int i=0; i < avgCommercialPricesByYear.length; i++)
        {
            addSeriesData(housingPricesSeries, String.valueOf(i), avgHousingPricesByYear[i]);
            addSeriesData(commercialPricesSeries, String.valueOf(i), avgCommercialPricesByYear[i]);
        }
        housingPricesSeries.setName("avg Housing Price");
        commercialPricesSeries.setName("avg Commercial Price");
        barChart.getData().addAll(housingPricesSeries, commercialPricesSeries);


        //** Pie Chart **
        PieChart pieChart = new PieChart();

        // pie chart data
        PieChart.Data slice1 = new PieChart.Data(ageGroups[0], purchasesByAgeGroup[0]);
        PieChart.Data slice2 = new PieChart.Data(ageGroups[1], purchasesByAgeGroup[1]);
        PieChart.Data slice3 = new PieChart.Data(ageGroups[2], purchasesByAgeGroup[2]);
        PieChart.Data slice4 = new PieChart.Data(ageGroups[3], purchasesByAgeGroup[3]);
        PieChart.Data slice5 = new PieChart.Data(ageGroups[4], purchasesByAgeGroup[4]);
        PieChart.Data slice6 = new PieChart.Data(ageGroups[5], purchasesByAgeGroup[5]);

        pieChart.setLegendVisible(false);
        pieChart.getData().addAll(slice1, slice2, slice3, slice4, slice5, slice6);

        // put the charts in a borderpane
        BorderPane border = new BorderPane();
        border.setLeft(barChart);
        border.setRight(pieChart);

        // scene
        Scene scene = new Scene(border, 700, 500);
        primaryStage.setScene(scene);

        // stage
        primaryStage.setHeight(500);
        primaryStage.setWidth(1200);
        primaryStage.show();
    }

    /**
     * addSeriesData method to add data (for the bar chart)
     * @param series- XYChart.Series
     * @param section - String
     * @param data - double
     */
    public void addSeriesData(XYChart.Series series, String section, double data)
    {
        series.getData().add(new XYChart.Data(section, data));
    }
}
