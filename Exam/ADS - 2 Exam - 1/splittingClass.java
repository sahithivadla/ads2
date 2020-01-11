import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;


public class splittingClass
{
    static ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<String> emails = new ArrayList<String>();
    static ArrayList<Integer> from = new ArrayList<Integer>();
    static ArrayList<Integer> to = new ArrayList<Integer>();
    static ArrayList<String> arr = new ArrayList<String>();


    static LinearProbingHashST<Integer, String> lp = new LinearProbingHashST<Integer, String>();
    // static LinearProbingHashST<Integer,ArrayList<Integer>> lpconn = new LinearProbingHashST<Integer,ArrayList<Integer>>();
    // static LinearProbingHashST<Integer,Integer> lpconncount = new LinearProbingHashST<Integer,Integer>();
    // just to know the non duplicate hash array ;;

    static Hashtable<Integer,Integer> lpconndup = new Hashtable<Integer,Integer>();
    static Hashtable<String,Integer> lpconn = new Hashtable<String,Integer>();





    public splittingClass() {

    }

    public void splitter(String filename) {
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                id.add(Integer.parseInt(line.split(";")[0]));
                emails.add(line.split(";")[1]);
                lp.put(Integer.parseInt(line.split(";")[0]), line.split(";")[1]);
            }
        }

        catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }



    public void digraph(String fname)
    {
        try {
            File file = new File(fname);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                arr.add(line);
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    public static void sortValue(Hashtable<?, Integer> t){

        //Transfer as List and sort it
        ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){

          public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
             return o1.getValue().compareTo(o2.getValue());
         }});

         for(int i=lp.size() ; i>=lp.size()-10;i++)
         {
             System.out.println(l.get(i));
         }


     }



    public static void main(String[] args) {
        splittingClass spc = new splittingClass();
        spc.splitter("C:\\Users\\Sahithi\\Desktop\\ADS - 2 Exam - 1\\emails.txt");
        // for (int i = 0; i < lp.size(); i++)
        // {
        // System.out.println(id.get(i)+"--->"+ lp.get(id.get(i)));
        // }
        spc.digraph("C:\\Users\\Sahithi\\Desktop\\ADS - 2 Exam - 1\\email-logs.txt");
        // for (int i = 0; i < lpconn.size(); i++)
        // {
        // System.out.println(to.get(i)+"--->"+ lpconn.get(to.get(i)));
        // }

        // System.out.println(to);
        // System.out.println(from);
        // for (int i = 0; i < lpconn.size(); i++)
        // {
        // System.out.println(to.get(i)+"--->"+ lpconn.get(to.get(i)));
        // lpconn.remove(0);
        // }
        Digraph graph = new Digraph(lp.size());
        ArrayList<Integer> tid = new ArrayList<Integer> ();
        ArrayList<Integer> fid = new ArrayList<Integer> ();

        for(int i=0;i<arr.size();i++)
        {
                fid.add(Integer.parseInt(arr.get(i).split(",")[0].split(" ")[1]));

                to.add(Integer.parseInt(arr.get(i).split(",")[1].split(" ")[2]));
                graph.addEdge(Integer.parseInt(arr.get(i).split(",")[0].split(" ")[1]),Integer.parseInt(arr.get(i).split(",")[1].split(" ")[2]));
        }

               for(int i=0;i<graph.V();i++)
               {
                        // System.out.println(i+"--->"+graph.indegree(i));
                        lpconndup.put(graph.indegree(i),i);
                        // to.add(graph.indegree(i));

               }



            ArrayList<Integer> l = new ArrayList(lpconndup.keySet());
            Collections.sort(l,Collections.reverseOrder());
            for(int i=0 ;i<=10;i++)
            {
                System.out.println(lp.get(lpconndup.get(l.get(i)))+"--"+l.get(i));
            }






    //         // System.out.println(tid);
    //         // System.out.println(fid);

    //         //    spc.sortValue(lpconndup);






    }

    }
