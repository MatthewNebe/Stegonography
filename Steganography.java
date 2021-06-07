import java.awt.Color;
import java.util.Scanner;
import java.awt.Point;
import java.util.ArrayList;
public class Steganography
{
    /** 
    * Set the lower 2 bits in a pixel to the highest 2 bits in c 
    */
    public static void clearLow (Pixel p) 
    {
    /* To be implemented */ 
        p.setRed((p.getRed() / 4) * 4);
        p.setGreen((p.getGreen() / 4) * 4);
        p.setBlue((p.getBlue() / 4) * 4);
    }
    public static Pixel setLow (Pixel p, Color c) 
    {
        clearLow(p);
        int newvalueR = (c.getRed() / 64);
        int newvalueG = (c.getGreen() / 64);
        int newvalueB = (c.getBlue() / 64);
        p.setRed(p.getRed() + newvalueR);
        p.setGreen(p.getGreen() + newvalueG);
        p.setBlue(p.getBlue() + newvalueB);
        return p;
    }
    public static Picture testSetLow(Picture p, Color f)
    {
        Picture newPicture = new Picture(p);
        Pixel[][] pixels = newPicture.getPixels2D();
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c<pixels[0].length; c++)
            {
                //pixels[r][c]; //bottom pixel
                setLow(pixels[r][c], f);
            }
        }
        return newPicture;
    }
    /** 
    * Sets the highest two bits of each pixel's colors to the lowest two bits of each pixel's colors 
    */ 
    public static Picture revealPicture(Picture hidden) 
    { 
        Picture copy = new Picture(hidden); 
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D(); 
        for (int r = 0; r < pixels.length; r++)
        { 
            for (int c = 0; c < pixels[0].length; c++)
            { 	
                Color col = source[r][c].getColor();
                //col variables 
                int red = col.getRed();
                int green = col.getGreen();
                int blue = col.getBlue();

                red %= 4; 
                red *= 64;
                green %= 4;
                green *= 64;
                blue %= 4;
                blue *= 64;

                /* To be implemented */
                pixels[r][c].setRed(red);
                pixels[r][c].setGreen(green);
                pixels[r][c].setBlue(blue);
            }
	    }
    return copy; 
    }
    public static boolean canHide(Picture p, Picture l)
    {
        if(p.getWidth() >= l.getWidth() && p.getHeight() >= l.getHeight())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Picture hidePicture(Picture p, Picture l)
    {
        Picture source = new Picture(p); 
        Picture secret = new Picture(l); 
        Pixel[][] pixels = source.getPixels2D();
        Pixel[][] pixelsH = secret.getPixels2D(); 
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c<pixels[0].length; c++)
            {
                pixels[r][c].setColor(setLow(pixels[r][c], pixelsH[r][c].getColor()).getColor());
            }
        }
        return source;
    }
    public static Picture hidePictureNew(Picture p, Picture l, int x, int y)
    {
        Picture source = new Picture(p); 
        Picture secret = new Picture(l); 
        Pixel[][] pixels = source.getPixels2D();
        Pixel[][] pixelsH = secret.getPixels2D(); 
        for(int r = x, sRow = 0; r < x + secret.getHeight(); r++, sRow++)
        {
            for(int c = y, sCol = 0; c < y + secret.getWidth(); c++, sCol++)
            {
                setLow(pixels[r][c], pixelsH[sRow][sCol].getColor());
            }
        }
        return source;
    }
    public static boolean isSame(Picture p, Picture l)
    {
        Picture source = new Picture(p); 
        Picture secret = new Picture(l);
        Pixel[][] pixels = source.getPixels2D();
        Pixel[][] pixelsH = secret.getPixels2D();
        if(p.getWidth() <= l.getWidth() && p.getHeight() <= l.getHeight())
        {
            for(int r = 0; r < pixels.length; r++)
            {
                for(int c = 0; c<pixels[0].length; c++)
                {
                    if(pixels[r][c].getRed() == pixelsH[r][c].getRed() && pixels[r][c].getBlue() == pixelsH[r][c].getBlue() && pixels[r][c].getGreen() == pixelsH[r][c].getGreen())
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<Point> findDifferences(Picture p, Picture l)
    {
        Picture source = new Picture(p);//original image
        Picture secret = new Picture(l); //compared image
        Pixel[][] pixels = source.getPixels2D(); //array of pixels for original image
        Pixel[][] pixelsH = secret.getPixels2D(); //array of pixels for compared image
        ArrayList<Point> list = new ArrayList<Point>(); //makes en empty list for points
        if(p.getHeight() != l.getHeight()||p.getWidth() != l.getWidth()) //checks for same size
        {
            return list; //if not same size returns empty list
        }
        for(int r = 0; r < pixels.length; r++) //iterates through rows
            {
                for(int c = 0; c<pixels[0].length; c++) //iterates through columns
                {
                    //if statement below compares RGB values or orig. and compared image to check if theyre different
                    if(pixels[r][c].getRed() != pixelsH[r][c].getRed() || pixels[r][c].getBlue() != pixelsH[r][c].getBlue() || pixels[r][c].getGreen() != pixelsH[r][c].getGreen())
                    {
                        Point po = new Point(r, c); //if different create point from r(row x) nd c(column y)
                        list.add(po); //adds point to list(list of coordinates of differences)
                    }
                }
            }
        return list; //if none true returns empty list  
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Picture beach2 = new Picture("beach.jpg");
        beach2.explore(); 
        Picture copy2 = testSetLow(beach2, Color.PINK);
        copy2.explore();
        Picture copy3 = revealPicture(copy2);
        copy3.explore(); 
        Picture bMo = new Picture("blueMotorcycle.jpg");
        Picture robot = new Picture("robot.jpg"); 
        Picture flower1 = new Picture("flower1.jpg");
        Picture swan = new Picture("swan.jpg"); 
        Picture swan2 = new Picture("swan.jpg");
        Picture kitten = new Picture("kitten2.jpg");

        System.out.println("swan and swan2 are the same: " + isSame(swan, swan2));

        swan = PixelPlayground.mirrorPic(swan);
        System.out.println("swan and swan2 are the same (after mirrorPic run on swan): "+ isSame(beach2, bMo));
        Picture copy4 = hidePicture(beach2, bMo);
        if(canHide(beach2, bMo))
        {
            copy4 = hidePicture(beach2, bMo);
            copy4.explore();
            Picture copy5 = revealPicture(copy4);
            copy5.explore();
        }
        else 
        {
            System.out.print("NO");
        }
        if(canHide(beach2, robot))
        {
            System.out.println("Enter an x value: ");
            int xValue = sc.nextInt();
            System.out.println("Enter an y value: ");
            int yValue = sc.nextInt();
            Picture copy6 = hidePictureNew(beach2, robot, xValue, yValue);
            copy6.explore();
            Picture copy7 = revealPicture(copy6);
            copy7.explore();
        }
        else
        {
            System.out.print("NO");
        }
        Picture copy8 = hidePicture(beach2, bMo);
        System.out.println(findDifferences(beach2, copy8).size());

        Picture arch = new Picture("arch.jpg");
        Picture arch2 = new Picture("arch.jpg");
        Picture koala = new Picture("koala.jpg");
        Picture robot1 = new Picture("robot.jpg");

        ArrayList<Point> pointList = findDifferences(arch, arch2);
        System.out.println("PointList after comparing two identical pictures has a size of " + pointList.size());

        pointList = findDifferences(arch, koala); 
        System.out.println("PointList after comparing two different sized pictures has a size of " + pointList.size());

        arch2 = hidePictureNew(arch, robot1, 65, 102);

        pointList = findDifferences (arch, arch2);
        System.out.println("Pointlist after hiding a picture has a size of " + pointList.size());

        arch.show();
        arch2.show(); 

        ArrayList<Point> pointList2 = findDifferences(beach2, copy4);
        System.out.println("Difference between beach2 and copy4 is: " + pointList2.size());

        ArrayList<Point> pointlistKitten = findDifferences(beach2, kitten);
        System.out.println("Differences between beach and kitten is: " + pointlistKitten.size());


    }
}
