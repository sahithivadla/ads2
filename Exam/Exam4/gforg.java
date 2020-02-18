import java.util.*;
public class gforg {
    public static ArrayList<String> permutationFinder(String str) {
        ArrayList<String> perm = new ArrayList<String>();
        //Handling error scenarios
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            perm.add("");
            return perm;
        }
        char initial = str.charAt(0); // first character
        String rem = str.substring(1); // Full string without first character
        ArrayList<String> words = permutationFinder(rem);
        for (String strNew : words) {
            for (int i = 0;i<=strNew.length();i++){
                perm.add(charInsert(strNew, initial, i));
            }
        }
        return perm;
    }

    public static String charInsert(String str, char c, int j) {
        String begin = str.substring(0, j);
        String end = str.substring(j);
        return begin + c + end;
    }

    public static void main(String[] args) {
        String s = "AAC";
        String s1 = "ABC";
        String s2 = "ABCD";
        String[] wo = new String[permutationFinder(s).size()];
        for(int i=0;i<wo.length;i++)
        {
              wo[i]=permutationFinder(s).get(i);
        }
        System.out.println("\nPermutations for " + s + " are: \n" + permutationFinder(s));
        System.out.println( wo[1]);



    }
}
