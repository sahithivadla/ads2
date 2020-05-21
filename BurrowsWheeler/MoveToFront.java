import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int r=8;
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode(){
        char[] letters = new char [256];
        for (int i = 0; i < 256; i++) {
            letters[i] = (char)i;
        }
        while (!BinaryStdIn.isEmpty()){
            char ch = BinaryStdIn.readChar();
            char t1 = ch;
            char t2 = ch;
            for (int i = 0; i < 256; i++) {
                if (ch == letters[i]){
                    BinaryStdOut.write(i,r);
                    letters[i]=t1;
                    break;
                }
                t2=letters[i];
                letters[i]=t1;
                t1=t2;
            }
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode(){
        char[] letters = new char [256];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char)i;
        }
        while (!BinaryStdIn.isEmpty()){
            char ch = BinaryStdIn.readChar();
            char temp1 = letters[ch];
            char temp2 = letters[ch];
            BinaryStdOut.write(letters[ch],r);
            for (int i = 0; i < letters.length; i++) {
                if(letters[ch] == letters[i]){
                    letters[i]=temp2;
                    break;
                }
                temp2 = letters[i];
                letters[i] = temp1;
                temp1=temp2;
            }
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args){}

}