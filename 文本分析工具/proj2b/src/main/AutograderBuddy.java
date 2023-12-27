package main;

import browser.NgordnetQueryHandler;
import browser.NgordnetServer;
import ngrams.NGramMap;


public class AutograderBuddy {

    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {


        NGramMap ngm = new NGramMap(wordFile, countFile);
        WorldNet wn = new WorldNet(synsetFile, hyponymFile);

        return new HyponymsHandler(wn, ngm);
//        throw new RuntimeException("Please fill out AutograderBuddy.java!");
    }
}
