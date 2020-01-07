
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class wordNet1 {

    static ArrayList<String> id = new ArrayList<String>();
    static ArrayList<String> word = new ArrayList<String>();
    // static ArrayList<String> wordm = new ArrayList<String>();

    static ArrayList<String> idhy = new ArrayList<String>();
    static ArrayList<String[]> idsynset = new ArrayList<String[]>();
    static ArrayList<String> nouns = new ArrayList<String>();
    LinearProbingHashST<String,String[]> lp = new LinearProbingHashST<String,String[]>(821921);
    LinearProbingHashST<String,String[]> lps = new LinearProbingHashST<String,String[]>(821921);


    // constructor takes the name of the two input files
    // public wordNet(String synsets, String hypernyms)
    // {

    // }

    public wordNet1() {

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
        try {
            String fileName = "C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\hypernyms3InvalidCycle.txt";
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] temp;
            while ((line = br.readLine()) != null) {
                temp = line.split(",",2);
                String s = "";
                // System.out.println("Hello - 1 :: " + line);
                if(temp.length>1)
                {
                    // System.out.println("Helloyyyyyy2");
                lp.put(line.split(",",2)[0], line.split(",",2)[1].split(","));
                idhy.add(line.split(",",2)[0]);
                }

                else if(line.split(" ").length==1)
                {
                    // System.out.println("Hello - 3 : adding" + line);
                    lp.put(line, s.split(""));
                    idhy.add(line);
                }
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
            String fileName = "C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\synsets3.txt";
            // System.out.println(fileName);
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("heyyyy");

                // intArray = line.split(",");
                // lp.put(line.split(",")[0], line.split(",")[1].split(" "));
                // id.add(intArray[0]);
                // wordm.add(intArray[2]);
                String[]tem = line.split(",")[1].split(" ");
                // System.out.println(Arrays.toString(tem));






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
        System.out.println("Size is : " + idhy.size());
        for(int i =0 ; i<lp.size();i++)
        {
            // lp.put(idhy.get(i),links.get(i));
            String key = idhy.get(i);
            sb.append(key + " :: [");
            int j = 0;
            if (lp.get(key) !=null ) {
                String[] toks = lp.get(key);
                for (; j < toks.length-1; j++) {
                    sb.append(toks[j] + ", ");
                }
                sb.append(toks[j]);
            } else {
                sb.append(key);
            }
            sb.append("]\n");

            // System.out.println(lp.toString());
        }
        return sb.toString();
    }

    public String toString1() {
        StringBuffer sb = new StringBuffer();
        System.out.println("Size is : " + id.size());
        for(int i =0 ; i<lp.size();i++)
        {
            // lp.put(idhy.get(i),links.get(i));
            String key = id.get(i);
            sb.append(key + " :: [");
            int j = 0;
            if (lp.get(key) !=null ) {
                String[] toks = lp.get(key);
                for (; j < toks.length-1; j++) {
                    sb.append(toks[j] + ", ");
                }
                sb.append(toks[j]);
            } else {
                sb.append(key);
            }
            sb.append("]\n");

            // System.out.println(lp.toString());
        }
        return sb.toString();
    }


    // // do unit testing of this class
    public static void main(String[] args) {
        wordNet wn = new wordNet();
        wn.synSets();
        // wn.hyperNyms();
        // System.out.println(id);
        // System.out.println(word);
        // System.out.println(wordm);
        // System.out.println(idhy);
        // for (int i = 0; i < links.size(); i++) {
        //     System.out.println(Arrays.toString(links.get(i)));

        // }
        // System.out.println(wn.toString());
        // System.out.println(wn.toString1());
   }
}

