import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;


public class SAP {
    private Digraph G;
    private final int infinity= Integer.MAX_VALUE;

    /**
     * constructor takes graph as input
     * @param G is the graph
     */
      public SAP(Digraph G) {
      this.G = new Digraph(G);
      }

      /**
       * This method is used to find the length between two vertices
       * @param v is one of the vertex
       * @param w is one of the vertex
       * @return distance if exists, else returns -1
       */
      public int length(int v, int w) {
      BreadthFirstDirectedPaths bv = new BreadthFirstDirectedPaths(G, v);
      BreadthFirstDirectedPaths bw = new BreadthFirstDirectedPaths(G, w);
      int MinDist = infinity;
      for (int i = 0; i < G.V(); i++) {
      if (bv.hasPathTo(i) && bw.hasPathTo(i)) {
      if ((bv.distTo(i) + bw.distTo(i)) < infinity) {
      MinDist = bv.distTo(i) + bw.distTo(i);
      }
      }
      }
      if (MinDist == infinity) {
      return -1;
      }
      else {
      return MinDist;
      }
      }

      /**
       * This method is used to find the nearest ancestor
       * @param v is one of the vertex
       * @param w is one of the vertex
       * @return ancestor if exists, else returns -1
       */
      public int ancestor(int v, int w) {
      BreadthFirstDirectedPaths bv = new BreadthFirstDirectedPaths(G, v);
      BreadthFirstDirectedPaths bw = new BreadthFirstDirectedPaths(G, w);
      int MinDist = infinity;
      int anc = -1;
      for (int i = 0; i < G.V(); i++) {
      if (bv.hasPathTo(i) && bw.hasPathTo(i)) {
      if (bv.distTo(i) + bw.distTo(i) < infinity) {
      MinDist = bv.distTo(i) + bw.distTo(i);
      anc = i;
      }
      }
      }
      return anc;
      }

       /**
        * This method is used to find the length
        * @param v is the one vertex
        * @param w is the second vertex
        * @return distance if exists, else returns -1
        */
      public int length(Iterable<Integer> v, Iterable<Integer> w) {
      int MinDist = infinity;
      for (int i : v) {
      for (int j : w) {
      int dist = length(i,j);
      if (dist == -1 && dist < MinDist) {
      MinDist = dist;
      }
      }
      }
      if (MinDist == infinity) {
      return -1;
      } else {
      return MinDist;
      }
      }

      /**
       * This method is used to find nearest ancestor
       * @param v is the one vertex
       * @param w is the second vertex
       * @return ancestor if exists, else return -1
       */
      public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
      int MinDist = infinity;
      int anc = -1;
      for (int i : v) {
      for (int j : w) {
      int dist = length(i,j);
      if(dist == -1 && dist < MinDist) {
      MinDist = dist;
      anc = ancestor(i,j);
      }
      }
      }
      return anc;
      }

      /**
       *  do unit testing of this class
       * @param args as string
       *
       */

    //   public static void main (String[] args) throws FileNotFoundException,IOException{
    //   ArrayList<String> arr = new ArrayList<>();
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

    // // for (int v = 0; v < arr.size(); v++) {
    // // System.out.print(v+"---------->");
    // // for (int w : graph.adj(v)) {
    // // System.out.print(w+" ");
    // // }
    // // System.out.println();
    // // }

    // SAP sap = new SAP(graph);
    // System.out.println(sap.length(1,6));
    // System.out.println(sap.ancestor(1,6));


    //   }

    }


