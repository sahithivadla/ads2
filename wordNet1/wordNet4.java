import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;


public class wordNet4
{
     Digraph graph;
     SAP sapobj;
    static ArrayList<String> arr = new ArrayList<>();
    static ArrayList<Integer> id = new ArrayList<>();
    static ArrayList<String> no= new ArrayList<String>();

    static ArrayList<String> temp= new ArrayList<String>();
    static ArrayList<String> words = new ArrayList<String>();
    LinearProbingHashST<String,ArrayList<Integer>> lpsyn = new LinearProbingHashST<String,ArrayList<Integer>>(821921);
    LinearProbingHashST<Integer,ArrayList<String>> lpsyn1 = new LinearProbingHashST<Integer,ArrayList<String>>(821921);
     public wordNet4(String synsets,String hypernyms)
     {
         this.synsetssplit(synsets);
         this.hypernymssplit(hypernyms);
     }

     private void synsetssplit(String fname)
     {
        try {
            String fileName = "C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\synsets3.txt";
            System.out.println(fileName);
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(",")[1].split(" ");
                ArrayList<String> forval = new ArrayList<String>();
                ArrayList<Integer> t = new ArrayList<Integer>();
                for(int i =0;i<temp.length;i++)
                {
                      if(lpsyn.get(temp[i]) == null)
                      {
                            t = new ArrayList<Integer>();
                            int a=Integer.parseInt(line.split(",")[0]);
                            t.add(a);;
                            lpsyn.put(temp[i],t);
                            //to keep a note of all the keys for the tostring method
                            words.add(temp[i]);
                      }
                      else
                      {
                         int b =Integer.parseInt(line.split(",")[0]);
                         lpsyn.get(temp[i]).add(b);
                      }
                }
                while ((line = br.readLine()) != null) {
                    // intArray = line.split(",");
                    String[] re =  line.split(",")[1].split(" ");
                    for(int i =0;i<re.length;i++)
                    {
                       no.add(re[i]);
                    }

                    lpsyn1.put(Integer.parseInt(line.split(",")[0]), no);

                    id.add(Integer.parseInt(line.split(",")[0]));
                    // wordm.add(intArray[2]);
                }


            }
        }

        catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

     private void hypernymssplit(String filname)
     {
         try{
        File file = new File(filname);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line1;
        while ((line1 = br.readLine()) != null) {
            arr.add(line1);
        }
        graph = new Digraph(arr.size());

       for (int i = 0; i < arr.size(); i++) {
        String str[] = arr.get(i).split(",");
         int v = Integer.parseInt(str[0]);
      for (int j = 1; j < str.length; j++) {
        int w = Integer.parseInt(str[j]);
       graph.addEdge(v,w);
      }
    }
   }
   catch (FileNotFoundException e) {
    e.getMessage();
} catch (IOException e) {
    e.getMessage();
}

}
      public Iterable<String> nouns()
      {
        return words;
      }
      public boolean isNoun(String word)
      {
           return lpsyn.contains(word);
      }
      public int distance(String nounA,String nounB)
      {
          sapobj=new SAP(graph);
          return sapobj.length(lpsyn.get(nounA),lpsyn.get(nounB));
      }


    //   public String sap(String nounA,String nounB)
    //   {
    //     int length = Integer.MAX_VALUE, temp = 0, ancestor = 0;
    //     for (int val1 : lpsyn.get(nounA)) {
    //         for (int val2 : lpsyn.get(nounB)) {
    //             temp = sapobj.length(val1, val2);
    //             if (temp < length) {
    //                 length = temp;
    //                 ancestor = sapobj.ancestor(val1, val2);
    //             }
    //         }
    //     }
    //   }
      public static void main(String[] args)
      {
           wordNet4 objNet4 = new wordNet4("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\synsets.txt","C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\hypernyms.txt");
        //    objNet4.synsetssplit("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\synsets.txt");
        // System.out.println(objNet4.nouns());
        System.out.print(objNet4.isNoun("a-b"));
        System.out.print( objNet4.isNoun("tr"));
        System.out.print( objNet4.distance("a-b","tr"));
}
}