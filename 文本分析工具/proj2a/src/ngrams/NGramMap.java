package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {


    private static TimeSeries countsFile = new TimeSeries();
    private int numofYears = 0;
    private ArrayList<String> wordsList = new ArrayList<String>();
    private ArrayList<NewWordTimeSeries> wordsFile = new ArrayList<NewWordTimeSeries>();

    public class NewWordTimeSeries {
        private String wordname;
        private int year;
        private TimeSeries specificTimeSeries;

        public NewWordTimeSeries(String name, int year, double value) {
            wordname = name;
            this.year = year;
            specificTimeSeries = new TimeSeries();
            specificTimeSeries.put(year, value);
        }
    }
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In words = new In(wordsFilename);
        In counts = new In(countsFilename);

        while (words.hasNextLine()) {
            String nextLine = words.readLine();
            String[] splitLine = nextLine.split("\t");
            String newword = splitLine[0];

            int year = Integer.parseInt(splitLine[1]);
            double value = Double.parseDouble(splitLine[2]);

            if (wordsList.contains(newword)) {
                int index = wordsList.indexOf(newword);
                wordsFile.get(index).specificTimeSeries.put(year, value);

            } else {
                wordsList.add(newword);
                NewWordTimeSeries newTimeSeries = new NewWordTimeSeries(newword, year, value);
                wordsFile.add(newTimeSeries);
            }
        }

        while (counts.hasNextLine()) {
            numofYears++;
            String nextLine = counts.readLine();
            String[] splitLine = nextLine.split(",");
            String year = splitLine[0];
            String value = splitLine[1];

            int finalyear = Integer.parseInt(year);
            double finalvalue = Double.parseDouble(value);

            countsFile.put(finalyear, finalvalue);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {

        TimeSeries returnTimeSeries = new TimeSeries();
        NewWordTimeSeries upperTimeSeries;
        if (!wordsList.contains(word)) {
            return returnTimeSeries;
        } else {
            int index = wordsList.indexOf(word);
            upperTimeSeries = wordsFile.get(index);
            List<Integer> year = upperTimeSeries.specificTimeSeries.years();
            List<Double> value = upperTimeSeries.specificTimeSeries.data();

            for (int i = 0; i < year.size(); i++) {
                if (year.get(i) >= startYear && year.get(i) <= endYear) {
                    returnTimeSeries.put(year.get(i), value.get(i));
                }
            }
            return returnTimeSeries;
        }
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {

        TimeSeries returnTimeSeries = new TimeSeries();
        NewWordTimeSeries upperTimeSeries;
        if (!wordsList.contains(word)) {
            return returnTimeSeries;
        } else {
            int index = wordsList.indexOf(word);
            upperTimeSeries = wordsFile.get(index);
            List<Integer> year = upperTimeSeries.specificTimeSeries.years();
            List<Double> value = upperTimeSeries.specificTimeSeries.data();

            for (int i = 0; i < year.size(); i += 1) {
                returnTimeSeries.put(year.get(i), value.get(i));
            }
            return returnTimeSeries;
        }
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {

        TimeSeries returnTimeSeries = new TimeSeries();
        returnTimeSeries.putAll(countsFile);
        return returnTimeSeries;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {

        TimeSeries returnTimeSeries = new TimeSeries();
        if (!wordsList.contains(word)) {
            return returnTimeSeries;
        } else {
            int index = wordsList.indexOf(word);
            TimeSeries upperTimeSeries = wordsFile.get(index).specificTimeSeries;
            for (int i = startYear; i <= endYear; i++) {
                if (countsFile.containsKey(i)) {
                    double allwords = countsFile.get(i);
                    if (upperTimeSeries.containsKey(i)) {
                        double theword = upperTimeSeries.get(i);
                        double frequency = theword / allwords;
                        returnTimeSeries.put(i, frequency);
                    }
                }
            }

            return returnTimeSeries;
        }
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {

        TimeSeries returnTimeSeries = new TimeSeries();
        if (!wordsList.contains(word)) {
            return returnTimeSeries;
        } else {
            int index = wordsList.indexOf(word);
            TimeSeries specificword = wordsFile.get(index).specificTimeSeries;
            List<Integer> specificwordyear = specificword.years();
            for (int i = 0; i < specificwordyear.size(); i++) {
                int key = specificwordyear.get(i);
                double allwords = countsFile.get(key);
                double theword = specificword.get(key);
                double frequency = theword / allwords;
                returnTimeSeries.put(key, frequency);
            }
        }
        return returnTimeSeries;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {

        TimeSeries returnTimeSeries = new TimeSeries();
        List<String> listofwords = new ArrayList<>(words);
        for (int i = startYear; i <= endYear; i++) {
            double frequency = 0;
            for (int j = 0; j < words.size(); j++) {
                String word = listofwords.get(j);
                if (wordsList.contains(word)) {
                    int index = wordsList.indexOf(word);
                    TimeSeries specificword = wordsFile.get(index).specificTimeSeries;
                    if (specificword.years().contains(i)) {
                        double newfre = weightHistory(word, startYear, endYear).get(i);
                        frequency += newfre;
                    }
                }
            }
            returnTimeSeries.put(i, frequency);
        }
        for (int i = startYear; i <= endYear; i++) {
            if (returnTimeSeries.get(i) == 0.0) {
                returnTimeSeries.remove(i);
            }
        }
        return returnTimeSeries;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {

        TimeSeries returnTimeSeries = new TimeSeries();
        List<String> listofwords = new ArrayList<>(words);
        for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
            double frequency = 0;
            returnTimeSeries.put(i, 0.0);
        }
        for (int i = 0; i < listofwords.size(); i++) {
            String theword = listofwords.get(i);
            TimeSeries thewordTimeSeries = weightHistory(theword);
            List<Integer> theyear = thewordTimeSeries.years();
            for (int j = 0; j < theyear.size(); j += 1) {
                int thespecificyear = theyear.get(j);
                double newvalue = thewordTimeSeries.get(thespecificyear);
                double prevvalue = returnTimeSeries.get(thespecificyear);
                returnTimeSeries.replace(thespecificyear, prevvalue + newvalue);
            }
        }
        for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
            if (returnTimeSeries.get(i) == 0.0) {
                returnTimeSeries.remove(i);
            }
        }
        return returnTimeSeries;
    }


}
