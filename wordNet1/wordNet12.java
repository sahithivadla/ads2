
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class wordNet12 {

    static ArrayList<String> word = new ArrayList<String>();
    // static ArrayList<String> id = new ArrayList<String>();

    // static ArrayList<String> wordm = new ArrayList<String>();

    static ArrayList<String> idhy = new ArrayList<String>();
    // static ArrayList<String[]> links = new ArrayList<String[]>();
    LinearProbingHashST<String,String[]> lp = new LinearProbingHashST<String,String[]>(821921);
    LinearProbingHashST<String,ArrayList<String>> lpsyn = new LinearProbingHashST<String,ArrayList<String>>(821921);


    // constructor takes the name of the two input files
    // public wordNet(String synsets, String hypernyms)
    // {

    // }

    public wordNet12() {

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
            System.out.println(fileName);
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                String[] nouns = arr[1].split(" ");
                for (int i = 0; i < nouns.length; i++) {
                    if (lpsyn.get(nouns[i]) == null) {
                        lpsyn.put(nouns[i], new ArrayList<>());
                        word.add(nouns[i]);
                    }
                    lpsyn.get(nouns[i]).add(arr[0]);
                }
            }
        }

        catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

public String toString() {
    for (int i = 0; i < word.size(); i++) {
        System.out.println(word.get(i));
        System.out.println(lpsyn.get(word.get(i)));
    }
    return "";
}


    // // do unit testing of this class
    public static void main(String[] args) {
        wordNet12 wordNet12Obj = new wordNet12();
        wordNet12Obj.synSets();
        for (int i = 0; i < wordNet12Obj.word.size(); i++) {
            System.out.println(wordNet12Obj.word.get(i)+"--->"+wordNet12Obj.lpsyn.get(wordNet12Obj.word.get(i)));
            // System.out.println(wordNet12Obj.lpsyn.get(wordNet12Obj.word.get(i)));
        }

   }
}

