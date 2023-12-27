package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

import static plotting.Plotter.encodeChartAsString;
import static plotting.Plotter.generateTimeSeriesChart;

public class HistoryHandler extends NgordnetQueryHandler  {
    private NGramMap map;
    public HistoryHandler(NGramMap map) {
        this.map = map;
    }

    public String handle(NgordnetQuery q) {
        String returnString = "";
        ArrayList<String> words = new ArrayList<>();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        int startyear = q.startYear();
        int endyear = q.endYear();

        List<String> wantwords = q.words();
        for (String word : wantwords) {
            words.add(word);
            lts.add(map.weightHistory(word, startyear, endyear));
        }

        XYChart chart = generateTimeSeriesChart(words, lts);

        return encodeChartAsString(chart);
    }
}
