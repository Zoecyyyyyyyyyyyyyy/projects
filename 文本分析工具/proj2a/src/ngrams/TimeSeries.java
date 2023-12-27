package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();

        TimeSeries returnPopulation = new TimeSeries();
        for (int i = startYear; i <= endYear; i += 1) {
            if (ts.containsKey(i)) {
                returnPopulation.put(i, ts.get(i));
            }
        }
        this.putAll(returnPopulation);
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {

        List<Integer> returnList = new ArrayList<>(this.keySet());
        return returnList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {

        List<Double> returnList = new ArrayList<>();
        for (int i = 0; i < this.years().size(); i += 1) {
            int year = years().get(i);
            double value = this.get(year);
            returnList.add(value);
        }
        return returnList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {

        TimeSeries returnTimeSeries = new TimeSeries();
        returnTimeSeries = this;
        for (int i = 0; i < ts.years().size(); i += 1) {
            int key = ts.years().get(i);
            double value = ts.data().get(i);
            if (returnTimeSeries.containsKey(key)) {
                double valueNew = returnTimeSeries.get(key);
                returnTimeSeries.replace(key, value + valueNew);
            } else {
                returnTimeSeries.put(key, value);
            }
        }
        return returnTimeSeries;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {

        List<Integer> listThisyear = this.years();
        List<Double> listThisdata = this.data();

        TimeSeries returnTimeSeries = new TimeSeries();

        for (int i = 0; i < listThisdata.size(); i += 1) {
            int key = listThisyear.get(i);
            if (ts.containsKey(key)) {
                double quotient = listThisdata.get(i) / ts.get(key);
                returnTimeSeries.put(key, quotient);
            } else {
                throw new IllegalArgumentException("");
            }
        }
        return returnTimeSeries;
    }

}
