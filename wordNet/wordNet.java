
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class wordNet {

    static ArrayList<String> id = new ArrayList<String>();
    static ArrayList<String> word = new ArrayList<String>();
    static ArrayList<String> wordm = new ArrayList<String>();

    static ArrayList<String> idhy = new ArrayList<String>();
    // static ArrayList<String[]> links = new ArrayList<String[]>();
    LinearProbingHashST<String,String[]> lp = new LinearProbingHashST<String,String[]>(4);

    // constructor takes the name of the two input files
    // public wordNet(String synsets, String hypernyms)
    // {

    // }

    public wordNet() {

    }
    // returns all WordNet nouns
    // public Iterable<String> nouns()
    // {

    // }

    // // is the word a WordNet noun?
    // public boolean isNoun(String word)
    // {

    // }

    // // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB)
    // {

    // }

    // // a synset (second field of synsets.txt) that is the common ancestor of
    // nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB)
    // {

    // }

    public void hyperNyms()  {
        String[] intArray = new String[3];
        try {
            String fileName = "C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\hypernyms6InvalidTwoRoots.txt";
            System.out.println(fileName);
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                lp.put(line.split(",",2)[0], line.split(",",2)[1].split(","));
                idhy.add(line.split(",",2)[0]);
                // links.add(line.split(",",2)[1].split(","));
            }
        }

        catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void synSets()  {
        String[] intArray = new String[3];
        try {
            String fileName = "C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\synsets6.txt";
            System.out.println(fileName);
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                intArray = line.split(",");
                id.add(intArray[0]);
                word.add(intArray[1]);
                wordm.add(intArray[2]);
            }
        }

        catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(int i =0 ; i<lp.size();i++)
        {
            // lp.put(idhy.get(i),links.get(i));
            String key = idhy.get(i);
            sb.append(key + " :: [");
            int j = 0;
            String[] toks = lp.get(key);
            for (; j < toks.length-1; j++) {
                sb.append(toks[j] + ", ");
            }
            sb.append(toks[j]);
            sb.append("]\n");

            // System.out.println(lp.toString());
        }
        return sb.toString();
    }

    // do unit testing of this class
    public static void main(String[] args) {
        wordNet wn = new wordNet();
        wn.synSets();
        wn.hyperNyms();
        // System.out.println(id);
        // System.out.println(word);
        // System.out.println(wordm);
        System.out.println(idhy);

        // for (int i = 0; i < links.size(); i++) {
        //     System.out.println(Arrays.toString(links.get(i)));

        // }
        System.out.println(wn);
   }
}

