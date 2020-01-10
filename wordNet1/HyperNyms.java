import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import edu.princeton.cs.algs4.Digraph;
public class HyperNyms
{
    static ArrayList<String> idhy = new ArrayList<String>();
    LinearProbingHashST<String,String[]> lp = new LinearProbingHashST<String,String[]>(821921);
    static ArrayList<String> arr = new ArrayList<>();



    public HyperNyms() {

    }

    public void hyperNyms(String fileName)  {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] temp;
            while ((line = br.readLine()) != null) {
                arr.add(line);
                temp = line.split(",",2);
                String s = "";
                // System.out.println("Hello - 1 :: " + line);
                if(temp.length>1)
                {
                    // System.out.println("Helloyyyyyy2");
                lp.put(line.split(",",2)[0], line.split(",",2)[1].split(","));
                idhy.add(line.split(",",2)[0]);
                }

                else if(line.split(" ").length==1)
                {
                    // System.out.println("Hello - 3 : adding" + line);
                    lp.put(line, s.split(""));
                    idhy.add(line);
                }
                // links.add(line.split(",",2)[1].split(","));
            }
        }
        catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    public String toString() {
        StringBuffer sb = new StringBuffer();
        System.out.println("Size is : " + idhy.size());
        for(int i =0 ; i<lp.size();i++)
        {
            // lp.put(idhy.get(i),links.get(i));
            String key = idhy.get(i);
            sb.append(key + " :: [");
            int j = 0;
            if (lp.get(key) !=null ) {
                String[] toks = lp.get(key);
                for (; j < toks.length-1; j++) {
                    sb.append(toks[j] + ", ");
                }
                sb.append(toks[j]);
            } else {
                sb.append(key);
            }
            sb.append("]\n");

            // System.out.println(lp.toString());
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        HyperNyms hn = new HyperNyms();
       hn.hyperNyms("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\wordNet\\hypernyms.txt");
       // we are aware that the first part of the line read from the file are the ids which are used as vertices
       // The rest of the values are adjacent vertices to the first vertex .
       Digraph graph = new Digraph(arr.size());
       for (int i = 0; i < arr.size(); i++) {
        String str[] = arr.get(i).split(",");
         int v = Integer.parseInt(str[0]);
      for (int j = 1; j < str.length; j++) {
        int w = Integer.parseInt(str[j]);
       graph.addEdge(v,w);
      }
    }

    for(int v =0;v<arr.size();v++)
    {
        System.out.print(v+"---->");
        for(int w:graph.adj(v))
        {
             System.out.print(w+" ");
        }
        System.out.println();
    }

}



}