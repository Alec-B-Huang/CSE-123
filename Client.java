import java.awt.*;

public class Client {
    public static void main(String[] args) {
        // 1. Create a new picture sized 400 x 400 pixels
        Picture picture = new Picture(400, 400);
        Color[][] pixels = picture.getPixels();
        // 2. Call divide divide canvas
        divideCanvas(pixels, 2, 0, 400, 0, 400);
        // 3. Save the image to display it
        picture.save("picture.jpg");
        picture.setPixels(pixels);
    }

    // TODO: Implement divideCanvas below using your 'fill' implementation
    // from the previous slide
    public static void divideCanvas(Color[][] pixels, int n, int x1, int x2, int y1, int y2) {
        if (n == 0) {
            fillRegion(pixels, x1, x2, y1, y2);
            return;
        } else {
            int midX = (x2 + x1) / 2;
            int midY = (y2 + y1) / 2;

            divideCanvas(pixels, (n - 1), x1, midX, y1, midY);
            divideCanvas(pixels, (n - 1), midX, x2, y1, midY);
            divideCanvas(pixels, (n - 1), x1, midX, midY, y2);
            divideCanvas(pixels, (n - 1), midX, x2, midY, y2);
        }
    }

    // TODO: Paste in 'fill' from the previous slide
    public static void fillRegion(Color[][] pixels, int x1, int x2, int y1, int y2) {
        for (int i = (y1 + 1); i < (y2 - 1); i++) {
            for (int j = (x1 + 1); j < (x2 - 1); j++) {
                Color white = new Color(255, 255, 255);
                pixels[i][j] = white;
            }
        }
    }
}
