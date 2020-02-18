import java.util.HashSet;
import java.util.Set;

public class BoggleSolver{
    private TrieSET dict = new TrieSET();
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary){
       for(int i=0;i<dictionary.length;i++){
            dict.add(dictionary[i]);
       }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        // BoggleBoard bb =new BoggleBoard();
        Set<String> letterStrings = new HashSet<String>();
        int rows = board.rows();
        int cols = board.cols();
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                boolean[][] visited = new boolean[rows][cols];
                check(board,i,j,"",visited,letterStrings);
            }
        }
        return letterStrings;
    }

    private void check(BoggleBoard bb,int r,int c,String pref,boolean[][] v,Set<String> a)
    {
        if(v[r][c]){
            return ;
        }
        char let=bb.getLetter(r, c);
        String words=pref;

        if(let=='Q'){
           words=words+"QU";
        }
        else{
            words+=let;
        }

        if(!dict.hasPrefix(words)){
           return ;
        }
        if(words.length()>2 && dict.contains(words)){
            a.add(words);
        }
        v[r][c]=true;
        // to check the possibilities of adjacent letters to a particular letter  -- we will take a 3X3 grid
        // in that grid the middle element is taken as (0,0) left to that element is taken as (0,-1)
        //grid is taken , as per the graph --- the condition (r+i>=0 && c+j>=0 so on will take only possible adjacent letter)

        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(i==0 && j==0){
                    continue;
                }
                if((r+i>=0) && (r+i<bb.rows()) && (c+j>=0) && (c+j<bb.cols())){
                    check(bb, r+i, c+j, words, v, a);
                }
            }
        }
        v[r][c]=false;
    }




    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    // public int scoreOf(String word)
    // {
    //     if(word==null)
    //     {
    //         throw new IllegalArgumentException();
    //     }
    //     int wordlen=word.length();
    //     if (!dict.contains(word) && word.length()<3){
    //         return 0;
    //     }
    //     else if(wordlen==3 || wordlen==4)
    //      {
    //           return 1;
    //      }
    //      else if (wordlen==5)
    //      {
    //          return 2;
    //      }
    //      else if (wordlen==6)
    //      {
    //          return 3;
    //      }
    //      else if (wordlen==7)
    //      {
    //          return 5;
    //      }
    //      else
    //      {
    //          return 11;
    //      }

    // }
    public int scoreOf(String word){

        if (dict.contains(word)){
            switch (word.length()){
                case 0:
                case 1:
                case 2: return 0;
                case 3:
                case 4: return 1;
                case 5: return 2;
                case 6: return 3;
                case 7: return 5;
                default: return 11;
            }
        }else {
            return 0;
        }

    }
    // public static void main(String[] args)
    // {
    //  StringBuilder sb=new StringBuilder();
    //   try
    //   {
    //     String fname= "C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\boggleSolver\\boggle\\dictionary-nursery.txt";
    //     File f=new File(fname);
    //     Scanner sc =new Scanner(f);
    //     String line="";
    //   while(sc.hasNextLine())
    //   {
    //      line=sc.nextLine();
    //      sb.append(line).append(";");
    //   }
    // }
    // catch(FileNotFoundException e)
    // {
    //     e.getMessage();
    // }
    // String[]allwords = sb.substring(0).split(";");
    // BoggleSolver bs = new BoggleSolver(allwords);
    // BoggleBoard bb =new BoggleBoard("");
    // bs.getAllValidWords(bb);
//   }
}