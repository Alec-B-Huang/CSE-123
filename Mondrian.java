// ALEC HUANG
// CSE 123
// C2
// 02/15/26
// TA: Ishta Mundra

import java.util.*;
import java.awt.*;

// Class comment: Generates a mondrian style artwork by recursively dividing
// a canvas into rectangular regions and filling them with colors.
public class Mondrian {

    // BEHAVIOR: Generates a basic Mondrian style painting on the given canvas
    //           by recursively splitting regions and filling them with colors.
    // EXCEPTIONS: IllegalArgumentException
    //              if pixels == null
    //              if pixels.length < 300
    //              if pixels[0].length < 300
    // RETURNS: none
    // PARAMETERS: Color[][] pixels; pixel grid representing the canvas
    public static void paintBasicMondrian(Color[][] pixels) {
        if (pixels == null || (pixels.length < 300 || pixels[0].length < 300)) {
            throw new IllegalArgumentException();
        }

        int x1 = 0;
        int y1 = 0;
        int x2 = pixels[0].length;
        int y2 = pixels.length;
        Random rand = new Random();

        recurseBasicMondrian(pixels, x1, x2, y1, y2, pixels[0].length, pixels.length, rand);
    }

    // BEHAVIOR: Generates a complex Mondrian style painting on the given canvas
    //           by recursively splitting regions, with a random decision at each
    //           step to either split or fill the region.
    // EXCEPTIONS: IllegalArgumentException
    //              if pixels == null
    //              if pixels.length < 300
    //              if pixels[0].length < 300
    // RETURNS: none
    // PARAMETERS: Color[][] pixels; pixel grid representing the canvas
    public static void paintComplexMondrian(Color[][] pixels) {
        if (pixels == null || pixels.length < 300 || pixels[0].length < 300) {
            throw new IllegalArgumentException();
        }

        int x1 = 0;
        int y1 = 0;
        int x2 = pixels[0].length;
        int y2 = pixels.length;
        Random rand = new Random();
    
        recurseComplexMondrian(pixels, x1, x2, y1, y2, pixels[0].length, pixels.length, rand);
    }

    // BEHAVIOR: Recursively divides the given region of the canvas using basic 
    //           mondrian rules and fills regions when they are too small to split
    //           further.
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: Color[][] pixels; pixel grid representing the canvas
    //             int x1; the first x coordinate
    //             int x2; the 2nd x coordinate
    //             int y1; the first y coordinate
    //             int y2; the 2nd y coordinate
    //             int fullWidth; full width of the canvas
    //             int fullHeight; height of the canvas
    //             Random rand; random generator variable
    private static void recurseBasicMondrian(Color[][] pixels, int x1, int x2, int y1, int y2, 
                                                int fullWidth, int fullHeight, Random rand) {
        int regionWidth = x2 - x1;
        int regionHeight = y2 - y1;
        int minSplitWidth = fullWidth / 4;
        int minSplitHeight = fullHeight / 4;     
        
        if (regionWidth >= minSplitWidth && regionHeight >= minSplitHeight && 
                                                regionHeight >= 20 && regionWidth >= 20) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseBasicMondrian(pixels, x1, randomSplitNumX, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, randomSplitNumX, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, x1, randomSplitNumX, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, randomSplitNumX, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionHeight >= minSplitHeight && regionHeight >= 20) {
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseBasicMondrian(pixels, x1, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, x1, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionWidth >= minSplitWidth && regionWidth >= 20) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);

            recurseBasicMondrian(pixels, x1, randomSplitNumX, y1, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, randomSplitNumX, x2, y1, y2, 
                                                        fullWidth, fullHeight, rand);

        } else {
            int randomColorNumber = rand.nextInt(4);
            Color color = null;

            if (randomColorNumber == 0) {
                color = Color.RED;
            } else if (randomColorNumber == 1) {
                color = Color.YELLOW;
            } else if (randomColorNumber == 2) {
                color = Color.CYAN;
            } else {
                color = Color.WHITE;
            }

            fillRegion(pixels, x1, x2, y1, y2, color);
        }
    }

    // BEHAVIOR: Recursively divides the given region of the canvas using complex
    //           mondrian rules where each region randomly decides whether or not to split 
    //           or be filled with color.
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: Color[][] pixels; pixel grid representing the canvas
    //             int x1; the first x coordinate
    //             int x2; the 2nd x coordinate
    //             int y1; the first y coordinate
    //             int y2; the 2nd y coordinate
    //             int fullWidth; full width of the canvas
    //             int fullHeight; height of the canvas
    //             Random rand; random generator variable
    private static void recurseComplexMondrian(Color[][] pixels, int x1, int x2, int y1, int y2, 
                                                int fullWidth, int fullHeight, Random rand) {
        int regionWidth = x2 - x1;
        int regionHeight = y2 - y1;
        int minSplitWidth = fullWidth / 4;
        int minSplitHeight = fullHeight / 4;   
        int splitOrNot = rand.nextInt(2);
        
        if (regionWidth >= minSplitWidth && regionHeight >= minSplitHeight && 
                                    regionHeight >= 20 && regionWidth >= 20 && splitOrNot == 1) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseComplexMondrian(pixels, x1, randomSplitNumX, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, randomSplitNumX, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, x1, randomSplitNumX, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, randomSplitNumX, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionHeight >= minSplitHeight && regionHeight >= 20 && splitOrNot == 1) {
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseComplexMondrian(pixels, x1, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, x1, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionWidth >= minSplitWidth && regionWidth >= 20 && splitOrNot == 1) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);

            recurseComplexMondrian(pixels, x1, randomSplitNumX, y1, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, randomSplitNumX, x2, y1, y2, 
                                                        fullWidth, fullHeight, rand);

        } else {
            int randomColorNumber = rand.nextInt(4);
            Color color = null;

            if (randomColorNumber == 0) {
                color = Color.RED;
            } else if (randomColorNumber == 1) {
                color = Color.YELLOW;
            } else if (randomColorNumber == 2) {
                color = Color.CYAN;
            } else {
                color = Color.WHITE;
            }

            fillRegion(pixels, x1, x2, y1, y2, color);
        }
    }

    // BEHAVIOR: Fills the interior of specified region with the given color while 
    //           leaving a border around the edges.
    // EXCEPTIONS: none
    // RETURNS: none
    // PARAMETERS: Color[][] pixels; pixel grid representing the canvas
    //             int x1; the first x coordinate
    //             int x2; the 2nd x coordinate
    //             int y1; the first y coordinate
    //             int y2; the 2nd y coordinate
    //             Color color; the color used to fill pixels
    public static void fillRegion(Color[][] pixels, int x1, int x2, int y1, int y2, Color color) {
        for (int i = (y1 + 1); i < (y2 - 1); i++) {
            for (int j = (x1 + 1); j < (x2 - 1); j++) {
                pixels[i][j] = color;
            }
        }
    }    
}
