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
      String str;
      int count = 0;
     while (input.hasNextLine()) {
        str = input.readLine();
        String[] x = str.split(",");
        int k = Integer.parseInt(x[0]);
        val.add(k,x[1]);
        //0,abc a_B_C,meaning
        //x - [0, abc a_b_c, meaning]
        String [] st = x[1].split(" ");
        count++;
        for(int j = 0; j < st.length; j++) {
                  String n = st[j].trim();
                  if(!lpsyn.contains(n)) {
                      ArrayList<Integer> id = new ArrayList<Integer>();
                      id.add(k);
                      lpsyn.put(n, id);
                  } else {
                    lpsyn.get(n).add(Integer.parseInt(x[0]));
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
      if(Str.length>1)
      {
      for(int i = 1; i < Str.length; i++) {
         int a = Integer.parseInt(Str[0]);
         int b = Integer.parseInt(Str[i]);
         graph.addEdge(a,b);
         }
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
            // System.out.println(lpsyn.get("Aalst"));
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