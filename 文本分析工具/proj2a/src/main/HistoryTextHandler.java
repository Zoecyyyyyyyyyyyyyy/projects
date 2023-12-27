package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap map;
    public HistoryTextHandler(NGramMap map) {
        this.map = map;
    }

    public String handle(NgordnetQuery q) {
        String returnString = "";
        List<String> wantwords = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        for (int i = 0; i < wantwords.size(); i++) {
            String word = wantwords.get(i);
            returnString += word;
            returnString += ": ";

            TimeSeries weightHistory = map.weightHistory(word, startYear, endYear);
            returnString += weightHistory.toString();
            returnString += "\n";
        }
        System.out.println(returnString);
        return returnString;
    }
}
