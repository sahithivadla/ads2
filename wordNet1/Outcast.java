import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;

    import edu.princeton.cs.algs4.In;
    import edu.princeton.cs.algs4.StdIn;
    import edu.princeton.cs.algs4.StdOut;



    public class Outcast {
      private final WordNet wordNet;

      public Outcast(final WordNet wordnet) { // constructor takes a WordNet object
         wordNet = wordnet;
      }
      public String outcast(String[] nouns) { // given an array of WordNet nouns, return an outcast
         String outcastword=null;
         int dist=0,maxlen=0;
         for(String a:nouns)
         {
            dist=0;
            for(String b:nouns)
            {
                 dist=wordNet.distance(a,b);
            }
            if(dist>maxlen)
            {
               maxlen=dist;
               outcastword=a;
            }
         }
         return outcastword;
      }
       // public static void main(String[] args) {

       // } // see test client below
  // public static void main (String[] args) {
  //   WordNet wordnet = new WordNet("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet1\\synsets.txt","C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet1\\hypernyms.txt");
  //   Outcast obj = new Outcast(wordnet);
  //   String[] s = {"water","soda" ,"bed" ,"orange_juice" ,"milk" ,"apple_juice" ,"tea", "coffee"};
  //   System.out.println(obj.outcast(s));
	// }
}
