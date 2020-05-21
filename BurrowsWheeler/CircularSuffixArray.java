import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
    private String str;
    private Integer [] index;
    // circular suffix array of s
    public CircularSuffixArray(String s){
        if (s==null){
            throw new IllegalArgumentException();
        }
        str=s;
        index=new Integer[length()];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        Arrays.sort(index,new Comparator<Integer>() {
            public int compare(Integer a, Integer b){
                int first = a;
                int second = b;
                for (int i = 0; i < str.length(); i++) {
                    char x= str.charAt(first);
                    char y= str.charAt(second);
                    if (x<y){
                        return -1;
                    }
                    else if(x>y){
                        return 1;
                    }
                    ++first;
                    ++second;
                    if (first == str.length()){
                        first =0;
                    }
                    if (second == str.length()){
                        second=0;
                    }
                }
                return 0;
            }
        });

    }

    // length of s
    public int length(){
        return str.length();
    }

    // returns index of ith sorted suffix
    public int index(int i){
        if (i>=str.length() || i<0){
            throw new IllegalArgumentException();
        }
        return index[i];

    }

    // unit testing (required)
    public static void main(String[] args){}

}