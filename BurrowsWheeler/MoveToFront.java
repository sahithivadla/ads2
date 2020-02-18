
public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode()
    {
        fillchararr();


    }
    private static void fillchararr()
    {
        char[] allchar= new char[256];
        for(int i=0;i<256;i++)
        {
            // System.out.println((char)i);
            allchar[i]=(char)i;
            System.out.println(allchar[i]);
        }

    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    // public static void decode()
    // {

    // }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args)
    {
        MoveToFront mtf = new MoveToFront();
        char[] allchar= new char[256];
        for(int i=0;i<256;i++)
        {
            // System.out.println((char)i);
            allchar[i]=(char)i;
            System.out.println(allchar[i]);
        }
        encode();
    }

}

