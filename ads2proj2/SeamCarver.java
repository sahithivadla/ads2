import java.io.File;
import java.util.Arrays;
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
public class SeamCarver {
    private Picture pict;
//    private   int picheight=0;
//     private  int picwidth=0;
    private static  double[][] energyarr;
    private  static double[][] energyarrt;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.pict = picture;
        }

    // returns current picture
    public Picture picture() {
        return pict;
    }

    // width of current picture
    public int width() {
        return pict.width();
    }

    // height of current picture
    public int height() {
        return pict.height();
    }

    // energy of pixel at column x and row y

    private void energyar() {
        energyarr = new double[width()][height()];
        for (int i = 0; i < energyarr[0].length; i++) {
            energyarr[0][i] = 1000;
            energyarr[width() - 1][i] = 1000;
        }
        for (int i = 0; i < energyarr.length; i++) {
            energyarr[i][0] = 1000;
            energyarr[i][height() - 1] = 1000;
        }
        for(int i=1; i<width()-1;i++)
        {
          for(int j=1;j<height()-1;j++)
          {
            energyarr[i][j]= energy(i,j);
        // System.out.println(energyarr[i][j]);
          }
        }
        energyarrt = new double[height()][width()];
        energyarrt=mattranspose(energyarr);

    }

    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1)
            throw new IndexOutOfBoundsException();
        if(x==0 || x==width()-1 ||y==0||y==height()-1)
         return 1000;
        return Math.sqrt(xValue(x, y) + yValue(x, y));
    }
    private int xValue(int x, int y) {
        // in order to get the rgb values of a particular pixel
        // take the pixels above and below that particular pixel and get their
        // respective colours

        Color c1 = pict.get(x - 1, y);
        Color c2 = pict.get(x + 1, y);
        int rxval = Math.abs(c1.getRed() - c2.getRed());
        int gxval = Math.abs(c1.getGreen() - c2.getGreen());
        int bxval = Math.abs(c1.getBlue() - c2.getBlue());
        return (rxval * rxval) + (gxval * gxval) + (bxval * bxval);
    }

    private int yValue(int x, int y) {
        // in order to get the rgb values of a particular pixel,
        // take the pixels leftside and rightside that particular pixel and get their
        // respective colours
        Color c1 = pict.get(x, y - 1);
        Color c2 = pict.get(x, y + 1);
        int ryval = Math.abs(c1.getRed() - c2.getRed());
        int gyval = Math.abs(c1.getGreen() - c2.getGreen());
        int byval = Math.abs(c1.getBlue() - c2.getBlue());
        return (ryval * ryval) +(gyval * gyval) + (byval * byval);
    }

    private double[][] mattranspose(double[][]a)
    {
        double[][] transpose = new double[height()][width()];
        for(int i=0;i<pict.height();i++){
            for(int j=0;j<pict.width();j++){
            transpose[i][j]=a[j][i];
            }
            }
            return transpose;
    }

    // sequence of indices for horizontal seam
    public int[] findVerticalSeam()
    {
    energyar();
    double[][] finalarr=new double[height()][width()];
    finalarr=cumulativevert(energyarrt);
    // first go from top to bottom
        // for(int i=0;i<height();i++)
        // {
        //     for(int j=0;j<width();j++)
        //     {
        //         System.out.print(finalarr[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        int[] fseam = new int[seamcalc(finalarr).length];
        fseam=seamcalc(finalarr);
        int[] finalseam=new int[fseam.length];
        int k=0;
        for(int i=finalseam.length-1;i>=0;i--)
        {
            finalseam[k]=fseam[i];
            k++;
        }

        // for(int i=0;i<finalseam.length;i++)
        // {
        //     System.out.print(finalseam[i]);
        // }
        return (finalseam);
    }

    private double[][] cumulativevert(double[][]b)
    {
        int rows= b.length;
        int cols =b[0].length;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(i==0)
                {
                    b[i][j]=1000;
                }
                  else if(j==0)
                  {
                      //if it is the first element , then get the minimum of above two cells and add the min of those 2 to the current element
                      b[i][j]=energyarrt[i][j]+Math.min(b[i-1][0],b[i-1][1]);
                  }
                   //middle elements
                   else if(j > 0 && j < width() - 1)
                   {
                        double min1=Math.min(b[i-1][j-1],b[i-1][j]);
                        double min2=b[i-1][j+1];
                        b[i][j]=energyarrt[i][j]+Math.min(min1,min2);
                   }
                   //for the last elements
                   else
                   {
                       b[i][j]=energyarrt[i][j]+Math.min(b[i-1][j-1],b[i-1][j]);
                   }
            }
        }
        // for(int i=0;i<rows;i++)
        // {
        //     for(int j=0;j<cols;j++)
        //     {
        //         System.out.print(b[i][j]+" ");
        //     }
        // }
        // System.out.println();
        return b;
    }

    private double[][] cumulativehor(double[][]b)
    {
        int rows= b.length;
        int cols =b[0].length;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(i==0)
                {
                    b[i][j]=1000;
                }
                  else if(j==0)
                  {
                      //if it is the first element , then get the minimum of above two cells and add the min of those 2 to the current element
                      b[i][j]=energyarr[i][j]+Math.min(b[i-1][0],b[i-1][1]);
                  }
                   //middle elements
                   else if(j > 0 && j < cols - 1)
                   {
                        double min1=Math.min(b[i-1][j-1],b[i-1][j]);
                        double min2=b[i-1][j+1];
                        b[i][j]=energyarr[i][j]+Math.min(min1,min2);
                   }
                   //for the last elements
                   else
                   {
                       b[i][j]=energyarr[i][j]+Math.min(b[i-1][j-1],b[i-1][j]);
                   }
            }
        }
        // for(int i=0;i<rows;i++)
        // {
        //     for(int j=0;j<cols;j++)
        //     {
        //         System.out.print(b[i][j]+" ");
        //     }
        // }
        // System.out.println();
        return b;
    }
    private int[] seamcalc(double[][]a)
    {
        int cols=a[0].length;
        int rows=a.length;
        int[] seam = new int[rows];
        double min=50000;
        int k=1,ind=0;
        // for the last row find the index of least element separately
        for(int i=0;i<1;i++)
        {
            min=50000;
             for(int j=0;j<cols;j++)
             {
                 if(min>a[rows-1][j])
                 {
                    min=a[rows-1][j];
                    k=j;
                 }
             }
             seam[0]=k;
        }
        // System.out.println(seam[0]);
        //for the rest of the rows
        k=1;
        for(int i=rows-2;i>=0;i--)
        {
                // if the previous minimum element is the first element of the row
               if(seam[k-1]==0)
               {
                    if(a[i][k]>a[i][k+1])
                    {
                         seam[k]=k+1;
                         k++;
                    }
                    else
                    {
                        seam[k]=k;
                        k++;
                    }
               }
               // if the previously selected minimum element is the middle element in the row
               else if(seam[k-1]==cols-1)
               {
                if(a[i][k]>a[i][k-1])
                {
                    seam[k]=seam[k-1]-1;
                    k++;
                }
                else
                {
                    seam[k]=seam[k-1];
                    k++;
                }
               }
               // if the previously found minimum element is the last element
             else
             {
                double m1 = a[i][seam[k-1]-1];
                double m2 = a[i][seam[k-1]];
                double m3 =a[i][seam[k-1]+1];
                // after finding minimum of 2 elements compare that minimum with the third element
                if(m1<m2)
                {
                       if(m1<m3)
                       {
                           seam[k]=seam[k-1]-1;
                           k++;
                       }
                       else
                       {
                           seam[k]=seam[k-1]+1;
                           k++;
                       }
                }
                else if (m1>m2)
                {
                    if(m2<m3)
                       {
                           seam[k]=seam[k-1];
                           k++;
                       }
                       else
                       {
                           seam[k]=seam[k-1]+1;
                           k++;
                       }
                }

                else
                    {
                        if(m1<m3)
                        {
                            seam[k]=seam[k-1]-1;
                           k++;
                        }
                        else if(m3>m1)
                         {
                            seam[k]=seam[k-1]+1;
                            k++;
                         }
                         else
                          {
                            seam[k]=seam[k-1]-1;
                            k++;
                          }
                    }
              }
            }

        return seam;
    }
    // sequence of indices for horizontal  seam
    public int[] findHorizontalSeam()
    {
      energyar();
      double[][] bb=new double[width()][height()];
      bb=cumulativehor(energyarr);
      int[] fseam=new int[seamcalc(bb).length];
      fseam=seamcalc(bb);
        int[] finalseam=new int[fseam.length];
        int k=0;
        for(int i=finalseam.length-1;i>=0;i--)
        {
            finalseam[k]=fseam[i];
            k++;
        }
        return (finalseam);
    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam)
    {        if (seam == null || height() == 1 || seam.length != width()) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= height()) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < seam.length - 1; i++) {
            if (seam[i] < 0 || Math.abs(seam[i] - seam[i + 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
        Picture resizedPicture;
        resizedPicture = new Picture(pict.width(), pict.height() - 1);

        for (int i = 0; i < resizedPicture.width(); i++) {
            for (int j = 0; j < resizedPicture.height();) {
                if (j >= seam[i]) {
                    resizedPicture.set(i, j, pict.get(i, ++j));
                } else {
                    resizedPicture.set(i, j, pict.get(i, j++));
                }
            }
        }
        // System.out.println(pict);
        // System.out.println(resizedPicture);
        pict = resizedPicture;
        resizedPicture = null;
        double[][] resizedarr=new double[pict.height()][pict.width()];
        // for(int i=0;i<pict.width();i++)
        // {
        //    resizedarr[0][i]=1000;
        //    resizedarr[pict.height()-1][i]=1000;
        // }
        // for(int i=0;i<pict.height();i++)
        // {
        //      resizedarr[i][0]=1000;
        //      resizedarr[i][pict.width()-1]=1000;
        // }

        // for(int i=1;i<pict.height()-1;i++)
        // {
        //    for(int j=1;j<pict.width()-1;j++)
        //    {
        //        resizedarr[i][j]=Math.sqrt(energy(j,i));
        //    }
        // }
        // for(int i=0;i<pict.height();i++)
        // {
        //     for(int j=0;j<pict.width();j++)
        //     {
        //         System.out.print(resizedarr[i][j]+" ");
        //     }
        //     System.out.println();
        // }


    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam)
    {
        if (seam == null || width() == 1 || seam.length != height()){
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= width()) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < seam.length - 1; i++) {
            if (seam[i] < 0 || Math.abs(seam[i] - seam[i + 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }

        Picture resizedPicture;
        resizedPicture = new Picture(pict.width() - 1, pict.height());

        for (int i = 0; i < resizedPicture.height(); i++) {
            for (int j = 0; j < resizedPicture.width();) {
                if (j >= seam[i]) {
                    resizedPicture.set(j, i, pict.get(++j, i));
                } else {
                    resizedPicture.set(j, i, pict.get(j++, i));
                }
            }
        }
        // System.out.println(pict);
        // System.out.println(resizedPicture);
        pict = resizedPicture;
        resizedPicture = null;
        // double[][] resizedarr=new double[pict.height()][pict.width()];
        // for(int i=0;i<pict.height();i++)
        // {
        //      resizedarr[i][0]=1000;
        //      resizedarr[i][pict.width()-1]=1000;
        // }
        // for(int i=0;i<pict.width();i++)
        // {
        //    resizedarr[0][i]=1000;
        //    resizedarr[pict.height()-1][i]=1000;
        // }

        // for(int i=1;i<pict.height()-1;i++)
        // {
        //    for(int j=1;j<pict.width()-1;j++)
        //    {
        //        resizedarr[i][j]=Math.sqrt(energy(j,i));
        //    }
        // }
        // for(int i=0;i<pict.height();i++)
        // {
        //     for(int j=0;j<pict.width();j++)
        //     {
        //         System.out.print(resizedarr[i][j]+" ");
        //     }
        //     System.out.println();
        // }

    }

    // unit testing (optional)
    public static void main(String[] args)
    {
        Picture pi = new Picture("C:\\Users\\Sahithi\\Desktop\\ads2\\ads2\\ads2proj2\\10x12.png");
        SeamCarver sc = new SeamCarver(pi);
        // energyarrt=new double[height()][width()];
        sc.energyar();
        int[] c=sc.findHorizontalSeam();
        System.out.println(Arrays.toString(c));
    }
}