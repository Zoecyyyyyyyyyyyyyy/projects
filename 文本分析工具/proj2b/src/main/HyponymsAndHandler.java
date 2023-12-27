//package main;
//
//import browser.NgordnetQuery;
//import browser.NgordnetQueryHandler;
//import edu.princeton.cs.algs4.In;
//import ngrams.NGramMap;
//import ngrams.TimeSeries;
//
//import java.util.*;
//
//public class HyponymsAndHandler extends NgordnetQueryHandler {
//
//    private WorldNet wn;
//    private NGramMap ngm;
//    public HyponymsAndHandler(WorldNet wn, NGramMap ngm) {
//        this.ngm = ngm;
//        this.wn = wn;
//    }
//    public NgordnetQuery q;
//    @Override
//    public String handle(NgordnetQuery q) {
//        this.q = q;
//        List<String> inputWords = q.words();
//
//        if (inputWords.size() == 1) {
//            String inputWord = inputWords.get(0);
//            String returnString = findTheWord(inputWord);
//            HashSet<String> returnSet = keepOne(returnString);
//            returnString = handleWithK(returnSet, q.startYear(), q.endYear(), q.k());
//            returnString = inOrder(returnString);
//            return changeFormat(returnString);
//        }
//        if (inputWords.size() > 1) {
//            String returnString = findTheWord(inputWords);
////            returnString = whichToSelect(returnString, inputWords.size());
//            HashSet<String> returnSet = keepOne(returnString);
//            returnString = handleWithK(returnSet, q.startYear(), q.endYear(), q.k());
//            returnString = inOrder(returnString);
//            return changeFormat(returnString);
//        }
//        return "AAA";
//    }
//
//    public String findTheWord(String word) {
//        String returnString = "";
//        Graph graph = wn.graph;
//
//        String[] words = new String[1];
//        words[0] = word;
//
//        Graph.Node wordNode = graph.get(word);
////        System.out.println(word);
//        String[] hyponym = wordNode.key;
//        wordNode = wordNode.next;
//
//        for (String i : hyponym) {
//            returnString += (i + " ");
//        }
//        int m =0;
//
//        while (wordNode != null) {
//            m += 1;
//            hyponym = wordNode.key;
//            wordNode = wordNode.next;
//            for (String i : hyponym) {
//                returnString += (i + " ");
////                returnString += findTheWord(i);
//            }
////            returnString += (hyponym + " ");
//            String inputName = "";
//            for (String i : hyponym) {
//                inputName += i + " ";
//            }
//            returnString += findTheFullWord(inputName.substring(0,inputName.length() - 1));
//        }
//        return returnString;
//    }
//
//
//    public String findTheFullWord(String inputName) {
//        String returnString = "";
//        Graph graph = wn.graph;
//
//        if (inputName.endsWith(" ")) {
//            inputName = inputName.substring(0, inputName.length() - 1);
//        }
////        System.out.println(inputName + "AAA");
////        System.out.println();
//
//        ArrayList<Graph.Node> allNode = graph.getAll(inputName);
//
//        for (Graph.Node node : allNode) {
//
//            for (String i : node.key) {
//                returnString += i + " ";
//            }
//            //        returnString += node.key + " ";
//            node = node.next;
//            while (node != null) {
//                //            returnString += node.key + " ";
//                String in = "";
//                for (String i : node.key) {
//                    in += i + " ";
//                }
//                returnString += findTheFullWord(in);
//                node = node.next;
//            }
//        }
//
//        return returnString;
//    }
//
//    public String findTheWord(List<String> words) {
//        String returnString = "";
//        for (String i : words) {
//            HashSet<String> hs = keepOne(findTheWord(i));
//            for (String j : hs) {
//                returnString += j + " ";
//            }
//        }
//        returnString = whichToSelect(returnString, words.size());
//        return returnString;
//    }
//
//    public HashSet<String> keepOne(String inputString) {
//        HashSet<String> returnSet = new HashSet<String>();
//        String[] multiString = inputString.split(" ");
//        for (String i : multiString) {
//            returnSet.add(i);
//        }
//        return returnSet;
//    }
//
//    public String whichToSelect(String toBeSelected, Integer existNum) {
//        toBeSelected = toBeSelected.replace(",", "");
////        System.out.println(toBeSelected);
//        Map<String, Integer> whichToReturn = new HashMap<String, Integer>();
//        String[] wordList = toBeSelected.split(" ");
////        for (int i = 0; i < wordList.length; i += 1) {
////            System.out.println(i);
////        }
//
//        String returnString = "";
//
//        for (String i : wordList) {
//            if (whichToReturn.containsKey(i)) {
////                System.out.println("AAA");
//                int num = whichToReturn.get(i);
//                whichToReturn.replace(i, num + 1);
////                System.out.println(i + (num + 1));
//            } else {
//                whichToReturn.put(i, 1);
//            }
//        }
//
//        for (String key : whichToReturn.keySet()) {
//            Integer value = whichToReturn.get(key);
////            System.out.println(key + value);
//            if (value.equals(existNum)) {
//
//                returnString += key;
//                returnString += " ";
//            }
//        }
//        return returnString;
//    }
//    //    input string
//    public String inOrder(String inputString) {
//
//        String[] returnList = inputString.split(" ");
//        List<String> rL = new ArrayList<>();
//        for (String i : returnList) {
//            rL.add(i);
//        }
////        for (String i : inputSet) {
////            returnList.add(i);
////        }
//        Collections.sort(rL);
//
//        String returnString = "";
//        for (String i : rL) {
//            returnString += (i + " ");
//        }
////        System.out.println("AAA" + returnString);
//        return returnString;
//    }
//
//    public String changeFormat(String toChange) {
//        String[] afterSplit = toChange.split(" ");
//        if (afterSplit.length == 0) {
//            return "[]";
//        }
//        String returnString = "[";
//        for (int i = 0; i < afterSplit.length - 1; i += 1) {
//            returnString += afterSplit[i];
//            returnString += ", ";
//        }
//        returnString += afterSplit[afterSplit.length - 1];
//        returnString += "]";
//        return returnString;
//    }
//
//    public String handleWithK (HashSet<String> inputString, int startYear, int endYear, int k) {
//        if (k == 0) {
//            String returnString = "";
//            for (String i : inputString) {
//                returnString += i + " ";
//            }
//            return inOrder(returnString);
//        } else {
//            HashMap<String, Double> theMap = new HashMap<>();
//            for (String i : inputString) {
//                TimeSeries ts = ngm.countHistory(i,startYear, endYear);
//                Double num = 0.0;
//                for (double j : ts.values()) {
//                    num += j;
//                }
//                theMap.put(i, num);
//            }
//
//            TreeMap<Double, String> treemap = new TreeMap<>();
//            for (String i : theMap.keySet()) {
//                treemap.put(theMap.get(i), i);
//            }
//
//            String returnString = "";
//            for (int i = 0; i < k; i += 1) {
//                Map.Entry<Double, String> greatest = treemap.pollLastEntry();
//                if (greatest == null) {
//                    break;
//                }
//                String str = greatest.getValue();
//                returnString += (str + " ");
////                System.out.println(returnString);
//            }
//            return returnString;
//        }
//    }
//}
