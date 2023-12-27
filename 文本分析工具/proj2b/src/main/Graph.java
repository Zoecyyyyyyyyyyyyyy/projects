package main;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class Graph {
//    simply build the graph representation
    public ArrayList<Node> wordList = new ArrayList<>();
//    public ArrayList<>
    public Graph(String synsetsFile) {
        In words = new In(synsetsFile);

        while (words.hasNextLine()) {
            String nextLine = words.readLine();
            String[] splitLine = nextLine.split(",");
            String manywords = splitLine[1];
            String[] splitwords = manywords.split(" ");
            Node newNode = new Node(splitwords, null);
            wordList.add(newNode);
        }
    }

    public Node get(int i) {
        return wordList.get(i);
    }

    public Node get(String i) {
        for (int h = 0; h < wordList.size(); h += 1) {
            String[] theKey = wordList.get(h).key;
            for (int j = 0; j < theKey.length; j += 1) {
                if (theKey[j].equals(i)) {
                    return wordList.get(h);
                }
            }
//            if (wordList.get(h).key.(i)) {
//                return wordList.get(h);
//            }
        }
        return null;
    }

    public ArrayList<Node> getAll(String str) {
        ArrayList<Node> allwithSameName = new ArrayList<>();
        for (int h = 0; h < wordList.size(); h += 1) {
            String[] theKey = wordList.get(h).key;
            String theString = "";
            for (String i : theKey) {
                theString += i + " ";
            }
            theString = theString.substring(0,theString.length() - 1);

            if (theString.equals(str)) {
                allwithSameName.add(wordList.get(h));
//                return wordList.get(h);
            }
        }
        return allwithSameName;

    }



    public static class Node {
        public String[] key;
        public Node next;
        Node(String[] k, Node n) {
            key = k;
            next = n;
        }

        public Node addLast(Node node) {
            Node nodetoChange = this;
            while (nodetoChange.next != null) {
                nodetoChange = nodetoChange.next;
            }
            nodetoChange.next = node;
            return nodetoChange;
        }

    }
}
