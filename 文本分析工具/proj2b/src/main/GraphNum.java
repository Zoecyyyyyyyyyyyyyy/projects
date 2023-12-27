package main;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class GraphNum {
    public ArrayList<Node> intList = new ArrayList<>();
    public ArrayList<String> wordList = new ArrayList<>();

    public GraphNum(String synsetsFile) {
        In words = new In(synsetsFile);
        int count = 0;
        while (words.hasNextLine()) {
            String nextLine = words.readLine();
            String[] splitLine = nextLine.split(",");
            String manywords = splitLine[1];
            wordList.add(manywords);
            Node nodetoAdd = new Node(count,null);
            intList.add(nodetoAdd);
            count += 1;
        }

    }

    public static class Node {
        public Integer key;
        public Node next;

        Node(int k, Node n) {
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

//    get the specific word based on key index
    public String getString(int i) {
        return wordList.get(i);
    }
    public Node getIntKeys(int i) {
        return intList.get(i);
    }

    public ArrayList<Integer> getAll(String str) {
        ArrayList<Integer> keyList = new ArrayList<>();
        int count = 0;
        for (String i : wordList) {
            String[] strList = i.split(" ");
            for (String j : strList) {
                if (j.equals(str)) {
                    keyList.add(count);
                }
            }
            count += 1;
        }
        return keyList;
    }

}
