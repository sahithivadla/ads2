import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;


public class SAP
{

    // private static final Exception IllegalArgumentException = null;
    Digraph gra;
    int d=Integer.MAX_VALUE;


    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        this.gra = new Digraph(G);

    }

    // length of szetween v and w; -1 if no such path
    public int length(int v, int w)
    {
        if(v<0 || w<=0) throw  new IllegalArgumentException();
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(gra,v);
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(gra,w);
        int dist=d;


        for(int i=0;i<gra.V();i++)
        {
            if (bfs.hasPathTo(i) && bfs1.hasPathTo(i)) {
                if ((bfs.distTo(i) + bfs1.distTo(i)) < dist) {
               dist= bfs.distTo(i)+bfs1.distTo(i);
            }
        }
        }
        if (dist == d) {
            return -1;
        } else {
            return dist;
        }
    }


    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        if(v<0 || w<=0) throw  new IllegalArgumentException();
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(gra,v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(gra,w);
        int minLength = d;
        int anc = -1;
        for (int i = 0; i < gra.V(); i++) {
            if (bfv.hasPathTo(i) && bfw.hasPathTo(i)) {
                if ((bfv.distTo(i) + bfw.distTo(i)) < d) {
                    minLength= bfv.distTo(i)+bfw.distTo(i);
                    anc=i;
            }
        }
    }
    return anc;
}

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {

        int dist =0;
        int m =d;
        for(int iiv:v)
        {
            for(int iiw:w)
            {
                dist=length(iiv,iiw);
                if (dist == -1 && dist < m) {
                    m = dist;
                }
            }
        }
        if (m==d) {
            return -1;
        } else {
            return m;
        }
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        int dist =0;
        int m =d;
        int ancestors =-1;
        for(int iiv:v)
        {
            for(int iiw:w)
            {
                dist=length(iiv,iiw);
                if (dist == -1 && dist < m) {
                    m = dist;
                    ancestors =ancestor(iiv,iiw);
                }
            }
        }
        return ancestors;
        }
    // do unit testing of this class
    public static void main(String[] args) throws FileNotFoundException,IOException
    {

        File file = new File("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\hypernyms.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> arr = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            arr.add(line);
    }
    Digraph graph = new Digraph(arr.size());
    for (int i = 0; i < arr.size(); i++) {
        String str[] = arr.get(i).split(",");
         int v = Integer.parseInt(str[0]);
      for (int j = 1; j < str.length; j++) {
        int w = Integer.parseInt(str[j]);
       graph.addEdge(v,w);
      }
    }
      SAP sp = new SAP(graph);
      System.out.println(sp.ancestor(9,1000));
      System.out.println(sp.length(900,1000));

 }

}

