public class PixelPlayground
{
    //a method that removes all blue from the pixels in a photo
    public static Picture mirrorPic(Picture p)
    {
        Picture newPicture = new Picture(p);
        Pixel[][] pixels = newPicture.getPixels2D();
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c<pixels[0].length; c++)
            {
                Pixel temp = pixels[r][c]; //left pixel
                pixels[r][pixels[0].length-1-c].setColor(temp.getColor());
            }
        }
        return newPicture;
    }
    public static Picture mirrorPicTop(Picture p)
    {
        Picture newPicture = new Picture(p);
        Pixel[][] pixels = newPicture.getPixels2D();
        for(int c = 0; c<pixels[0].length; c++)
        {
            for(int r = 0; r < pixels.length/2; r++)
            {
                Pixel temp = pixels[r][c]; //top pixel
                pixels[pixels.length-1-r][c].setColor(temp.getColor());
            }
        }
        return newPicture;
    }
    public static Picture mirrorPicBot(Picture p)
    {
        Picture newPicture = new Picture(p);
        Pixel[][] pixels = newPicture.getPixels2D();
        for(int r = pixels.length-1; r > pixels.length/2; r--)
        {
            for(int c = 0; c<pixels[0].length; c++)
            {
                Pixel temp = pixels[r][c]; //bottom pixel
                pixels[pixels.length-1-r][c].setColor(temp.getColor());
            }
        }
        return newPicture;
    }
    public static Picture mirrorPicLe(Picture p)
    {
        Picture newPicture = new Picture(p);
        Pixel[][] pixels = newPicture.getPixels2D();
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = pixels[0].length-1; c>pixels[0].length; c--)
            {
                Pixel temp = pixels[r][c]; //left pixel
                pixels[r][pixels[0].length-1-c].setColor(temp.getColor());
            }
        }
        return newPicture;
    }
    public static void main(String[] args)
    {
        Picture beachPic = new Picture("beach.jpg");
        beachPic.explore();
        Picture beachPicMirrored = mirrorPic(beachPic);
        beachPicMirrored.explore();
        Picture beachPicMirroredTop = mirrorPicTop(beachPic);
        beachPicMirroredTop.explore();
        Picture beachPicMirroredBot = mirrorPicBot(beachPic);
        beachPicMirroredBot.explore();
        Picture beachPicMirroredLe = mirrorPicLe(beachPic);
        beachPicMirroredLe.explore();
    }
}