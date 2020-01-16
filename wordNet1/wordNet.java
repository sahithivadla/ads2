import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import java.util.Arrays;
import java.util.Hashtable;
import edu.princeton.cs.algs4.In;


public class WordNet
{
     private final  Digraph graph;
     private final SAP sapobj;
  private final Hashtable<String,ArrayList<Integer>> lpsyn = new Hashtable<String,ArrayList<Integer>>();
    private final ArrayList<String> val = new ArrayList<>();
    private final ArrayList<String> words = new ArrayList<>();

     public WordNet(String synsets,String hypernyms)
     {

        int c= this.synsetssplit(synsets);
         graph=this.hypernymssplit(hypernyms,c);
         sapobj=new SAP(graph);
     }

     private int synsetssplit(String fname)
     {

        In input = new In(fname);
            String line;
            int count=0;

            ArrayList<String> te = new ArrayList<String>();
            while (input.hasNextLine()) {
                // id as key  and then array list of nouns as value
                line=input.readLine();
                int k = Integer.parseInt(line.split(",")[0]);
                val.add(k,line.split(",")[1]);
                String[] temp = line.split(",")[1].split(" ");
                ArrayList<Integer> t = new ArrayList<Integer>();
                for(int i =0;i<temp.length;i++)
                {
                      if(lpsyn.get(temp[i]) == null)
                      {
                            t = new ArrayList<Integer>();
                            int a=Integer.parseInt(line.split(",")[0]);
                            t.add(a);;
                            lpsyn.put(temp[i].trim(),t);
                            //to keep a note of all the keys for the tostring method
                            words.add(temp[i]);
                            count++;
                      }
                      else
                      {
                         int b =Integer.parseInt(line.split(",")[0]);
                         lpsyn.get(temp[i].trim()).add(b);
                         words.add(temp[i]);
                      }
                }
            }
        return count;
    }

     private Digraph hypernymssplit(String filename,int len)
     {
        In br = new In(filename);
   Digraph graph =  new Digraph(len);
   while (br.hasNextLine()) {
      String [] Str = br.readLine().split(",");
      for(int i = 1; i < Str.length; i++) {
         int a = Integer.parseInt(Str[0]);
         int b = Integer.parseInt(Str[i]);
         graph.addEdge(a,b);
         }
      }
   return graph;


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
        if(isNoun(nounA) && isNoun(nounB) ){

            int  len = sapobj.length(lpsyn.get(nounA) , lpsyn.get(nounB));
              return len ;
           }

          return -1;
        }


      public String sap(String nounA,String nounB)
      {
        if(isNoun(nounA) && isNoun(nounB)==true)
        {
            // sapobj=new SAP(graph);
            System.out.println(lpsyn.get("Aalst"));
            // System.out.println(lpidnou.get("Abadan"));
            // System.out.println(sapobj.ancestor(47,53));

           int result = sapobj.ancestor(lpsyn.get(nounA),lpsyn.get(nounB));
            // System.out.println(result);
            if(result!=-1)
            {
                return val.get(result);
            }
            else{
                return "there is no common ancestor";
            }
        }
        return null;
      }
    //   public static void main(String[] args)throws Exception
    //   {
    //        WordNet objNet4 = new WordNet("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet1\\synsets.txt","C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet1\\hypernyms.txt");
//  for(int i=0;i<lp.size();i++)
//  {
//      System.out.println(id.get(i)+"--->"+lp.get(id.get(i)));
//  }
//  for(int i=0;i<lpidnou.size();i++)
//  {
//     System.out.println(noun.get(i)+"--->"+lpidnou.get(noun.get(i)));
//  }


// System.out.println(lpidnou.get("Acipenseridae"));
// System.out.println(objNet4.isNoun("1"));
// System.out.println(objNet4.distance("Aalst","Acipenseridae"));
// System.out.println(objNet4.sap("Aalst","Acipenseridae"));

// for(int v =0;v<arr.size();v++)
//     {
//         System.out.print(v+"---->");
//         for(int w:graph.adj(v))
//         {
//              System.out.print(w+" ");
//         }
//         System.out.println();
//         if(v==55)
//         break;



// }
}