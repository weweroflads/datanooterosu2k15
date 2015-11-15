package org.jfree.chart.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import components.map.Map;
import components.map.Map.Pair;
import components.queue.Queue;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from a {@link CategoryDataset}.
 */
public class LineChartDemo1 extends ApplicationFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new demo.
     *
     * @param title
     *            the frame title.
     */
    public LineChartDemo1(final String title, Queue<Map.Pair<String, Double>> A,
            Queue<Map.Pair<String, Double>> B) {
        super(title);
        final CategoryDataset dataset = this.createDataset(A, B);
        final JFreeChart chart = this.createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private CategoryDataset createDataset(Queue<Pair<String, Double>> q,
            Queue<Pair<String, Double>> qq) {

        // row keys...
        final String series1 = "Job Posting";
        final String series2 = "Resume";

        // column keys...
        Pair<String, Double> x = q.dequeue();
        final String type1 = x.key();
        final Double value1 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type2 = x.key();
        final Double value2 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type3 = x.key();
        final Double value3 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type4 = x.key();
        final Double value4 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type5 = x.key();
        final Double value5 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type6 = x.key();
        final Double value6 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type7 = x.key();
        final Double value7 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type8 = x.key();
        final Double value8 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type9 = x.key();
        final Double value9 = x.value();
        q.enqueue(x);
        x = q.dequeue();
        final String type10 = x.key();
        final Double value10 = x.value();
        q.enqueue(x);

        x = qq.dequeue();
        final String type11 = x.key();
        final Double value11 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type12 = x.key();
        final Double value12 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type13 = x.key();
        final Double value13 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type14 = x.key();
        final Double value14 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type15 = x.key();
        final Double value15 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type16 = x.key();
        final Double value16 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type17 = x.key();
        final Double value17 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type18 = x.key();
        final Double value18 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type19 = x.key();
        final Double value19 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();
        final String type20 = x.key();
        final Double value20 = x.value();
        qq.enqueue(x);
        x = qq.dequeue();

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(value1 * 100, series1, type1);
        dataset.addValue(value2 * 100, series1, type2);
        dataset.addValue(value3 * 100, series1, type3);
        dataset.addValue(value4 * 100, series1, type4);
        dataset.addValue(value5 * 100, series1, type5);
        dataset.addValue(value6 * 100, series1, type6);
        dataset.addValue(value7 * 100, series1, type7);
        dataset.addValue(value8 * 100, series1, type8);
        dataset.addValue(value9 * 100, series1, type9);
        dataset.addValue(value10 * 100, series1, type10);

        dataset.addValue(value11 * 100, series2, type11);
        dataset.addValue(value12 * 100, series2, type12);
        dataset.addValue(value13 * 100, series2, type13);
        dataset.addValue(value14 * 100, series2, type14);
        dataset.addValue(value15 * 100, series2, type15);
        dataset.addValue(value16 * 100, series2, type16);
        dataset.addValue(value17 * 100, series2, type17);
        dataset.addValue(value18 * 100, series2, type18);
        dataset.addValue(value19 * 100, series2, type19);
        dataset.addValue(value20 * 100, series2, type20);

        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset
     *            a dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
                "Relative Word Frequencies of Resumes and Job Postings", // chart title
                "Word", // domain axis label
                "%Frequency", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);
        //    legend.setShapeScaleX(1.5);
        //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
                .getRenderer();
//        renderer.setDrawShapes(true);

        renderer.setSeriesStroke(0,
                new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 1.0f,
                        new float[] { 10.0f, 6.0f }, 0.0f));
        renderer.setSeriesStroke(1,
                new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 1.0f,
                        new float[] { 6.0f, 6.0f }, 0.0f));
        renderer.setSeriesStroke(2,
                new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 1.0f,
                        new float[] { 2.0f, 6.0f }, 0.0f));
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args
     *            ignored.
     */
    public static void urmums(Queue<Map.Pair<String, Double>> A,
            Queue<Map.Pair<String, Double>> B) {

        final LineChartDemo1 demo = new LineChartDemo1("Line Chart Demo", A, B);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
