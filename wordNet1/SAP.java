import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;



public class SAP {
    private Digraph graph;
    private  int short_len;
    private   int short_ancestor;

    /**
     * constructor takes graph as input
     * @param G is the graph
     */
      public SAP(Digraph G) {
      graph=G;
      }

      /**
       * This method is used to find the length between two vertices
       * @param v is one of the vertex
       * @param w is one of the vertex
       * @return distance if exists, else returns -1
       */
      public int length(int v, int w) {
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V()) {
          throw new IllegalArgumentException();
      }
      BreadthFirstDirectedPaths bv = new BreadthFirstDirectedPaths(graph, v);
      BreadthFirstDirectedPaths bw = new BreadthFirstDirectedPaths(graph, w);
      short_len=Integer.MAX_VALUE;
      int templen=0;
      short_ancestor=-1;
      for (int i = 0; i < graph.V(); i++) {
      if (bv.hasPathTo(i) && bw.hasPathTo(i)) {
      templen = bv.distTo(i) + bw.distTo(i);
      if (templen<short_len) {
      short_len=templen;
      short_ancestor=i;
      }
    }
 }
 if(short_len == Integer.MAX_VALUE) {
  this.short_len = -1;
    }
    return short_len ;
}

      /**
       * This method is used to find the nearest ancestor
       * @param v is one of the vertex
       * @param w is one of the vertex
       * @return ancestor if exists, else returns -1
       */
      public int ancestor(int v, int w) {
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V()) {
          throw new IllegalArgumentException();
      }

        length(v,w);
        return short_ancestor;
      }

       /**
        * This method is used to find the length
        * @param v is the one vertex
        * @param w is the second vertex
        * @return distance if exists, else returns -1
        */

      public int length(Iterable<Integer> v, Iterable<Integer> w) {
       if(v==null||w==null)            throw new IllegalArgumentException();
       valid(v);
        valid(w);
      BreadthFirstDirectedPaths bv = new BreadthFirstDirectedPaths(graph, v);
      BreadthFirstDirectedPaths bw = new BreadthFirstDirectedPaths(graph, w);
      short_len = Integer.MAX_VALUE;
        short_ancestor = -1 ;
        int templen = Integer.MAX_VALUE;
        for(int i=0; i < graph.V(); i++){
          if(v==null||w==null)            throw new IllegalArgumentException();
             if (bv.hasPathTo(i) && bw.hasPathTo(i) && bv.distTo(i) + bw.distTo(i) < templen) {
			       short_ancestor = i;
			       templen = bv.distTo(i) + bw.distTo(i);
		                }
	            }
        if (templen == Integer.MAX_VALUE) {
          templen = -1;
	       }
	    short_len = templen;
        return short_len;
    }

      /**
       * This method is used to find nearest ancestor
       * @param v is the one vertex
       * @param w is the second vertex
       * @return ancestor if exists, else return -1
       */
      public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if(v==null||w==null)            throw new IllegalArgumentException();
        valid(v);
        valid(w);
      length(v,w);
      return short_ancestor;
    }
    private void valid(Iterable<Integer> v)
    {
      final Iterator<Integer> iobj = v.iterator();

      while(iobj.hasNext())
        {
          if(iobj.next()==null)
          {
            throw new IllegalArgumentException();
          }
        }
    }



      /**
       *  do unit testing of this class
       * @param args as string
       *
       */

    //   public static void main (String[] args) throws FileNotFoundException,IOException{
    // //   ArrayList<String> arr = new ArrayList<>();
    //     File file = new File("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet1\\hypernyms15Tree.txt");
    // BufferedReader br = new BufferedReader(new FileReader(file));
    //  String st;
    //  while ((st = br.readLine()) != null) {
    //  arr.add(st);
    //  }
    //  Digraph graph = new Digraph(arr.size());
    // for (int i = 0; i < arr.size(); i++) {
    // String str[] = arr.get(i).split(",");
    // int v = Integer.parseInt(str[0]);
    // for (int j = 1; j < str.length; j++) {
    // int w = Integer.parseInt(str[j]);
    // graph.addEdge(v,w);
    // }
    // }

    // for (int v = 0; v < arr.size(); v++) {
    // System.out.print(v+"---------->");
    // // for (int w : graph.adj(v)) {
    // // System.out.print(w+" ");
    // // }
    // // System.out.println();
    // // }

    // SAP sap = new SAP(graph);
    // System.out.println(sap.length(1,6));
    // System.out.println(sap.ancestor(1,6));


    //   }

    // }
  }



