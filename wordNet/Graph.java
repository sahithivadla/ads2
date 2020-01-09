import java.util.Iterator;
import java.util.NoSuchElementException;


public class Graph {
private int V = 0;
private Bag<Integer>[] adj;

public Graph(int V) {
this.V = V;
adj = (Bag<Integer>[]) new Bag[V];
for (int v = 0; v < V; v++) {
adj[v] = new Bag<Integer>();
}
}

public void addEdge (int v, int w) {
adj[v].add(w);
}

public Iterable<Integer>adj(int v) {
return adj[v];
}

}

