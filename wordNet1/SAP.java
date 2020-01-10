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

    Digraph gra;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        this.gra = new Digraph(G);

    }

    // length of szetween v and w; -1 if no such path
    public int length(int v, int w)
    {
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(gra,v);
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(gra,w);
        int d=Integer.MAX_VALUE;

        for(int i=0;i<gra.V();i++)
        {

               int a= bfs.distTo(i)+bfs1.distTo(i);
               if(a<d)
               {
                   d=a;
               }
        }
        // if (d == Integer.MAX_VALUE) {
        //     return -1;
        // } else {
        //     return d;
        // }    }
        return d;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(gra,v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(gra,w);
        int minLength = Integer.MAX_VALUE;
        int anc = -1;
        for (int u = 0; u < gra.V(); ++u) {
            if (!bfv.hasPathTo(u) || !bfw.hasPathTo(u)) {
                continue;
            }
            int dist = bfv.distTo(u) + bfw.distTo(u);
            if (dist < minLength) {
                minLength = dist;
                anc = u;
            }
        }
        return anc;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        int dist =0;
        int r= Integer.MAX_VALUE;
        for(int iiv:v)
        {
            for(int iiw:w)
            {
                dist=length(iiv,iiw);
                if(dist<r)
                {
                    r=dist;
                }
            }
        }
        if (r == Integer.MAX_VALUE) {
            return -1;
        } else {
            return r;
        }
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        int minLength = Integer.MAX_VALUE;
        int anc = -1;
        for (int iv : v) {
            for (int iw : w) {
                int dist = length(iv, iw);
                if (dist < minLength) {
                    minLength = dist;
                    anc = ancestor(iv, iw);
                }
            }
        }
        return anc;
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
      System.out.println(sp.ancestor(1109,1113));
      System.out.println(sp.length(1109,1113));

 }

}

