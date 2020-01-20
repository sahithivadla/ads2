class sample
{
    public static void main(String[] args) {
       String s="sahithi";
       String res="";
        for(int i=s.length()-1;i>=0;i--)
        {
           res=res+Character.toString(s.charAt(i));
        }
        System.out.println(res);
    }
}