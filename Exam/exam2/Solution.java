import java.io.*;
import java.util.*;

public class Solution {
    static int V=0;

    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        // printSolution(dist);
    }

    void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }


    public static void main(String[] args) {

        File file=new File("C:\\Users\\Sahithi\\Desktop\\sample.txt");
        try
        {
     Scanner sc= new Scanner(file);
        int l=0,vertnum=0;
        String line;
        // to get to know the number of edges -- first line
        while(sc.hasNextLine())
        {
            line=sc.nextLine();
            V=Integer.parseInt(line.split(" ")[0]);
            break;
        }
        int graph[][]=new int[V][V];

        int k=0;// to keep count of  indices in temp

        while(sc.hasNextLine())
        {
        // to read from line 2 and avoid reading the last line
           line=sc.nextLine();
           if(line.split(" ").length==1)
           {
               continue;
           }

            if((l>=0) && line.split(" ").length!=2)
            {
                k=0;
                System.out.println(line);
                // to split the elements and then put in an array - even element- vertex connection -odd -- edge weight
                int [] temp = new int[line.split(" ").length-1];
                // to print

              for(int i=1;i<line.split(" ").length;i++)
              {
                temp[k]=Integer.parseInt(line.split(" ")[i]);
                  k++;
              }

              for(int i=0;i<temp.length;i++)
              {
                   System.out.println(temp[i]);
              }

              int ww =temp[temp.length-1];

              for(int i=0;i<temp.length-1;i=i+1)
              {
                  if(i%2==0 && i+1!=temp.length)
                  {
                    graph[vertnum][temp[i]]=temp[i+1];
                    System.out.println("graph["+vertnum+"]"+"["+temp[i]+"]"+"="+temp[i+1]);
                  }
                  else
                  {
                    graph[vertnum][temp[i]]=ww;
                  }
              }

              //to print the graph
              for(int i=0;i<graph.length;i++)
              {
                  for(int j=0;j<graph[0].length;j++)
                  {
                      System.out.print(graph[i][j]);
                  }
                  System.out.println();
              }
                vertnum++;
            }
            //to get to know the distance between 2 vertices
            else if(line.split(" ").length==2)
            {
                Solution sol = new Solution();
                sol.dijkstra(graph, Integer.parseInt(line.split(" ")[0]));
            }
            l++;

        }
    }
    catch(FileNotFoundException e)
    {
        e.getMessage();
    }
    }
}