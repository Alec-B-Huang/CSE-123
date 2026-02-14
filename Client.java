import java.awt.Color;

public class Client {
    public static void main(String[] args) {
        // 1. Create a new picture with size 400 x 400
        Picture picture = new Picture(400, 400);
        // 2. Get the pixels out of the image
        Color[][] pixels = picture.getPixels();
        // 3. Call fill, providing a specific region
        fillRegion(pixels, 0, 250, 0, 320);
        // 4. Set the pixels of the image
        picture.setPixels(pixels);
        // 5. Save the image to display it
        picture.save("picture.jpg");
    }

    // TODO: Implement fill below (this solution can be iterative)
    public static void fillRegion(Color[][] pixels, int x1, int x2, int y1, int y2) {
        for (int i = 0; i < (y2 - y1); i++) {

            for (int j = 0; j < (x2 - x1); j++) {
                int x = x1 + j;
                int y = y1 + i;

                Color white = new Color(255, 255, 255);
                pixels[y][x] = white;
            }
        }
    }
}