import java.util.HashMap;
import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int r=8;
    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform(){
        String str= BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(str);
        for (int i = 0; i < str.length(); ++i) {
            if(csa.index(i) == 0){
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < csa.length(); ++i) {
            int ind= csa.index(i);
            if(ind ==0){
                BinaryStdOut.write(str.charAt(str.length()-1),r);
            }
            else{
                BinaryStdOut.write(str.charAt(ind-1),r);
            }
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform(){
        int first = BinaryStdIn.readInt();
        String str = BinaryStdIn.readString();
        char[] ch = str.toCharArray();
        HashMap<Character, Queue<Integer>> table
        = new HashMap<Character, Queue<Integer>>();
        for (int i = 0; i < ch.length; ++i) {
            if (!table.containsKey(ch[i])) {
                table.put(ch[i], new Queue<Integer>());
            }
            table.get(ch[i]).enqueue(i);
        }

        Arrays.sort(ch);
        int[] next = new int[ch.length];
        for (int i = 0; i < next.length; ++i) {
            next[i] = table.get(ch[i]).dequeue();
        }

        for (int i = 0; i < next.length; ++i) {
            BinaryStdOut.write(ch[first], 8);
            first = next[first];
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args){}

}