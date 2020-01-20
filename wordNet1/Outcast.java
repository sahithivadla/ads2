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
         int lengths = nouns.length ;
         int  [] temp = new int[lengths];
         for(int i=0; i < lengths-1 ; i++){
            for(int j = i+1; j< lengths ; j++){
               int Temp = wordNet.distance(nouns[i],nouns[j]);
               temp[i] += Temp;
               temp[j] += Temp;
            }
         }
         int maxDistance = 0;
         int index = 0;
         for(int i = 0; i< lengths; i++){
            if(temp[i] > maxDistance){
                maxDistance = temp[i] ;
                index = i;
            }
         }
         return nouns[index];

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
