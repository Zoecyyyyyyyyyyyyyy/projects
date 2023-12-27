package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WorldNet wn;
    private NGramMap ngm;
    private Set<Integer> newInt = new HashSet<>();
    private String returnString = "";
    private HashSet<String> theStringSet = new HashSet<>();
    private HashMap<String, Integer> multWords = new HashMap<>();
    private int numOfInputWords;
    private static final int A = 1900;
    private static final int B = 2020;

    public HyponymsHandler(WorldNet wn, NGramMap ngm) {
        this.ngm = ngm;
        this.wn = wn;
    }
    public String handle(NgordnetQuery q) {

        newInt = new HashSet<>();
        returnString = "";
        theStringSet = new HashSet<>();
        multWords = new HashMap<>();
        numOfInputWords = 0;

        if (q.words().size() == 1) {
            String word = q.words().get(0);



            numOfInputWords = q.words().size();
            findTheInt(word);

            HashSet<String> setString = intConvertToStringSet();
            mapConvert(setString);
            whichToSelect();

            handleWithK(q.startYear(), q.endYear(), q.k());
            returnString = changeFormat();
            return returnString;
        }
        if (q.words().size() > 1) {
            numOfInputWords = q.words().size();
            List<String> theWords = q.words();
            for (String i : theWords) {



                newInt = new HashSet<>();
                findTheInt(i);
                HashSet<String> setString = intConvertToStringSet();
                mapConvert(setString);
            }
            whichToSelect();
            handleWithK(q.startYear(), q.endYear(), q.k());
            returnString = changeFormat();
            return returnString;
        }
        return "AAA";
    }

    public HashSet<String> intConvertToStringSet() {
        HashSet<String> returnStringSet = new HashSet<>();
        for (int i : newInt) {
            String a = wn.graph.wordList.get(i);
            String[] aList = a.split(" ");
            for (String h : aList) {
                returnStringSet.add(h);
            }
        }
        return returnStringSet;
    }


    public void findTheInt(String word) {
        ArrayList<Integer> indexList = wn.graph.getAll(word);

        for (int i : indexList) {
            findIntBasedonOneInt(i);
        }
    }

    public void findIntBasedonOneInt(int index) {

        GraphNum.Node nodeofInt = wn.graph.getIntKeys(index);
        newInt.add(index);
        nodeofInt = nodeofInt.next;
        while (nodeofInt != null) {
            int key = nodeofInt.key;
            newInt.add(key);
            findIntBasedonOneInt(key);
            nodeofInt = nodeofInt.next;
        }
    }

    public String hsConvertString(HashSet<String> hs) {

        List<String> rL = new ArrayList<String>();

        for (String str : hs) {
            rL.add(str);
        }
        Collections.sort(rL);

        returnString = "";
        for (String str : rL) {
            returnString += str + " ";
        }

        if (returnString.length() == 0) {
            return returnString;
        }

        returnString = returnString.substring(0, returnString.length() - 1);
        return returnString;
    }


    public String changeFormat() {
        if (theStringSet.size() == 0) {

            return "[]";
        } else {
            ArrayList<String> strA = new ArrayList<>();
            for (String i : theStringSet) {
                strA.add(i);
            }
            Collections.sort(strA);
            returnString = strA.toString();
        }
        return returnString;
    }

    public void mapConvert(HashSet<String> set) {
        for (String i : set) {
            if (multWords.containsKey(i)) {
                multWords.replace(i, multWords.get(i) + 1);
            } else {
                multWords.put(i, 1);
            }
        }
    }

    public void whichToSelect() {
        for (String i : multWords.keySet()) {
            if (multWords.get(i) == numOfInputWords) {
                theStringSet.add(i);
            }
        }

    }

    public void handleWithK(int startYear, int endYear, int k) {
        if (k == 0) {
            return;
        }

        TreeMap<Double, String> maxTreeMap = new TreeMap<>();
        ArrayList<String> stringList = new ArrayList<>();
        ArrayList<Double> doubleList = new ArrayList<>();
        returnString = "";

        if (startYear == 0) {
            startYear = A;
        }
        if (endYear == 0) {
            endYear = B;
        }

        for (String i : theStringSet) {
            TimeSeries ts = ngm.countHistory(i, startYear, endYear);
            Double count = 0.0;

            for (double m : ts.values()) {
                count += m;
            }

            stringList.add(i);
            doubleList.add(count);

        }
        theStringSet = new HashSet<>();

        if (k > 0) {
            for (int i = 0; i < k; i += 1) {
                if (doubleList.size() == 0) {
                    break;
                }
                Double count = Collections.max(doubleList);

                if (count != null) {
                    if (count == 0.0) {
                        break;
                    }

                    Integer index = doubleList.indexOf(count);
                    String theString = stringList.get(index);


                    doubleList.remove(count);
                    stringList.remove(theString);
                    theStringSet.add(theString);
                } else {
                    break;
                }
            }
        }

    }
}

