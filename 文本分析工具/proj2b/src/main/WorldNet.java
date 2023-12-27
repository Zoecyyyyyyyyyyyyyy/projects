package main;

import edu.princeton.cs.algs4.In;

public class WorldNet {

    public GraphNum graph;
    public WorldNet(String synsetsFile, String hyponymsFile) {

        graph = new GraphNum(synsetsFile);
        In hyponyms = new In(hyponymsFile);

        while (hyponyms.hasNextLine()) {
            String nextLine = hyponyms.readLine();
            String[] splitLine = nextLine.split(",");
            String theWordKey = splitLine[0];
            int numtheWordKey = Integer.parseInt(theWordKey);
            GraphNum.Node nodeToChange = graph.getIntKeys(numtheWordKey);

            for (int i = 1; i < splitLine.length; i += 1) {
                String stringnumi = splitLine[i];
                int numi = Integer.parseInt(stringnumi);
                GraphNum.Node newNodeToAdd = new GraphNum.Node(numi, null);
                nodeToChange = nodeToChange.addLast(newNodeToAdd);
            }
        }
    }
}
